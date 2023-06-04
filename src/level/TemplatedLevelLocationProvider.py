from .Chunk import Chunk
from .LevelLocationProvider import LevelLocationProvider

class TemplatedLevelLocationProvider(LevelLocationProvider):
  def __init__(self):
    pass

  def loadChunk() -> Chunk:
    return Chunk({})