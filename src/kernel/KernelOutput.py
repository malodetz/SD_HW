import curses

from render import RenderedView

class KernelOutput:
  _screen: 'curses._CursesWindow'

  def __init__(self, screen: 'curses._CursesWindow') -> None:
    self._screen = screen

  def show(self, renderedView: RenderedView) -> None:
    for x in range(renderedView.xHeight):
      for y in range(renderedView.yWidth):
        self._screen.addch(x, y, renderedView.at(x, y))
