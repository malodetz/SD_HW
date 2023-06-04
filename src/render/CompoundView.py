from .View import View

class CompoundView(View):
  subViews: dict[View, tuple[int, int]]

  def __init__(self, xHeight: int, yWidth: int) -> None:
    super().__init__(xHeight, yWidth)
    self.subViews = {}

  def addSubView(self, xCoord: int, yCoord: int, subView: View) -> None:
    # self.subViews[xCoord, yCoord].append(view)
    self.subViews[subView] = (xCoord, yCoord)
