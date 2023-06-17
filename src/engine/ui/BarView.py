from engine.render import CompoundView
from engine.render import RenderedView
from engine.render import RenderedUnit

from utils import Graphic

class BarView(CompoundView):
  _filledPart: float
  _filledColor: int

  def __init__(self, yWidth: int, filledColor: int) -> None:
    super().__init__(1, yWidth)
    self._filledPart = 0.0
    self._filledColor = filledColor

  def fill(self, filledPart: float) -> None:
    self._filledPart = filledPart
    self._compose()

  def _compose(self) -> None:
    self.subViews = {}

    filled: int = self._filledPart * self.yWidth

    currentViewContent: list[list[RenderedUnit]] = [[]]
    for y in range(self.yWidth):
      color: int = Graphic.white if y >= filled else self._filledColor
      currentViewContent[0].append(RenderedUnit(Graphic.hline, color))

    self._addSubView(RenderedView(currentViewContent), (0, 0))

  def setResolution(self, _: int, yWidth: int) -> None:
    super().setResolution(1, yWidth)
    self._compose()
