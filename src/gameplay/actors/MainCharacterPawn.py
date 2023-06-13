from engine.actors import Pawn 

from engine.render import RenderedView

class MainCharacterPawn(Pawn):
  _healthCount: int
  _expCount: int

  def __init__(self) -> None:
    super().__init__()
    self._healthCount = 100
    self._expCount = 0
    self.setView(RenderedView([["@"]]))

  def beginPlay(self) -> None:
    super().beginPlay()

  def tick(self) -> None:
    super().tick()
