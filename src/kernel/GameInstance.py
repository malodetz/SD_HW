from level import World
from level import Level
from level import LevelLocation
from level import LevelLocationProvider
from level import TemplatedLevelLocationProvider


from ui import HUD

from render import View
from render import CompoundView
from render import RenderedView

from actors import Actor
from actors import CameraActor

class GameInstance:
  _world: World
  _hud: HUD

  _gameView: View

  def __init__(self) -> None:
    self._world = World()
    self._hud = HUD()

    # self._gameView = CompoundView(10, 10)

    # TODO: for test. Remove later.
    initialLevelLocationProvider: LevelLocationProvider = TemplatedLevelLocationProvider(5, 5)
    initialLevel: Level = LevelLocation(initialLevelLocationProvider)
    self._world.loadLevel(initialLevel)
    cameraActor: CameraActor = CameraActor(25, 25)
    
    self._gameView = cameraActor._cameraView
    self._world._currentLevel.spawnActor(0, 0, cameraActor)

    actor: Actor = Actor()
    actor.setView(RenderedView([["A"]]))
    self._world._currentLevel.spawnActor(10, 10, actor)

  def view(self) -> View:
    return self._gameView

  def tick(self) -> None:
    self._world.tick()
