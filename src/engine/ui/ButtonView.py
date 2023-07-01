from engine.render import View


class ButtonView(View):
    _button: 'ButtonWidget'
    def __init__(self, xHeight: int, yWidth: int, button: 'ButtonWidget'):
        super().__init__(xHeight, yWidth)
        self._button = button

    def onClick(self, xCoord: int, yCoord: int):
        self._button.onClick()

from .ButtonWidget import ButtonWidget
