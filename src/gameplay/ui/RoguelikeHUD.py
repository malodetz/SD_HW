from engine.ui import HUD
from engine.ui import BoxWidget
from engine.ui import RelationalCompoundWidget

from utils import Graphic

from .SignedBarWidget import SignedBarWidget


class RoguelikeHUD(HUD):
    def __init__(self):
        super().__init__()
        self._compose()

    def _compose(self) -> None:
        boxWidget: BoxWidget = BoxWidget(50, 20)

        infoWidget: RelationalCompoundWidget = RelationalCompoundWidget(50, 20)
        
        signedBarWidget: SignedBarWidget = SignedBarWidget(20, Graphic.red)
        signedBarWidget.fill(0.55)
        infoWidget.addSubWidget(signedBarWidget, (1, 1))

        boxWidget.setItem(infoWidget)
        self._headWidget = boxWidget
