from .Chunk import Chunk

class LevelLocationProvider:
  def loadChunk() -> Chunk:
    raise NotImplementedError()