class Chunk:
  _xSize: int
  _ySize: int

  def __init__(self, size: tuple[int, int]) -> None:
    self._xSize, self._ySize = size
