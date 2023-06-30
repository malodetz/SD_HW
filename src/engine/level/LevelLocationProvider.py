from .Chunk import Chunk


class LevelLocationProvider:
    _xHeightChunk: int
    _yWidthChunk: int

    def __init__(self, xHeightChunk: int, yWidthChunk: int) -> None:
        self._xHeightChunk = xHeightChunk
        self._yWidthChunk = yWidthChunk

    def chunkSizes(self) -> tuple[int, int]:
        return (self._xHeightChunk, self._yWidthChunk)

    def loadChunk() -> Chunk:
        raise NotImplementedError()
