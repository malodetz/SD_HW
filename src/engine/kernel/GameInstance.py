from engine.level import World

from engine.ui import HUD

from engine.render import View

from engine.config import Config

from engine.kernel.KernelInputHandler import KernelInputHandler

class GameInstance:
  _world: World
  _hud: HUD

  _gameView: View

  _paused: bool

  def __init__(self) -> None:
    self._paused = False

    self._world = World()
    self._hud = HUD()

    self.initGame()
    self.initView()

  def pause(self, paused: bool = True):
    self._paused = paused

  def view(self) -> View:
    return self._gameView

  def initGame(self) -> None:
    pass

  def initView(self) -> None:
    pass

  def tick(self) -> None:
    if self._paused:
      return
    self._world.tick()
