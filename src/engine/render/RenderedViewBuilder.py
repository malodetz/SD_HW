from .RenderedView import RenderedView
from .RenderedUnit import RenderedUnit


class RenderedViewBuilder:
    _content: dict[tuple[int, int], list[RenderedUnit]]

    _xHeight: int
    _yWidth: int

    def __init__(self, xHeight: int, yWidth: int):
        self._content = {}
        self._xHeight = xHeight
        self._yWidth = yWidth

    def nest(self, x: int, y: int, viewToNest: RenderedView) -> "RenderedViewBuilder":
        for (xView, yView), unit in viewToNest._content.items():
            if x + xView < 0 or x + xView >= self._xHeight or y + yView < 0 or y + yView >= self._yWidth:
                continue
            self._content[(x + xView, y + yView)] = unit
        return self

    def build(self) -> RenderedView:
        return RenderedView(self._xHeight, self._yWidth, self._content)
