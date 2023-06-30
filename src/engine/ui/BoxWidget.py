from .Widget import Widget
from .BoxView import BoxView


class BoxWidget(Widget):
    _item: Widget

    def __init__(self, xHeight: int, yWidth: int) -> None:
        super().__init__()
        self._widgetView = BoxView(xHeight, yWidth)

    def setItem(self, item: Widget) -> None:
        self._item = item
        self._widgetView.setItem(item.view())

    def view(self) -> None:
        return self._widgetView
