from .CompoundWidget import CompoundWidget
from engine.render import RelationalCompoundView

class RelationalCompoundWidget(CompoundWidget):
    def __init__(self, xHeight: int, yWidth: int) -> None:
        super().__init__(xHeight, yWidth)
        self._widgetView = RelationalCompoundView(xHeight, yWidth)
