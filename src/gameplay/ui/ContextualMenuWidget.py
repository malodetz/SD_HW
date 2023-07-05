from engine.ui import Widget
from engine.ui import RelationalCompoundWidget

class ContextualMenuWidget(RelationalCompoundWidget):
    _parent: Widget
    _xHeightOption: int
    _yWidthOption: int

    def __init__(self, xHeightOption: int, yWidthOption: int, parent: Widget) -> None:
        super().__init__(0, 0)
        self._parent = parent

    def _addOption(self, subWidget: Widget) -> None:
        # self.addSubWidget(subWidget)
        pass
