from engine.render import CompoundView
from .Widget import Widget

class WidgetView(CompoundView):
    _widget: Widget
    
    def __init__(self, xHeight: int, yHeight: int, widget: Widget):
        super().__init__(xHeight, yHeight)
        self._widget = widget

    def onClick(self, xCoord: int, yCoord: int) -> None:
        self._widget.onClick()
        super().onClick(xCoord, yCoord)
