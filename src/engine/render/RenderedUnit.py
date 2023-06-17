class RenderedUnit:
  color: int
  symbol: str

  def __init__(self, symbol: str, color: int = -1):
    self.color = color
    self.symbol = symbol
