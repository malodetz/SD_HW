from engine.ui import CompoundWidget
from engine.ui import BarWidget
from engine.ui import TextWidget

from engine.render import CompoundView

class SignedBarWidget(CompoundWidget):
  _bar: BarWidget
  _text: TextWidget
  
  def __init__(self) -> None:
    super().__init__()
    self._widgetView = CompoundView()

  def fill(self, _filledPart: float) -> None:
    pass