from .RenderedView import RenderedView

class RenderedViewBuilder:
  _content: list[list[str]]
  
  _xHeight: int
  _yWidth: int
  
  def __init__(self, xHeight: int, yWidth: int):
    self._content = []
    self._xHeight = xHeight
    self._yWidth = yWidth

    for _ in range(xHeight):
      self._content.append(["."] * yWidth)

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
