from .Widget import Widget

class ButtonWidget(Widget):
    def __init__(self, xHeight: int, yWidth: int):
        super().__init__()
        self._widgetView = ButtonView(xHeight, yWidth, self)

    def onClick(self):
        print("onClick")
        pass

from .ButtonView import ButtonView
