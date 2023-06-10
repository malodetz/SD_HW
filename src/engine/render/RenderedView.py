from .View import View

class RenderedView(View):
  _content: list[list[str]]

  def __init__(self, _content: list[list[str]]):
    xHeight: int = len(_content)
    yWidth: int = len(_content[0]) if xHeight > 0 else 0

    super().__init__(xHeight, yWidth)
    self._content = _content

  def at(self, x: int, y: int) -> str:
    return self._content[x][y]
