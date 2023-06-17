from .Widget import Widget
from .TextView import TextView

from utils import Graphic

class TextWidget(Widget):
  _text: str
  
  def __init__(self, xHeight: int, yWidth: int, color: int = Graphic.white) -> None:
    super().__init__()
    self._widgetView = TextView(xHeight, yWidth, color)
    self.setText("")

  def setText(self, text: str) -> None:
    self._text = text
    self._widgetView.setText(text)
