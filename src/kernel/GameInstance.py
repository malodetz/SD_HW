from level import World
from ui import HUD

class GameInstance:
  _world: World
  _hud: HUD

  def __init__(self) -> None:
    self._world = World()
    # self._hud = HUD()

  def tick(self) -> None:
    self._world.tick()

