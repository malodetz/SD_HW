from .Widget import Widget

class HUD:
  _widget = None

  def __init__(self, widget: Widget):
    self._widget = widget