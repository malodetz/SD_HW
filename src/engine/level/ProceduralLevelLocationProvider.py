from .Chunk import Chunk

import level.LevelLocationProvider as LevelLocationProvider


class ProceduralLevelGenerator(LevelLocationProvider):
    def loadChunk() -> Chunk:
        raise NotImplementedError()
