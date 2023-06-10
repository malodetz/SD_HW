from engine.actors import Pawn 

from .MainPlayerController import MainPlayerController

class MainCharacterPawn(Pawn):
  _healthCount: int
  _expCount: int

  def __init__(self) -> None:
    super().__init__()
    self.possess(MainPlayerController())

  def beginPlay(self) -> None:
    super().beginPlay()

  def tick(self) -> None:
    super().tick()
