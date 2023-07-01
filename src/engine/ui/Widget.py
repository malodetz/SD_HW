from engine.render import View


class Widget:
    _widgetView: View

    def __init__(self) -> None:
        self._widgetView = None

    def onClick(self) -> None:
        pass

    def onHovered(self) -> None:
        pass

    def view(self) -> View:
        return self._widgetView
