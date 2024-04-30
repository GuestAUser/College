class Tool:
    def action(self):
        pass

class Hammer(Tool):
    def action(self):
        return "*You have used the Hammer!"

class Screwdriver(Tool):
    def action(self):
        return "You have used the Screwdriver!"

class Wrench(Tool):
    def action(self):
        return "You have used the Wrench!"

class Toolbox:
    @staticmethod
    def get_tool(tool_type):
        if tool_type == "hammer":
            return Hammer()
        elif tool_type == "screwdriver":
            return Screwdriver()
        elif tool_type == "wrench":
            return Wrench()
        else:
            return None

tool_type = input("What tool do you want to use? (hammer/screwdriver/wrench): ").lower()
tool = Toolbox.get_tool(tool_type)

if tool:
    print(tool.action())
else:
    print("Desculpe, essa ferramenta não está disponível.")
