from actors import Pawn 

class MainCharacterPawn(Pawn):
  _healthCount: int
  _expCount: int
  
  def __init__(self) -> None:
    super().__init__(self)

  def beginPlay(self) -> None:
    super().beginPlay()

  def tick(self) -> None:
    super().tick()
