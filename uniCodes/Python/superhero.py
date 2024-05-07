import os

class Superhero():
    def __init__(self, name, category):
        self.name = name
        self.category = category
    
    def getName(self):
        return self.name
    
    def getCategory(self):
        return self.category
    
class Character():
    def __init__(self, name, real_name):
        self.__name = name
        self.__real_name = real_name
        self.__powers = []
    
    def addSuperpower(self, superpower):
        if len(self.__powers) < 4:
            self.__powers.append(superpower)
        else:
            print("Maximum powers have been reached, you cannot add more.")
    
    def getTotalPower(self):
        return sum(power.category for power in self.__powers)
    
    def getPowers(self):
        return self.__powers
        
class Superhero(Character):
    def __init__(self, name, category):
        super().__init__(name, name)
        self.category = category

class Villain(Character):
    def __init__(self, name, real_name, time_in_prison):
        super().__init__(name, real_name)
        self.__time_in_prison = time_in_prison

class Fight():
    def execute(self, superhero, villain):
        superhero_powers = superhero.getPowers()
        villain_powers = villain.getPowers()
        
        superhero_power = sum(power.category for power in superhero_powers)
        villain_power = sum(power.category for power in villain_powers)
        
        result = 1 if superhero_power > villain_power else 2 if villain_power > superhero_power else 0
        return result
            
class Superpower:
    def __init__(self, name, category):
        self.name = name
        self.category = category

class Game():
    def clear_screen(self):
        os.system('cls' if os.name == 'nt' else 'clear')
    
    def main(self):
        self.clear_screen()
        print("="*80)
        print(" "*20 + "Welcome to the Super vs Villains Game!" + " "*21 + "|")
        print("="*80)
        
        superhero_name = input("Add the Super Hero name: ")
        superhero_category = int(input("Add the Super Hero category: "))        
        superhero = Superhero(superhero_name, superhero_category)
        
        self.clear_screen()
        print("Now add powers for the Super Hero:")
        superhero_powers = self.add_powers()
        for power in superhero_powers:
            superhero.addSuperpower(power)
        
        self.clear_screen()
        villain_name = input("Add the Super Villain name: ")
        villain_real_name = input("Add the Super Villain real name: ")
        time_in_prison = int(input("Add the Super Villain time in prison: "))
        villain = Villain(villain_name, villain_real_name, time_in_prison)
        
        self.clear_screen()
        print("Now add powers for the Super Villain:")
        villain_powers = self.add_powers()
        for power in villain_powers:
            villain.addSuperpower(power)
            
        self.clear_screen()
        fight = Fight()
        
        result_messages = {
            0: "It's a draw!",
            1: f"{superhero_name} wins!",
            2: f"{villain_name} wins!"
        }
        
        result = fight.execute(superhero, villain)
        print(result_messages[result])
        print("-"*80)
        
        print(f"\n{superhero_name}'s Powers:")
        for power in superhero.getPowers():
            print(power.name, power.category)
        print(f"Total Power: {superhero.getTotalPower()}")

        print(f"\n{villain_name}'s Powers:")
        for power in villain.getPowers():
            print(power.name, power.category)
        print(f"Total Power: {villain.getTotalPower()}")
    
    def add_powers(self):
        powers = []
        while True:
            power_name = input("Enter power name ( or type 'done' to finish ): ")
            if power_name.lower() == 'done':
                break
            power_category = int(input(f"Enter category for {power_name}: "))
            powers.append(Superpower(power_name, power_category))
        return powers

game = Game()
game.main()