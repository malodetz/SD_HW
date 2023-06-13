from engine.kernel.GameInstance import GameInstance

from engine.level import Level
from engine.level import LevelLocation
from engine.level import LevelLocationProvider
from engine.level import TemplatedLevelLocationProvider

from gameplay.actors import MainCharacterPawn
from gameplay.actors import AttachableCameraActor
from gameplay.actors import MainPlayerController

from gameplay.ui import RoguelikeHUD

from engine.actors import Actor
from engine.render import RenderedView

class RoguelikeGameInstance(GameInstance):
  _mainCharacterPawn: MainCharacterPawn
  _mainPlayerController: MainPlayerController
  _mainCamera: AttachableCameraActor

  def _prepareMainController(self) -> None:
    self._mainPlayerController = MainPlayerController()
    self._mainPlayerController.possess(self._mainCharacterPawn)

  def _prepareMainCamera(self) -> None:
    self._mainCamera = AttachableCameraActor()
    self._world._currentLevel.spawnActor(self._mainCamera, (0, 0))
    self._mainCamera.attachTo(self._mainCharacterPawn)

  def _prepareMainCharacter(self) -> None:
    self._mainCharacterPawn = MainCharacterPawn()
    self._world._currentLevel.spawnActor(self._mainCharacterPawn, (0, 0))
    
  def _prepareLevel(self) -> None:
    initialLevelLocationProvider: LevelLocationProvider = TemplatedLevelLocationProvider(5, 5)
    initialLevel: Level = LevelLocation(initialLevelLocationProvider)
    self._world.loadLevel(initialLevel)

  def initGame(self) -> None:
    super().initGame()
    self._prepareLevel()
    self._prepareMainCharacter()
    self._prepareMainController()
    self._prepareMainCamera()

    actor: Actor = Actor()
    actor.setView(RenderedView([["A"]]))
    self._world._currentLevel.spawnActor(actor, (10, 10))

    self._hud = RoguelikeHUD()

  def initView(self) -> None:
    super().initView()
    self._gameView = self._mainCamera._cameraView
