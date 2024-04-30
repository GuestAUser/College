class Animal: #Início polimorfismo;
    def speak(self):
        pass

class Dog(Animal):
    def speak(self):
        return "Woof Woof!" #Implementação para o ( Cachorro );

class Cat(Animal):
    def speak(self):
        return "\n\nMeow!" #Implementação para o  ( Gato     );

class Cow(Animal):
    def speak(self):
        return "\n\nMooo!" #Implementação para a  ( Vaca     );

def animal_voice(animal):
    return animal.speak()  #Função que chama as implementações speak(self[Cow,Cat,Dog]);

dog = Dog()
cat = Cat()
cow = Cow()

print(animal_voice(dog))
print(animal_voice(cat))
print(animal_voice(cow))
