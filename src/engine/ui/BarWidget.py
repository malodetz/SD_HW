from engine.render import View

from .Widget import Widget
from .BarView import BarView


class BarWidget(Widget):
    def __init__(self, yWidth: int, filledColor: int) -> None:
        super().__init__()
        self._widgetView = BarView(yWidth, filledColor)

    def fill(self, filledPart: float) -> None:
        if filledPart < 0.0:
            filledPart = 0.0
        if filledPart >= 1.0:
            filledPart = 1.0
        self._widgetView.fill(filledPart)
