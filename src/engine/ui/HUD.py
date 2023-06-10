from .Widget import Widget
from engine.render import View

class HUD:
  _HUDView: View

  def __init__(self) -> None:
    self._HUDView = None

  def view(self) -> View:
    return self._HUDView
