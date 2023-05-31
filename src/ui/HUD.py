from .Widget import Widget
from render import View

class HUD:
  _HUDView: View

  def __init__(self) -> None:
    _HUDView = None

  def getView(self) -> View:
    return self._HUDView