import Level
import controllers.Controller as Controller

class World:
  _globalPlayerController: Controller
  _currentLevel: Level

  def __init__(self, playerController: Controller):
    self._globalPlayerController = playerController
  
  def loadLevel(self, currentLevel: Level):
    self._currentLevel = currentLevel

  def getPlayerController(self) -> Controller:
    return self._globalPlayerController
