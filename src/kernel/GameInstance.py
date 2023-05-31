from level import World
from ui import HUD

from render import View
from render import CompoundView

class GameInstance:
  _world: World
  _hud: HUD

  _gameView: View

  def __init__(self) -> None:
    self._world = World()
    self._hud = HUD()

    self._gameView = CompoundView(10, 10)

  def view(self) -> View:
    return self._gameView

  def tick(self) -> None:
    self._world.tick()
