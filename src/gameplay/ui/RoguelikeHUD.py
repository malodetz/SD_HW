from engine.ui import HUD
from engine.ui import BoxWidget
from engine.ui import CompoundWidget

from engine.render import RelationalCompoundView

from utils import Graphic

from .SignedBarWidget import SignedBarWidget


class RoguelikeHUD(HUD):
    def __init__(self):
        super().__init__()
        self._compose()

    def _compose(self) -> None:
        boxWidget: BoxWidget = BoxWidget(50, 20)

        infoWidget: CompoundWidget = CompoundWidget(50, 20)
        infoView: RelationalCompoundView = RelationalCompoundView(50, 20)
        infoWidget._widgetView = infoView

        signedBarWidget: SignedBarWidget = SignedBarWidget(20, Graphic.red)
        signedBarWidget.fill(0.55)
        infoView._addSubView(signedBarWidget.view(), (1, 1))

        boxWidget.setItem(infoWidget)
        self._headWidget = boxWidget
