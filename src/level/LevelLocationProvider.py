from .Chunk import Chunk

class LevelLocationProvider:
  _xHeightChunk: int
  _yWidthChunk: int
  
  def __init__(self, xHeightChunk: int, yWidthChunk: int):
    self._xHeightChunk = xHeightChunk
    self._yWidthChunk = yWidthChunk

  def xSizeChunk(self) -> int:
    return self._xHeightChunk

  def ySizeChunk(self) -> int:
    return self._yWidthChunk

  def loadChunk() -> Chunk:
    raise NotImplementedError()