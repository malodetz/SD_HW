import curses

from engine.render import RenderedView

class KernelOutput:
  _screen: 'curses._CursesWindow'

  def __init__(self, screen: 'curses._CursesWindow') -> None:
    self._screen = screen

  def _inScreen(self, xCoord, yCoord) -> bool:
    if xCoord < 0 or yCoord < 0:
      return False
    xCoordMaxScreen: int
    yCoordMaxScreen: int
    xCoordMaxScreen, yCoordMaxScreen = self._screen.getmaxyx()
    return xCoord < xCoordMaxScreen and yCoord < yCoordMaxScreen


  def show(self, renderedView: RenderedView) -> None:
    self._screen.clear()
    for xCoord in range(renderedView.xHeight):
      for yCoord in range(renderedView.yWidth):
        if (self._inScreen(xCoord, yCoord)):
          color: int = renderedView.at(xCoord, yCoord).color
          symbol: str = renderedView.at(xCoord, yCoord).symbol
          self._screen.insstr(xCoord, yCoord, symbol, curses.color_pair(color))
