from engine.render import View
from engine.render import CompoundView
from engine.render import RenderedView
from engine.render import RenderedUnit

from utils import Graphic

class BoxView(CompoundView):
  def __init__(self, xHeight: int, yWidth: int) -> None:
    super().__init__(xHeight, yWidth)
    self._compose()

  def _compose(self) -> None:
    self.subViews = {}

    viewContent: list[list[RenderedUnit]] = []
    for x in range(self.xHeight):
      viewContent.append([])
      for y in range(self.yWidth):
        viewContent[x].append(RenderedUnit(" "))
    
    viewContent[0][0] = RenderedUnit(Graphic.ulcorner)
    viewContent[0][self.yWidth - 1] = RenderedUnit(Graphic.urcorner)
    viewContent[self.xHeight - 1][0] = RenderedUnit(Graphic.llcorner)
    viewContent[self.xHeight - 1][self.yWidth - 1] = RenderedUnit(Graphic.lrcorner)

    for x in range(1, self.xHeight - 1):
      viewContent[x][0] = RenderedUnit(Graphic.vline)
      viewContent[x][self.yWidth - 1] = RenderedUnit(Graphic.vline)

    for y in range(1, self.yWidth - 1):
      viewContent[0][y] = RenderedUnit(Graphic.hline)
      viewContent[self.xHeight - 1][y] = RenderedUnit(Graphic.hline)

    self._addSubView(RenderedView(viewContent), (0, 0))

  def setItem(self, view: View) -> None:
    self._compose()
    self._addSubView(view, (1, 1))

  def setResolution(self, xHeight: int, yWidth: int) -> None:
    super().setResolution(xHeight, yWidth)
    self._compose()
