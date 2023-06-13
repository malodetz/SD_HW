from engine.render.View import View
from .CompoundView import CompoundView

class RelationalCompoundView(CompoundView):
  def setResolution(self, xHeight: int, yWidth: int) -> None:
    xScaleFactor: float = xHeight / self.xHeight
    yScaleFactor: float = yWidth / self.yWidth

    for subView, (xOld, yOld) in self.subViews.items():
      self.subViews[subView] = (int(xOld * xScaleFactor), int(yOld * yScaleFactor))
      
      xHeightSubViewNew: int = int(subView.xHeight * xScaleFactor)
      yWidthSubViewNew: int = int(subView.yWidth * yScaleFactor)
      if subView.xHeight != xHeightSubViewNew or \
         subView.yWidth != yWidthSubViewNew:
        subView.setResolution(xHeightSubViewNew, yWidthSubViewNew)

    super().setResolution(xHeight, yWidth)
