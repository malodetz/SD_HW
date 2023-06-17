from engine.actors import Pawn 

from engine.render import RenderedView
from engine.render import RenderedUnit

from utils import Graphic

class MainCharacterPawn(Pawn):
  _healthCount: int
  _expCount: int

  def __init__(self) -> None:
    super().__init__()
    self._healthCount = 100
    self._expCount = 0
    self.setView(RenderedView([[RenderedUnit("@", Graphic.magenta)]]))

  def beginPlay(self) -> None:
    super().beginPlay()

  def tick(self) -> None:
    super().tick()
