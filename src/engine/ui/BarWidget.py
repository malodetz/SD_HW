from engine.render import View

from .Widget import Widget
from .BarView import BarView

class BarWidget(Widget):
  _filled: float

  def __init__(self, yWidth: int) -> None:
    super().__init__()
    self._filled = 0.0
    self._widgetView = BarView(yWidth)

  def fill(self, filled: float) -> None:
    if filled < 0.0:
      filled = 0.0
    if filled >= 1.0:
      filled = 1.0
    self._filled = filled
