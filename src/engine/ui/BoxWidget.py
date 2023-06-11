from .Widget import Widget

class BoxWidget(Widget):
  _item: Widget

  def __init__(self) -> None:
    super().__init__()
    self._item = None
    self.setView()

  def setItem(self, item: Widget) -> None:
    self._item = item
