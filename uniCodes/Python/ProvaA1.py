import os

def clear_screen():
    os.system('cls' if os.name == 'nt' else 'clear')

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
        self.preco = preco

class Pedido:
    def __init__(self):
        self.produtos = []

    def adicionar_produto(self, produto, quantidade):
        self.produtos.append((produto, quantidade))

    def gerar_nota_fiscal(self):
        nota = "\nNOTA FISCAL\n"
        nota += "-------------------------\n"
        nota += "Produto\t\tPreço\n"
        nota += "-------------------------\n"
        for produto, quantidade in self.produtos:
            nota += f"{produto.nome}\t\t{produto.preco} x {quantidade}\n"
        nota += "-------------------------\n"
        return nota

produto_exemplo = Produto(nome="Iphone", preco=5400.00)

clear_screen()

nome = input("Digite seu nome: ")
email = input("Digite seu email: ")
senha = input("Digite sua senha: ")

clear_screen()

cliente = Cliente(nome, email, senha)

print(f" - Cadastro realizado com sucesso para {cliente.nome}.")
cliente.selecionar_produto(produto_exemplo, 1)
print(f" - Produto {produto_exemplo.nome} adicionado ao pedido.")

nota_fiscal = cliente.pedido.gerar_nota_fiscal()
print(nota_fiscal if nota_fiscal else "Nenhum pedido foi feito.")