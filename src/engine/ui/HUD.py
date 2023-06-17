from .Widget import Widget
from engine.render import View

class HUD:
  _headWidget: Widget

  def __init__(self) -> None:
    pass

  def view(self) -> View:
    return self._headWidget.view()
