from .View import View
from .RenderedUnit import RenderedUnit

class RenderedView(View):
  _content: list[list[RenderedUnit]]

  def __init__(self, _content: list[list[RenderedUnit]]):
    xHeight: int = len(_content)
    yWidth: int = len(_content[0]) if xHeight > 0 else 0

    super().__init__(xHeight, yWidth)
    self._content = _content

  def at(self, x: int, y: int) -> RenderedUnit:
    return self._content[x][y]

  def setResolution(self, xHeight: int, yWidth: int) -> None:
    pass