class Produto:
    last_id = 0
    def __init__(self, name="Não informado", price=0):
        Produto.last_id += 1
        self.id = Produto.last_id
        self.name = name
        self.price = price if price >= 0 else 0

        @property
        def id(self):
            return self._id
        @property
        def price(self):
            return self._price

        @price.setters
        def price(self, value):
            if value >= 0:
                self._price = value
            else:
                self.price = 0

        def show(self):
            print(f"Produto: {self.name}"
                  f"Preço:   {self.price}"
                  f"Id:      {self.id}")

        def price_change(self, percent):
            self.price *= (1 + percent / 100)
produtos = []
