from engine.render.View import View
from .CompoundView import CompoundView

from math import ceil


class HistoryEntry:
    xCoord: int
    yCoord: int

    xHeight: int
    yWidth: int

    xHeightOuter: int
    yWidthOuter: int


class RelationalCompoundView(CompoundView):
    _history: dict[View, HistoryEntry]

    def __init__(self, xHeight: int, yWidth: int) -> None:
        super().__init__(xHeight, yWidth)
        self._history = {}

    def addSubView(self, subView: View, coords: tuple[int, int]) -> None:
        super().addSubView(subView, coords)

        xCoord: int
        yCoord: int
        xCoord, yCoord = coords

        entry: HistoryEntry = HistoryEntry()
        entry.xCoord = xCoord
        entry.yCoord = yCoord

        entry.xHeight = subView.xHeight
        entry.yWidth = subView.yWidth

        entry.xHeightOuter = self.xHeight
        entry.yWidthOuter = self.yWidth

        self._history[subView] = entry

    def setResolution(self, xHeight: int, yWidth: int) -> None:
        for subView, _ in self.subViews.items():
            entry: HistoryEntry = self._history.get(subView)

            xScaleFactor: float = xHeight / entry.xHeightOuter
            yScaleFactor: float = yWidth / entry.yWidthOuter

            # Set up new coordinates.
            xCoordSubViewNew: int = ceil(entry.xCoord * xScaleFactor)
            yCoordSubViewNew: int = ceil(entry.yCoord * yScaleFactor)
            self.subViews[subView] = (xCoordSubViewNew, yCoordSubViewNew)

            # Choose new resolution.
            xHeightSubViewNew: int = ceil(entry.xHeight * xScaleFactor)
            yWidthSubViewNew: int = ceil(entry.yWidth * yScaleFactor)

            if entry.xCoord + entry.xHeight <= entry.xHeightOuter:
                xHeightSubViewNew = min(xHeightSubViewNew, xHeight - xCoordSubViewNew)

            if entry.yCoord + entry.yWidth <= entry.yWidthOuter:
                yWidthSubViewNew = min(yWidthSubViewNew, yWidth - yCoordSubViewNew)

            if subView.xHeight != xHeightSubViewNew or subView.yWidth != yWidthSubViewNew:
                subView.setResolution(xHeightSubViewNew, yWidthSubViewNew)

        super().setResolution(xHeight, yWidth)
