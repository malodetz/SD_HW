from engine.ui import HUD
from engine.ui import BoxWidget

from utils import Graphic

from .SignedBarWidget import SignedBarWidget

class RoguelikeHUD(HUD):
  def __init__(self):
    super().__init__()
    self._compose()
  
  def _compose(self) -> None:
    boxWidget: BoxWidget = BoxWidget(50, 20)
    infoWidget: SignedBarWidget = SignedBarWidget(15, Graphic.red)
    boxWidget.setItem(infoWidget)
    self._headWidget = boxWidget
    