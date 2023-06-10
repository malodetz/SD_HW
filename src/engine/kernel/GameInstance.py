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

from gameplay.actors import MainCharacterPawn
from gameplay.actors import AttachableCameraActor

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
    
    cameraActor: AttachableCameraActor = AttachableCameraActor(25, 25)
    self._gameView = cameraActor._cameraView

    mainCharacter: MainCharacterPawn = MainCharacterPawn()

    self._world._currentLevel.spawnActor(cameraActor, (0, 0))
    self._world._currentLevel.spawnActor(mainCharacter, (0, 0))
    
    cameraActor.attachTo(mainCharacter)
    mainCharacter.setView(RenderedView([["@"]]))

    actor: Actor = Actor()
    actor.setView(RenderedView([["A"]]))
    self._world._currentLevel.spawnActor(actor, (10, 10))

  def view(self) -> View:
    return self._gameView

  def tick(self) -> None:
    self._world.tick()
