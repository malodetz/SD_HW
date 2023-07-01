from .View import View


class CompoundView(View):
    subViews: dict[View, tuple[int, int]]

    def __init__(self, xHeight: int, yWidth: int) -> None:
        super().__init__(xHeight, yWidth)
        self.subViews = {}

    def addSubView(self, subView: View, coords: tuple[int, int]) -> None:
        self.subViews[subView] = coords

    def setResolution(self, xHeight: int, yWidth: int) -> None:
        super().setResolution(xHeight, yWidth)

    def onClick(self, xCoord: int, yCoord: int) -> None:
        for subView, (xCoordSubView, yCoordSubView) in self.subViews.items():
            if subView is None:
                continue
            xCoordClickSubView: int = xCoord - xCoordSubView
            yCoordClickSubView: int = yCoord - yCoordSubView

            if xCoordClickSubView in range(0, subView.xHeight) and \
               yCoordClickSubView in range(0, subView.yWidth):
                subView.onClick(xCoordClickSubView, yCoordClickSubView)
