# Atividade avaliativa feita em aula, código feito em um curto período e por isso talvez
# esteja um pouco mal implementado.
from os import system
import platform
import shutil

class Fatura:
    def __init__(self, item_name, item_description, item_buy_count, item_price=0):
        self.item_name = item_name
        self.item_description = item_description
        # Não deixar os números ficarem negativos (Constatado na atividade);
        self.item_buy_count = int(item_buy_count) if int(item_buy_count) > 0 else 0
        self.item_price = int(item_price) if int(item_price) >= 0 else 0.0

    @property
    def item_name(self):
        return self._item_name

    @item_name.setter
    def item_name(self, item_name):
        self._item_name = item_name

    @property
    def item_description(self):
        return self._item_description

    @item_description.setter
    def item_description(self, item_description):
        self._item_description = item_description

    @property
    def item_buy_count(self):
        return self._item_buy_count

    @item_buy_count.setter
    def item_buy_count(self, item_buy_count):
        self._item_buy_count = item_buy_count

    @property
    def item_price(self):
        return self._item_price

    @item_price.setter
    def item_price(self, item_price):
        self._item_price = item_price

    def calcular_valor_fatura(self):
        return self.item_buy_count * self.item_price

def clear_screen():
    os_name = platform.system()
    if os_name == 'Windows':
        system('cls')
    else:
        system('clear')

terminal_width = shutil.get_terminal_size().columns

clear_screen()

if __name__ == "__main__":
    fatura_name = input("Qual vai ser o objeto comprado? - > ")
    fatura_descriptor = input("Qual vai ser a descricao dele? - > ")
    fatura_quantity = input(f"Qual vai ser a quantidade de {fatura_name} comprado? - > ")
    fatura_price = input(f"Preco do objeto ( {fatura_name} ) - > ")
    fatura = Fatura(fatura_name, fatura_descriptor, fatura_quantity, fatura_price)
    
    clear_screen()
    
    print("=" * terminal_width)
    print(f"Nome do Item: {fatura.item_name}")
    print(f"Descrição do Item: {fatura.item_description}")
    print(f"Quantidade Comprada: {fatura.item_buy_count}")
    print(f"Preço Unitário: {fatura.item_price}")
    print(f"Valor da Fatura: {fatura.calcular_valor_fatura()}")
    print("=" * terminal_width + "\n")
    input()
