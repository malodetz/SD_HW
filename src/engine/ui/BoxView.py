from engine.render import View
from engine.render import CompoundView
from engine.render import RenderedView
from engine.render import RenderedUnit

from utils import Graphic

class BoxView(CompoundView):
  _lastItemView: View
  
  def __init__(self, xHeight: int, yWidth: int) -> None:
    super().__init__(xHeight, yWidth)
    self._lastItemView = None
    self._compose()

  def _compose(self) -> None:
    self.subViews = {}

    viewContent: dict[tuple[int, int], RenderedUnit] = {}
    
    viewContent[(0, 0)] = RenderedUnit(Graphic.ulcorner)
    viewContent[(0, self.yWidth - 1)] = RenderedUnit(Graphic.urcorner)
    viewContent[(self.xHeight - 1, 0)] = RenderedUnit(Graphic.llcorner)
    viewContent[(self.xHeight - 1, self.yWidth - 1)] = RenderedUnit(Graphic.lrcorner)

    for x in range(1, self.xHeight - 1):
      viewContent[(x, 0)] = RenderedUnit(Graphic.vline)
      viewContent[(x, self.yWidth - 1)] = RenderedUnit(Graphic.vline)

    for y in range(1, self.yWidth - 1):
      viewContent[(0, y)] = RenderedUnit(Graphic.hline)
      viewContent[(self.xHeight - 1, y)] = RenderedUnit(Graphic.hline)

    self._addSubView(RenderedView(self.xHeight, self.yWidth, viewContent), (0, 0))

  def setItem(self, view: View) -> None:
    self._compose()
    self._lastItemView = view
    view.setResolution(self.xHeight - 2, self.yWidth - 2)
    self._addSubView(view, (1, 1))

  def setResolution(self, xHeight: int, yWidth: int) -> None:
    super().setResolution(xHeight, yWidth)
    self._compose()
    if not self._lastItemView is None:
      self.setItem(self._lastItemView)
