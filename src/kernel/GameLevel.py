import Level
import Chunk

class GameLevel(Level):
  _chunks: dict

  def __init__(self):
    super().__init__(self)
    self._chunks = {}

  def loadChunk(self, xCoordChunk: int, yCoordChunk: int) -> Chunk:
    chunk = self._chunks[(xCoordChunk, yCoordChunk)]
    if chunk == None:
      pass
    return chunk
