from os import system
import platform
import shutil

class Produto:
    last_id = 0

    def __init__(self, name="Not Informed", price=0):
        Produto.last_id += 1
        self._id = "databaseObject." + str(Produto.last_id)
        self.name = name
        self.price = price if price >= 0 else 0

    @property
    def id(self):
        return self._id

    @property
    def price(self):
        return self._price

    @price.setter
    def price(self, value):
        if value >= 0:
            self._price = value
        else:
            self._price = 0

    def show(self):
        print(f"Produto: {self.name}\n"
              f"Preço:   {self.price:.2f}\n"
              f"Id:      {self.id}\n")

    def price_change(self, percent):
        self.price *= (1 + percent / 100)

def clear_screen():
    os_name = platform.system()
    if os_name == 'Windows':
        system('cls')
    else:
        system('clear')

clear_screen()
products = []
for i in range(2):
    name = input(f"Type the product {Produto.last_id+1} name: ")
    price = float(input("Type the product price: "))
    products.append(Produto(name, price))
    print("\n")

terminal_width = shutil.get_terminal_size().columns
percent = float(input("Readjustment percent: "))

clear_screen()
print("=" * terminal_width + "\n")

for product in products:
    product.price_change(percent)
    product.show()

print(f"Last id generated: databaseObject.{Produto.last_id}\n")

print("=" * terminal_width + "\n")
