import os
import curses
import time
import json

def clear_screen():
    os.system('cls' if os.name == 'nt' else 'clear')

def center_text(win, text, y):
    x = (curses.COLS - len(text)) // 2
    win.addstr(y, x, text)

def display_start_screen(stdscr):
    stdscr.clear()
    center_text(stdscr, "Welcome to the Lk10 e-Manager", 5)
    center_text(stdscr, "=============================", 6)
    center_text(stdscr, "[1] Login", 7)
    center_text(stdscr, "[2] Register", 8)
    center_text(stdscr, "[q] Quit", 9)
    center_text(stdscr, "=============================", 10)
    stdscr.refresh()

def save_login_info(name, email, senha):
    login_data = load_all_login_info()
    login_data.append({'name': name, 'email': email, 'senha': senha})
    with open('login_info.json', 'w') as file:
        json.dump(login_data, file)

def load_all_login_info():
    if os.path.exists('login_info.json'):
        with open('login_info.json', 'r') as file:
            return json.load(file)
    return []

def log_debug_info(message):
    with open('debug_log.txt', 'a') as log_file:
        log_file.write(message + "\n")

class Cliente:
    def __init__(self, nome, email, senha):
        self.nome = nome
        self.email = email
        self.senha = senha
        self.pedido = Pedido()

    def selecionar_produto(self, produto, quantidade):
        self.pedido.adicionar_produto(produto, quantidade)

class Produto:
    def __init__(self, nome, preco):
        self.nome = nome
        self.preco = preco + .00

class Pedido:
    def __init__(self):
        self.produtos = []

    def adicionar_produto(self, produto, quantidade):
        self.produtos.append((produto, quantidade))

    def gerar_nota_fiscal(self):
        nota = "NOTA FISCAL\n"
        nota += "--------------------------------\n"
        nota += "{:<15} {:<10} {:<10}\n".format("Produto", "Preço", "Quantidade")
        nota += "--------------------------------\n"
        total = 0.00
        for produto, quantidade in self.produtos:
            total += produto.preco * quantidade
            nota += "{:<15} R${:<10} {:<10}\n".format(produto.nome, produto.preco, quantidade)
        nota += "-------------------------\n"
        nota += f"Total: R${total}\n"
        return nota

def animate_login_name(stdscr, name):
    stdscr.clear()
    stdscr.addstr(0, 0, "Logging in as:")
    stdscr.refresh()
    for i in range(len(name)):
        time.sleep(0.20)
        stdscr.addstr(1, i, name[i])
        stdscr.refresh()
    time.sleep(0.6)
    stdscr.addstr(2, 0, "Login successful!")
    stdscr.refresh()
    time.sleep(0.5)

def animate_nota_fiscal(stdscr, nota_fiscal):
    stdscr.clear()
    lines = nota_fiscal.split("\n")
    for i, line in enumerate(lines):
        stdscr.addstr(i, 0, line)
        stdscr.refresh()
        time.sleep(0.3)
    stdscr.addstr(len(lines), 0, "Press any key to exit...")
    stdscr.refresh()
    stdscr.getch()

def main_interaction(stdscr, cliente):
    stdscr.clear()
    stdscr.addstr(0, 0, f"Hello {cliente.nome}!")
    time.sleep(0.3)
    stdscr.refresh()
    stdscr.addstr(1, 0, "Here are some product examples:")
    time.sleep(0.3)
    stdscr.refresh()
    products = [
        Produto("Laptop", 1500),
        Produto("Smartphone", 800),
        Produto("Headphones", 200),
        Produto("Monitor", 300),
        Produto("Keyboard", 100)
    ]

    for i, product in enumerate(products):
        time.sleep(0.5)
        stdscr.addstr(3 + i, 0, f"{i + 1}. {product.nome} - ${product.preco}")
        stdscr.refresh()

    stdscr.addstr(9, 0, "Press 'f' to finalize:")
    stdscr.refresh()
    
    while True:
        key = stdscr.getch()
        if chr(key) == 'f':
            break
        elif chr(key).isdigit() and 1 <= int(chr(key)) <= len(products):
            selected_product = products[int(chr(key)) - 1]
            stdscr.addstr(10, 0, f"Selected {selected_product.nome}. Enter quantity: ")
            stdscr.refresh()
            curses.echo()
            quantity = int(stdscr.getstr(10, len(f"Selected {selected_product.nome}. Enter quantity: "), 5).decode('utf-8'))
            curses.noecho()
            cliente.selecionar_produto(selected_product, quantity)
            stdscr.addstr(11, 0, f"Added {quantity}x {selected_product.nome} to your order.")
            stdscr.refresh()
            time.sleep(1)
            stdscr.addstr(11, 0, " " * 50)
            stdscr.addstr(10, 0, " " * 50)
            stdscr.addstr(9, 0, "Select another product number or press 'f' to finalize:")
            stdscr.refresh()

    nota_fiscal = cliente.pedido.gerar_nota_fiscal()
    animate_nota_fiscal(stdscr, nota_fiscal)

def login_action(stdscr):
    stdscr.clear()
    stdscr.refresh()
    login_data = load_all_login_info()

    if not login_data:
        stdscr.addstr(0, 0, "No registered users found. Please register first.")
        stdscr.refresh()
        stdscr.getch()
        return

    stdscr.addstr(0, 0, "Select a user to login:")
    for i, record in enumerate(login_data):
        stdscr.addstr(1 + i, 0, f"[{i + 1}] {record['name']}")
    stdscr.refresh()

    while True:
        key = stdscr.getch()
        if chr(key).isdigit() and 1 <= int(chr(key)) <= len(login_data):
            selected_user = login_data[int(chr(key)) - 1]
            cliente = Cliente(selected_user['name'], selected_user['email'], selected_user['senha'])
            animate_login_name(stdscr, cliente.nome)
            main_interaction(stdscr, cliente)
            break
        stdscr.addstr(len(login_data) + 1, 0, "Invalid option. Please select a valid user number.")
        stdscr.refresh()

def register_action(stdscr):
    stdscr.clear()
    stdscr.refresh()
    name = get_input(stdscr, "Digite seu nome: ", 0, 0)
    email = get_input(stdscr, "Digite seu email: ", 1, 0)
    senha = get_input(stdscr, "Digite sua senha: ", 2, 0)
    save_login_info(name, email, senha)
    stdscr.clear()
    stdscr.addstr(0, 0, "Registration successful. You can now log in.")
    stdscr.refresh()
    stdscr.getch()

def get_input(stdscr, prompt, y, x):
    stdscr.addstr(y, x, prompt)
    stdscr.refresh()
    curses.echo()
    input = stdscr.getstr(y, x + len(prompt), 20).decode('utf-8')
    curses.noecho()
    return input

def main(stdscr):
    curses.curs_set(0)
    stdscr.nodelay(0)

    log_debug_info("Application started")

    actions = {
        '1': login_action,
        '2': register_action,
        'q': lambda stdscr: exit()
    }

    while True:
        display_start_screen(stdscr)
        key = stdscr.getch()
        action = actions.get(chr(key), None)

        if action:
            action(stdscr)
        else:
            stdscr.addstr(11, 0, "Invalid option. Please select [1], [2], or [q].")
            stdscr.refresh()

if __name__ == "__main__":
    curses.wrapper(main)
