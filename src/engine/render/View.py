class View:
  xHeight: int
  yWidth: int

  def __init__(self, xHeight: int, yWidth: int) -> None:
    View.setResolution(self, xHeight, yWidth)

  def setResolution(self, xHeight: int, yWidth: int) -> None:
    self.xHeight, self.yWidth = xHeight, yWidth
