from engine.level import World
from engine.level import Level
from engine.level import LevelLocation
from engine.level import LevelLocationProvider
from engine.level import TemplatedLevelLocationProvider


from engine.ui import HUD

from engine.render import View
from engine.render import CompoundView
from engine.render import RenderedView

from engine.actors import Actor
from engine.actors import CameraActor

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
    self._world._currentLevel.spawnActor(cameraActor, (0, 0))

    actor: Actor = Actor()
    actor.setView(RenderedView([["A"]]))
    self._world._currentLevel.spawnActor(actor, (10, 10))

  def view(self) -> View:
    return self._gameView

  def tick(self) -> None:
    self._world.tick()
