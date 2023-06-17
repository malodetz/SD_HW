from .Widget import Widget

from engine.render import CompoundView 

class CompoundWidget(Widget):
  _child: dict[Widget, tuple[int, int]]

  def __init__(self) -> None:
    super().__init__()
    self._child = {}
    self._widgetView = CompoundView()
