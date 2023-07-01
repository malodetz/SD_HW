from engine.ui import CompoundWidget
from engine.ui import BarWidget
from engine.ui import TextWidget

from engine.render import RelationalCompoundView


class SignedBarWidget(CompoundWidget):
    _bar: BarWidget
    _text: TextWidget

    _maxBound: int

    def __init__(self, yWidth: int, color: int) -> None:
        super().__init__(1, yWidth)
        self._bar = BarWidget(yWidth - 8, color)
        self._text = TextWidget(1, 7, color)
        self.setMaxBound()

        self._widgetView = RelationalCompoundView(1, yWidth)
        self._widgetView.addSubView(self._bar.view(), (0, 0))
        self._widgetView.addSubView(self._text.view(), (0, yWidth - 7))

    def setMaxBound(self, maxBound: int = 100) -> None:
        self._maxBound = maxBound

    def fill(self, filledPart: float) -> None:
        self._bar.fill(filledPart)
        filled: int = int(filledPart * self._maxBound)

        text: str = "{}/{}".format(filled, self._maxBound)
        self._widgetView.setResolution(1, self._bar._widgetView.yWidth + len(text))

        self._text.setText(text)
