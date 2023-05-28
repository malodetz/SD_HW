# from .Level import Level
from .LevelLocationProvider import LevelLocationProvider

class World:
  _currentLevel = None #: Level
  _levelProvider: LevelLocationProvider

  def __init__(self):
    pass
  
  def loadLevel(self, newLevel): #: Level):
    self._currentLevel = newLevel

  def tick(self):
    pass
  