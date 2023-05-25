import World

class Actor:
  _owningWorld: World 

  xCoord: int
  yCoord: int
  
  def __init__(self, xCoord: int, yCoord: int) -> None:
    self.xCoord = xCoord
    self.yCoord = yCoord

  def beginPlay():
    pass

  def getWorld(self) -> World:
    return self._owningWorld
