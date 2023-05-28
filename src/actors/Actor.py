from level import Level

class Actor:
  _owningLevel: Level

  xCoord: int
  yCoord: int
  
  def __init__(self) -> None:
    pass
  
  def beginPlay() -> None:
    pass

  def getLevel(self) -> Level:
    return self._owningLevel
  
  def tick() -> None:
    pass
