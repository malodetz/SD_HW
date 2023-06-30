from .Chunk import Chunk
from .LevelLocationProvider import LevelLocationProvider


class TemplatedLevelLocationProvider(LevelLocationProvider):
    def __init__(self, xHeightChunk: int, yWidthChunk: int) -> None:
        super().__init__(xHeightChunk, yWidthChunk)

    def loadChunk(self) -> Chunk:
        return Chunk(self.chunkSizes())
