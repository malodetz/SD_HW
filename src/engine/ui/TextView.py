from engine.render import CompoundView

from engine.render import RenderedView
from engine.render import RenderedUnit

class TextView(CompoundView):
  _color: int
  _text: str

  def __init__(self, xHeight: int, yWidth: int, color: int) -> CompoundView:
    super().__init__(xHeight, yWidth)
    self._color = color
    self.setText("")

  def setText(self, text: str) -> None:
    self.subViews = {}

    self._text = text
    content: dict[tuple[int, int], RenderedUnit] = {}
    for i in range(len(text)):
      xPos: int = i // self.yWidth
      yPos: int = i % self.yWidth
      if xPos < self.xHeight and yPos < self.yWidth:
        content[(xPos, yPos)] = RenderedUnit(text[i], self._color)    
    self._addSubView(RenderedView(self.xHeight, self.yWidth, content), (0, 0))

  def setResolution(self, xHeight: int, yWidth: int) -> None:
    super().setResolution(xHeight, yWidth)
    self.setText(self._text)
