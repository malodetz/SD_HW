from actors import Actor
from .Level import Level
from .Chunk import Chunk
from .LevelLocationProvider import LevelLocationProvider
from .TemplatedLevelLocationProvider import TemplatedLevelLocationProvider

class LevelLocation(Level):
  _chunks: dict[tuple[int, int], Chunk]
  _levelLocationProvider: LevelLocationProvider

  def __init__(self, levelLocationProvider: LevelLocationProvider) -> None:
    super().__init__()
    self._chunks = {}
    self._levelLocationProvider = levelLocationProvider

  def _loadChunk(self, xCoordChunk: int, yCoordChunk: int) -> None:
    if not (xCoordChunk, yCoordChunk) in self._chunks:
      self._chunks[(xCoordChunk, yCoordChunk)] = self._levelLocationProvider.loadChunk()

  def _xHeightChunk(self) -> int:
    return self._levelLocationProvider.xSizeChunk()

  def _yWidthChunk(self) -> int:
    return self._levelLocationProvider.ySizeChunk()

  def _chunkCoordsByPoint(self, xCoord: int, yCoord: int) -> tuple[int, int]:
    xCoordChunk: int = xCoord // self._xHeightChunk()
    if xCoord % self._xHeightChunk() != 0:
      xCoordChunk += (1 if xCoord > 0 else -1)
    
    yCoordChunk: int = yCoord // self._yWidthChunk()
    if yCoord % self._yWidthChunk() != 0:
      yCoordChunk += (1 if yCoord > 0 else -1)
    
    return (xCoordChunk, yCoordChunk)

  def _prepare(self, xCoordUpper: int, yCoordUpper: int, xCoordLower: int, yCoordLower: int) -> None:
    xCoordUpperChunk: int
    yCoordUpperChunk: int
    xCoordUpperChunk, yCoordUpperChunk = self._chunkCoordsByPoint(xCoordUpper, yCoordUpper)
    
    xCoordLowerChunk: int
    yCoordLowerChunk: int
    xCoordLowerChunk, yCoordLowerChunk = self._chunkCoordsByPoint(xCoordLower, yCoordLower)

    for xCoordChunk in range(xCoordUpperChunk, xCoordLowerChunk + 1):
      for yCoordChunk in range(yCoordUpperChunk, yCoordLowerChunk + 1):
        self._loadChunk(xCoordChunk, yCoordChunk)

  def tick(self) -> None:
    for (xCoord, yCoord) in self._actors.values():
      self._prepare(xCoord - 100, yCoord - 100, xCoord + 100, yCoord + 100)
    super().tick()