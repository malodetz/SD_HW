from engine.render import View


class Widget:
    _widgetView: View
    _isVisible: bool

    def __init__(self) -> None:
        self._widgetView = None
        self._isVisible = True

    def onClick(self) -> None:
        pass

    def setVisibility(self, visible: bool) -> None:
        self._isVisible = visible

    def view(self) -> View:
        return self._widgetView
