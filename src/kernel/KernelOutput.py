import curses

from render import RenderedView

class KernelOutput:
  _screen: 'curses._CursesWindow'

  def __init__(self, screen: 'curses._CursesWindow') -> None:
    self._screen = screen

  def _inScreen(self, xCoord, yCoord) -> bool:
    if xCoord < 0 or yCoord < 0:
      return False
    xCoordMaxScreen, yCoordMaxScreen = self._screen.getmaxyx()
    return xCoord < xCoordMaxScreen and yCoord < yCoordMaxScreen


  def show(self, renderedView: RenderedView) -> None:    
    for xCoord in range(renderedView.xHeight):
      for yCoord in range(renderedView.yWidth):
        if (self._inScreen(xCoord, yCoord)):
          self._screen.addch(xCoord, yCoord, renderedView.at(xCoord, yCoord))
