from .Level import Level

class World:
  _currentLevel: Level

  def __init__(self) -> None:
    self._currentLevel = None
  
  def loadLevel(self, newLevel: Level) -> None:
    self._currentLevel = newLevel
    self._currentLevel.beginPlay()
    for actor in self._currentLevel._actors:
      actor.beginPlay()

  def tick(self) -> None:
    if not self._currentLevel is None:
      self._currentLevel.tick()
