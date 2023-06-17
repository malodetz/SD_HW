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
from engine.render import RenderedUnit
from engine.render import RelationalCompoundView

from engine.ui import BoxWidget
from engine.ui import BarWidget

from utils import Graphic
from engine.ui import TextWidget

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
    actor.setView(RenderedView([[RenderedUnit("A")]]))
    self._world._currentLevel.spawnActor(actor, (10, 10))

    self._hud = RoguelikeHUD()

  def initView(self) -> None:
    super().initView()
    self._gameView = RelationalCompoundView(50, 50)
    self._mainCamera.setFOV((50, 30))
    self._gameView._addSubView(self._mainCamera._cameraView, (0, 0))
    self._gameView._addSubView(self._hud.view(), (0, self._mainCamera._cameraView.yWidth))
