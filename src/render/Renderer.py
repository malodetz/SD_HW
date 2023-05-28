from .View import View
import curses

class Renderer:
  """Class for rendering and displaying views placed on the screen.  

  """

  _screen: 'curses._CursesWindow'
  _views: dict

  def __init__(self, screen: 'curses._CursesWindow'):
    self._screen = screen

  def registerView(self, view: View):
    """
    TODO:
    """
    self._views[""] = view

  def _renderAt(self, xCoord: int, yCoord: int, xHeight: int, yWidth: int, view: View) -> None:
    view.render()
    for x in range(xCoord, xCoord + xHeight):
      for y in range(yCoord, yCoord + yWidth):
        self._screen.addch(x, y, renderedView.at(x, y))

  def render(self) -> None:
    pass