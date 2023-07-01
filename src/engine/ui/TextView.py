from engine.render import RenderedView
from engine.render import RenderedUnit

from .WidgetView import WidgetView
from .Widget import Widget

class TextView(WidgetView):
    _color: int
    _text: str

    def __init__(self, xHeight: int, yWidth: int, color: int, widget: Widget) -> None:
        super().__init__(xHeight, yWidth, widget)
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
        self.addSubView(RenderedView(self.xHeight, self.yWidth, content), (0, 0))

    def setResolution(self, xHeight: int, yWidth: int) -> None:
        super().setResolution(xHeight, yWidth)
        self.setText(self._text)
