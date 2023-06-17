from engine.ui import HUD
from engine.ui import BoxWidget

class RoguelikeHUD(HUD):
  def __init__(self):
    super().__init__()
    self._compose()
  
  def _compose(self) -> None:
    boxWidget: BoxWidget = BoxWidget(50, 20)
    self._headWidget = boxWidget
