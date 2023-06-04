class Chunk:
  _xSize: int
  _ySize: int

  _subActors: dict['Actor', tuple[int, int]]

  def __init__(self, subActors: dict['Actor', tuple[int, int]]) -> None:
    self._subActors = subActors

from actors import Actor
