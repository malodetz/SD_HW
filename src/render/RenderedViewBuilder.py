from .RenderedView import RenderedView

class RenderedViewBuilder:
  _content: list[list[str]]

  def __init__(self, xHeight: int, yWidth: int):
    self._content = []
    for _ in range(xHeight):
      self._content.append(["."] * yWidth)

  def nest(self, x: int, y: int, viewToNest: RenderedView) -> 'RenderedViewBuilder':
    for xView in range (viewToNest.xHeight):
      for yView in range (viewToNest.yWidth):
        self._content[x + xView, y + yView] = viewToNest.at(xView, yView)
    return self

  def build(self) -> RenderedView:
    return RenderedView(self._content)
