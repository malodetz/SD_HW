from .RenderedView import RenderedView
from .RenderedUnit import RenderedUnit

class RenderedViewBuilder:
  _content: list[list[RenderedUnit]]
  
  _xHeight: int
  _yWidth: int
  
  def __init__(self, xHeight: int, yWidth: int):
    self._content = []
    self._xHeight = xHeight
    self._yWidth = yWidth

    for x in range(xHeight):
      self._content.append([])
      for y in range(yWidth):
        self._content[x].append(RenderedUnit("."))

  def nest(self, x: int, y: int, viewToNest: RenderedView) -> 'RenderedViewBuilder':
    for xView in range (viewToNest.xHeight):
      for yView in range (viewToNest.yWidth):
        if x + xView < 0 or x + xView >= self._xHeight or \
            y + yView < 0 or y + yView >= self._yWidth:
          continue

        self._content[x + xView][y + yView] = viewToNest.at(xView, yView)
    return self

  def build(self) -> RenderedView:
    return RenderedView(self._content)
