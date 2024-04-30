class Tool:
    def action(self):
        pass

class Hammer(Tool):
    def action(self):
        return " * You have used the Hammer!"

class Screwdriver(Tool):
    def action(self):
        return " * You have used the Screwdriver!"

class Wrench(Tool):
    def action(self):
        return " * You have used the Wrench!"

class Toolbox:
    tools = {
        "hammer": Hammer,
        "screwdriver": Screwdriver,
        "wrench": Wrench
    }

    @staticmethod
    def get_tool(tool_type):
        return Toolbox.tools.get(tool_type.lower())

tool_type = input("What tool do you want to use? (hammer/screwdriver/wrench): ").lower()
tool_class = Toolbox.get_tool(tool_type)

if tool_class:
    tool = tool_class()
    print(tool.action())
else:
    print("Sorry, tool not available.")
