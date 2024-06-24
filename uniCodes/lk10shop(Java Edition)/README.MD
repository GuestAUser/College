Olá este é o meu trabalho da faculdade que foi feito em java, ele serve para implementar uma solução de automatização de pedidos e compras, mantendo um "tracking" de todo os seus processos envolvidos, e  
também deixa tudo registrado para maior segurança.


**Utilização de AI**

Os requisitos ja foram desenhados pelo fato de eu ja ter implementado esta idea antes para outros usos além de acadêmicos mas ainda pedi informações sobre isso para o chatgpt, comparando com os meus própios  
desenhos.

## Meus desenhos dos requisitos:

*Tela Inicial*

    Descrição: A tela inicial
    Importância: Serve como ponto de entrada.

*Ação de Login*

    Descrição: Sistema de login(Básico) de usuários
    Importância: Autentica o usuário, permitindo acesso personalizado e seguro às funcionalidades do sistema.

*Ação de Registro*

    Descrição: Novos usuários podem se registrar-se fornecendo nome, email e senha.
    Importância: Permite novos usuários para o sistema

*Salvar Informações de Login*

    Descrição: Todo login agora fica registrado.
    Importância: Armazena dados dos usuários de maneira persistente.

*Carregar Informações de Login*

    Descrição: Carregar todas as informações de login.
    Importância: Facilita o processo de autenticação, acessando dados armazenados para verificação.

*Animação de Login*

    Descrição: Exibir uma mensagem de boas-vindas após o login.
    Importância: Melhora a experiência do usuário.

*Interação Principal (Seleção de Produtos e Finalização)*

    Descrição: Permitir ao usuário realizar a funcionalidade principal do programa, executar compras.
    Importância: Principal funcionalidade do sistema. **[Essencial]**

*Obter Valor da Doação*

    Descrição: Solicitar ao usuário o valor da doação para o Rio Grande do Sul.
    Importância: Incentiva a responsabilidade social.

*Atualizar Total de Doações*

    Descrição: Registrar e atualizar o valor total das doações em um arquivo de texto.
    Importância: Mantém um registro atualizado das contribuições, que pode ser utilizado para transparência e prestação de contas, ou até para ter-se uma visão melhor da contribuição e progresso.

*Salvar Compras*

    Descrição: Registrar as compras de cada usuário em um arquivo de log.
    Importância: Permite rastrear o histórico de compras dos usuários (útil para suporte e auditoria).

*Animação de Nota Fiscal*

    Descrição: Exibir a nota fiscal gerada após a compra.
    Importância: Deixa o usuário em questão com maior segurança no sistema desenvolvido e com uma animação deixa a experiência geral melhor.  

## Desenho da A.I

Requisitos Funcionais:

    *Login e Registro de Usuário:*
        Os usuários podem fazer login com credenciais previamente registradas.
        Novos usuários podem se registrar fornecendo nome, email e senha.

    *Interface de Início:*
        Exibe opções para Login, Registro e Saída do programa.

    *Seleção de Produtos:*
        Após o login, exibe uma lista de produtos disponíveis com botões para cada um.
        Cada botão permite adicionar produtos ao carrinho especificando a quantidade.

    *Finalização da Compra:*
        Permite ao usuário finalizar a compra após selecionar os produtos desejados.
        Oferece a opção de fazer uma doação adicional, com validação do valor inserido.

    *Geração de Nota Fiscal:*
        Após a finalização da compra, gera uma nota fiscal com detalhes dos produtos comprados.
        Inclui o total da compra e, se aplicável, o valor da doação.

    *Registro de Compras:*
        Salva todas as notas fiscais geradas em arquivos de log separados por cliente.

    *Atualização de Doações Totais:*
        Mantém um registro do total de doações feitas por todos os clientes.

    *Exceções e Mensagens de Erro:*
        Trata casos onde não há usuários registrados ou erros durante o processo de registro/login.
        Validações para entrada de dados como quantidade de produtos e valores de doação.

Funcionalidades Adicionais:

    Registro de Log de Atividades:
        Registra atividades importantes, como login bem-sucedido, compras realizadas e doações feitas.

    Interface Gráfica usando Lanterna:
        Utiliza a biblioteca Lanterna para construir uma interface textual interativa.

Chatgpt: "Este esboço de requisitos funcionais é baseado no código fornecido e cobre as principais funcionalidades necessárias para automatizar o processo de compra em um sistema de e-commerce simplificado."

## A.I Vs Eu

Como podemos ver a a.i mesmo sendo fornecida com o própio código do projeto ainda sim faltou nos quesitos de detalhes como o user-experience e organização dos dados, mas ainda sim acho muito útil pelo fato de automatizar diversos processos complicados e acaba virando um facilitador imenso para muitos processos, mas não todos.

## Descrição Textual do Diagrama de Classes do Sistema

O sistema em si é uma aplicação de gerenciamento de loja virtual online, desenvolvida com uma interface de texto que usa a biblioteca Lanterna e o compilador Gradle (O jeito mais facil de imitar a interface que eu desenvolvi em python). O programa permite que os usuários se registrem, façam login, realizem compras e doações, e gera uma nota fiscal ao final da compra.

### Classes Principais

1. **Lk10Shop**
   - **Descrição**: Classe principal que gerencia a lógica da interface de usuário e interações principais.
   - **Propriedades**:
     - `logger`: Logger utilizado para registrar atividades do sistema.
     - `DONATION_FILE`: Nome do arquivo que armazena o total de doações.
     - `LOGIN_FILE`: Nome do arquivo que armazena informações de login.
   - **Métodos**:
     - `displayStartScreen(MultiWindowTextGUI gui)`: Exibe a tela inicial do programa.
     - `loginAction(MultiWindowTextGUI gui)`: Gerencia o processo de login dos usuários.
     - `registerAction(MultiWindowTextGUI gui)`: Gerencia o processo de registro de novos usuários.
     - `saveLoginInfo(String name, String email, String senha)`: Salva as informações de login de um novo usuário.
     - `loadAllLoginInfo()`: Carrega todas as informações de login dos usuários registrados.
     - `animateLoginName(MultiWindowTextGUI gui, String name)`: Exibe uma animação de boas-vindas após o login.
     - `mainInteraction(MultiWindowTextGUI gui, Cliente cliente)`: Gerencia a interação principal, permitindo que o usuário selecione produtos e finalize a compra.
     - `getDonationAmount(MultiWindowTextGUI gui)`: Solicita ao usuário o valor da doação.
     - `updateDonationTotal(double amount)`: Atualiza o valor total das doações no arquivo de texto.
     - `savePurchase(String clientName, String notaFiscal)`: Salva as compras do usuário em um arquivo de log.
     - `animateNotaFiscal(MultiWindowTextGUI gui, String notaFiscal)`: Exibe a nota fiscal gerada após a compra.
     - `main(String[] args)`: Método principal que inicializa a aplicação.

2. **Cliente**
   - **Descrição**: Representa um cliente da loja (Usuário).
   - **Propriedades**:
     - `nome`: Nome do cliente.
     - `email`: Email do cliente.
     - `senha`: Senha do cliente.
     - `pedido`: Instância da classe Pedido que armazena os produtos selecionados pelo cliente.
   - **Métodos**:
     - `Cliente(String nome, String email, String senha)`: Construtor da classe Cliente.
     - `selecionar_produto(Produto produto, int quantidade)`: Adiciona um produto ao pedido do cliente.

3. **Produto**
   - **Descrição**: Representa um produto disponível na loja.
   - **Propriedades**:
     - `nome`: Nome do produto.
     - `preco`: Preço do produto.
   - **Métodos**:
     - `Produto(String nome, double preco)`: Construtor da classe Produto.

4. **Pedido**
   - **Descrição**: Representa um pedido contendo produtos selecionados por um cliente.
   - **Propriedades**:
     - `produtos`: Lista de produtos que compõem o pedido.
   - **Métodos**:
     - `adicionar_produto(Produto produto, int quantidade)`: Adiciona produtos à lista de produtos do pedido.
     - `gerar_nota_fiscal(String clientName)`: Gera a nota fiscal do pedido.
     - `gerar_nota_fiscal(String clientName, double donation)`: Gera a nota fiscal do pedido, incluindo uma doação.

5. **LoginInfo**
   - **Descrição**: Armazena as informações de login de um usuário.
   - **Propriedades**:
     - `name`: Nome do usuário.
     - `email`: Email do usuário.
     - `senha`: Senha do usuário.
   - **Métodos**:
     - `LoginInfo(String name, String email, String senha)`: Construtor da classe LoginInfo.

### Relacionamentos Entre Classes

- **Lk10Shop** classe principal que coordena a interação entre as outras classes. Ela gerencia o fluxo do programa desde a tela inicial até o login, registro, seleção de produtos, doação e finalização da compra.
- **Cliente** Se instancia após o login ou registro e armazena as informações do usuário e seu pedido atual.
- **Pedido** está associado a **Cliente** e contém os produtos que o cliente selecionou para compra.
- **Produto** é utilizado para representar os itens que podem ser adicionados ao pedido.
- **LoginInfo** é usado para armazenar e recuperar as informações de login dos usuários.

### Fluxo de Interação

1. O programa inicia exibindo a tela inicial, onde o usuário pode escolher entre fazer login, registrar-se ou sair.
2. Se o usuário optar por registrar-se, ele insere seu nome, email e senha, que são salvos em um arquivo JSON.
3. Se o usuário optar por fazer login, ele seleciona um nome de usuário da lista de usuários registrados e, ao ser autenticado, é saudado com uma mensagem de boas-vindas.
4. O usuário pode então selecionar produtos para adicionar ao seu pedido. Cada produto adicionado é registrado no objeto `Pedido` associado ao `Cliente`.
5. O usuário tem a opção de doar um valor para o estado do Rio Grande do Sul. Se fizer uma doação, o valor total das doações é atualizado.
6. Ao finalizar a compra, é gerada uma nota fiscal que pode incluir o valor da doação. Esta nota fiscal é salva em um arquivo de log associado ao cliente.
7. A nota fiscal é exibida para o usuário com uma animação de confirmação.
