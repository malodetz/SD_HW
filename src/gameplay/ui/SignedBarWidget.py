from engine.ui import CompoundWidget
from engine.ui import BarWidget
from engine.ui import TextWidget

from engine.render import RelationalCompoundView

class SignedBarWidget(CompoundWidget):
  _bar: BarWidget
  _text: TextWidget
  
  def __init__(self, yWidth: int, color: int) -> None:
    super().__init__(1, yWidth)
    self._bar = BarWidget(yWidth - 8, color)
    self._text = TextWidget(1, 7, color)

    self._widgetView = RelationalCompoundView(1, yWidth)
    self._widgetView._addSubView(self._bar.view(), (0, 0))
    self._widgetView._addSubView(self._text.view(), (0, yWidth - 7))

  def fill(self, filledPart: float) -> None:
    self._bar.fill(filledPart)
