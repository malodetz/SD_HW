import curses

from engine.render import RenderedView


class KernelOutput:
    _screen: "curses._CursesWindow"

    def __init__(self, screen: "curses._CursesWindow") -> None:
        self._screen = screen

    def _inScreen(self, xCoord, yCoord) -> bool:
        if xCoord < 0 or yCoord < 0:
            return False
        xCoordMaxScreen: int
        yCoordMaxScreen: int
        xCoordMaxScreen, yCoordMaxScreen = self._screen.getmaxyx()
        return xCoord < xCoordMaxScreen and yCoord < yCoordMaxScreen

    def show(self, renderedView: RenderedView) -> None:
        self._screen.clear()
        xCoordMaxScreen: int
        yCoordMaxScreen: int
        xCoordMaxScreen, yCoordMaxScreen = self._screen.getmaxyx()

        for (xCoord, yCoord), unit in renderedView._content.items():
            if self._inScreen(xCoord, yCoord):
                color: int = unit.color
                symbol: str = unit.symbol
                # This is a problem inside curses library, as it can not
                # add a character in the bottom right corner.
                if xCoord + 1 == xCoordMaxScreen and yCoord + 1 == yCoordMaxScreen:
                    self._screen.insstr(xCoord, yCoord, symbol, curses.color_pair(color))
                else:
                    self._screen.addstr(xCoord, yCoord, symbol, curses.color_pair(color))
