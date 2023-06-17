from utils import Graphic

class RenderedUnit:
  color: int
  symbol: str

  def __init__(self, symbol: str, color: int = Graphic.white):
    self.color = color
    self.symbol = symbol
