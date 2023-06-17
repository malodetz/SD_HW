from engine.render import View 
from engine.render import RenderedView
from engine.render import RenderedUnit

from utils import Graphic

class BarView(View):
  _currentView: RenderedView
  
  def __init__(self, yWidth: int) -> None:
    super().__init__(1, yWidth)

  def _composeView(self) -> None:
    currentViewContent: list[list[RenderedUnit]] = [[]]
    for _ in self.yWidth:
      currentViewContent.append(RenderedUnit(Graphic.hline))
    self._currentView = RenderedView(currentViewContent)
