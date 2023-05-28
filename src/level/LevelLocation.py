from .Level import Level
from .Chunk import Chunk

class LevelLocation(Level):
  _chunks: dict

  def __init__(self):
    super().__init__(self)
    self._chunks = {}

  def loadChunk(self, xCoordChunk: int, yCoordChunk: int) -> Chunk:
    chunk = self._chunks[(xCoordChunk, yCoordChunk)]
    if chunk == None:
      self._chunks[(xCoordChunk, yCoordChunk)] = super().getWorld()._levelProvider.loadChunk()
    return chunk
