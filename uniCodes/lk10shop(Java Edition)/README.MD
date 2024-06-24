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
