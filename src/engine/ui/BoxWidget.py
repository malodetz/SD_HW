from .Widget import Widget

from engine.render import RenderedView
from engine.render import CompoundView
from .BoxView import BoxView

class BoxWidget(Widget):
  _item: Widget

  def __init__(self, xHeight: int, yWidth: int) -> None:
    super().__init__()
    self._widgetView = BoxView(xHeight, yWidth)

  def setItem(self, item: Widget) -> None:
    self._item = item

  def view(self) -> None:
    return self._widgetView
