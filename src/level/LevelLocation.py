from .Level import Level
from .Chunk import Chunk
from .LevelLocationProvider import LevelLocationProvider
from .TemplatedLevelLocationProvider import TemplatedLevelLocationProvider

class LevelLocation(Level):
  _chunks: dict
  _levelLocationProvider: LevelLocationProvider

  _chunkXHeight: int
  _chunkYWidth: int

  def __init__(self, levelLocationProvider: LevelLocationProvider) -> None:
    super().__init__()
    self._chunks = {}
    self._levelLocationProvider = levelLocationProvider

  def loadChunk(self, xCoordChunk: int, yCoordChunk: int) -> None:
    if not (xCoordChunk, yCoordChunk) in self._chunks:
      self._chunks[(xCoordChunk, yCoordChunk)] = self._levelLocationProvider.loadChunk()
  
  def loadChunkByPoint(self, xCoord: int, yCoord: int) -> None:
    xChunkCoord: int = xCoord / self._chunkXHeight
    if xCoord % self._chunkXHeight != 0:
      xChunkCoord += (1 if xCoord > 0 else -1)
    
    yChunkCoord: int = yCoord / self._chunkYWidth
    if yCoord % self._chunkYWidth != 0:
      yChunkCoord += (1 if yCoord > 0 else -1)
    
    self.loadChunk(xChunkCoord, yChunkCoord)
