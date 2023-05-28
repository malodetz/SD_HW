class Chunk:
  _xSize: int
  _ySize: int

  _gameField: str

  def __init__(self, field: str):
    self._gameField = field
