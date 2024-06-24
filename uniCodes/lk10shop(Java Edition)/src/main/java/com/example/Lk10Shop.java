package com.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import java.util.regex.Pattern;

public class Lk10Shop {
    private static final Logger logger = Logger.getLogger(Lk10Shop.class.getName());
    private static final String DONATION_FILE = "donations_total.txt";
    private static final String LOGIN_FILE = "login_info.json";

    static {
        try {
            FileHandler fileHandler = new FileHandler("Lk10Shop.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayStartScreen(MultiWindowTextGUI gui) {
        BasicWindow window = new BasicWindow();
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));

        panel.addComponent(new Label("Welcome to the Lk10 e-Manager"));
        panel.addComponent(new Label("============================="));
        panel.addComponent(new Button("Login", () -> {
            window.close();
            loginAction(gui);
        }));
        panel.addComponent(new Button("Register", () -> {
            window.close();
            registerAction(gui);
        }));
        panel.addComponent(new Button("Quit", () -> System.exit(0)));
        panel.addComponent(new Label("============================="));

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    private static void loginAction(MultiWindowTextGUI gui) {
        BasicWindow window = new BasicWindow();
        List<LoginInfo> loginData = loadAllLoginInfo();
        if (loginData.isEmpty()) {
            new MessageDialogBuilder()
                .setTitle("Info")
                .setText("No users registered. Please register first.")
                .addButton(MessageDialogButton.OK)
                .build()
                .showDialog(gui);
            displayStartScreen(gui);
            return;
        }

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));

        panel.addComponent(new Label("Select a user to login:"));
        for (int i = 0; i < loginData.size(); i++) {
            int index = i;
            panel.addComponent(new Button(loginData.get(i).name, () -> {
                animateLoginName(gui, loginData.get(index).name);
                mainInteraction(gui, new Cliente(loginData.get(index).name, loginData.get(index).email, loginData.get(index).senha));
                window.close();
            }));
        }
        panel.addComponent(new Button("Back", () -> {
            window.close();
            displayStartScreen(gui);
        }));

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    private static void registerAction(MultiWindowTextGUI gui) {
        BasicWindow window = new BasicWindow();
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        TextBox nameBox = new TextBox();
        TextBox emailBox = new TextBox();
        TextBox senhaBox = new TextBox();

        panel.addComponent(new Label("Digite seu nome:"));
        panel.addComponent(nameBox);
        panel.addComponent(new Label("Digite seu email:"));
        panel.addComponent(emailBox);
        panel.addComponent(new Label("Digite sua senha:"));
        panel.addComponent(senhaBox);
        panel.addComponent(new Button("Register", () -> {
            saveLoginInfo(nameBox.getText(), emailBox.getText(), senhaBox.getText());
            new MessageDialogBuilder()
                .setTitle("Info")
                .setText("Registration successful. You can now log in.")
                .addButton(MessageDialogButton.OK)
                .build()
                .showDialog(gui);
            window.close();
            displayStartScreen(gui);
        }));
        panel.addComponent(new Button("Back", () -> {
            window.close();
            displayStartScreen(gui);
        }));

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    private static void saveLoginInfo(String name, String email, String senha) {
        List<LoginInfo> loginData = loadAllLoginInfo();
        loginData.add(new LoginInfo(name, email, senha));
        try (Writer writer = new FileWriter(LOGIN_FILE)) {
            writer.write(new com.google.gson.Gson().toJson(loginData));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<LoginInfo> loadAllLoginInfo() {
        List<LoginInfo> loginData = new ArrayList<>();
        if (new File(LOGIN_FILE).exists()) {
            try (Reader reader = new FileReader(LOGIN_FILE)) {
                LoginInfo[] loginArray = new com.google.gson.Gson().fromJson(reader, LoginInfo[].class);
                if (loginArray != null) {
                    for (LoginInfo loginInfo : loginArray) {
                        loginData.add(loginInfo);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loginData;
    }

    private static void animateLoginName(MultiWindowTextGUI gui, String name) {
        new MessageDialogBuilder()
            .setTitle("Info")
            .setText("Logging in as: " + name + "\nLogin successful!")
            .addButton(MessageDialogButton.OK)
            .build()
            .showDialog(gui);
    }

    private static void mainInteraction(MultiWindowTextGUI gui, Cliente cliente) {
        BasicWindow window = new BasicWindow();
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));

        panel.addComponent(new Label("Hello " + cliente.nome + "!"));
        panel.addComponent(new Label("Here are some product examples:"));

        Produto[] products = {
            new Produto("Laptop", 6000),
            new Produto("Smartphone", 3500),
            new Produto("Headphones", 400),
            new Produto("Monitor", 1500),
            new Produto("Keyboard", 500)
        };

        for (Produto product : products) {
            panel.addComponent(new Button(product.nome + " - R$" + product.preco, () -> {
                String quantityStr = new TextInputDialogBuilder()
                    .setTitle("Quantity")
                    .setDescription("Enter quantity for " + product.nome + ":")
                    .setValidationPattern(Pattern.compile("\\d+"), "Must be a number")
                    .build()
                    .showDialog(gui);

                int quantity = Integer.parseInt(quantityStr);
                cliente.selecionar_produto(product, quantity);
                new MessageDialogBuilder()
                    .setTitle("Info")
                    .setText("Added " + quantity + "x " + product.nome + " to your order.")
                    .addButton(MessageDialogButton.OK)
                    .build()
                    .showDialog(gui);
            }));
        }

        panel.addComponent(new Button("Finalize", () -> {
            double donation = getDonationAmount(gui);
            if (donation >= 0.009) {
                updateDonationTotal(donation);
                String notaFiscal = cliente.pedido.gerar_nota_fiscal(cliente.nome, donation);
                savePurchase(cliente.nome, notaFiscal);
                animateNotaFiscal(gui, notaFiscal);
            } else {
                String notaFiscal = cliente.pedido.gerar_nota_fiscal(cliente.nome);
                savePurchase(cliente.nome, notaFiscal);
                animateNotaFiscal(gui, notaFiscal);
            }
        }));
        panel.addComponent(new Button("Back", () -> {
            window.close();
            displayStartScreen(gui);
        }));

        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    private static double getDonationAmount(MultiWindowTextGUI gui) {
        String donationStr = new TextInputDialogBuilder()
            .setTitle("Donation")
            .setDescription("Do you want to contribute to helping the state of Rio Grande Do Sul in Brazil suffering with a lot of floods?\n(Any value above 0.009 cents is accepted)\nEnter donation amount:")
            .setValidationPattern(Pattern.compile("\\d+(\\.\\d{1,2})?"), "Must be a valid amount")
            .build()
            .showDialog(gui);
        return Double.parseDouble(donationStr);
    }

    private static void updateDonationTotal(double amount) {
        double total = 0.00;
        if (new File(DONATION_FILE).exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(DONATION_FILE))) {
                total = Double.parseDouble(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        total += amount;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DONATION_FILE))) {
            writer.write(String.format("%.2f", total));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void savePurchase(String clientName, String notaFiscal) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(clientName + "_purchases.log", true))) {
            writer.write(notaFiscal + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void animateNotaFiscal(MultiWindowTextGUI gui, String notaFiscal) {
        new MessageDialogBuilder()
            .setTitle("Nota Fiscal")
            .setText(notaFiscal)
            .addButton(MessageDialogButton.OK)
            .build()
            .showDialog(gui);
    }

    public static void main(String[] args) {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setTerminalEmulatorTitle("Kitty Terminal");
        terminalFactory.setPreferTerminalEmulator(true);

        try (Terminal terminal = terminalFactory.createTerminal()) {
            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();

            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);
            displayStartScreen(gui);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Cliente {
        String nome;
        String email;
        String senha;
        Pedido pedido;

        Cliente(String nome, String email, String senha) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.pedido = new Pedido();
        }

        void selecionar_produto(Produto produto, int quantidade) {
            this.pedido.adicionar_produto(produto, quantidade);
        }
    }

    private static class Produto {
        String nome;
        double preco;

        Produto(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }
    }

    private static class Pedido {
        List<Produto> produtos = new ArrayList<>();

        void adicionar_produto(Produto produto, int quantidade) {
            for (int i = 0; i < quantidade; i++) {
                this.produtos.add(produto);
            }
        }

        String gerar_nota_fiscal(String clientName) {
            return gerar_nota_fiscal(clientName, 0);
        }

        String gerar_nota_fiscal(String clientName, double donation) {
            StringBuilder nota = new StringBuilder();
            nota.append("NOTA FISCAL\n")
                .append("--------------------------------\n")
                .append(String.format("%-15s %-10s %-10s\n", "Produto", "Preço", "Quantidade"))
                .append("--------------------------------\n");
            double total = 0.00;
            for (Produto produto : this.produtos) {
                total += produto.preco;
                nota.append(String.format("%-15s R$%-10.2f %-10d\n", produto.nome, produto.preco, 1));
            }
            nota.append("--------------------------------\n");
            if (donation > 0) {
                nota.append(String.format("Donation: R$%.2f\n", donation));
                total += donation;
            }
            nota.append(String.format("Total: R$%.2f\n", total));

            logger.info(clientName + " has made a purchase: " + this.produtos + " at " + DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss").format(LocalDateTime.now()) + " with [ donation: R$" + donation + " ]");

            return nota.toString();
        }
    }

    private static class LoginInfo {
        String name;
        String email;
        String senha;

        LoginInfo(String name, String email, String senha) {
            this.name = name;
            this.email = email;
            this.senha = senha;
        }
    }
}
