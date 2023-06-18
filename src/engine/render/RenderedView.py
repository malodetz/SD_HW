from .View import View
from .RenderedUnit import RenderedUnit

class RenderedView(View):
  _content: dict[tuple[int, int], RenderedUnit]

  def __init__(self, xHeight: int, yWidth: int, content: dict[tuple[int, int], RenderedUnit]):
    super().__init__(xHeight, yWidth)
    self._content = content

  def _contains(self, x: int, y: int) -> bool:
    return x in self._content and y in self._content[x]

  def at(self, x: int, y: int) -> RenderedUnit:
    if not self._contains(x, y):
      return None
    return self._content[x][y]

  def setResolution(self, xHeight: int, yWidth: int) -> None:
    pass