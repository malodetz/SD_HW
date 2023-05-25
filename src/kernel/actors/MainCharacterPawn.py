import Pawn

class MainCharacterPawn(Pawn):
  _healthCount: int
  _expCount: int
  
  def __init__(self) -> None:
    super().__init__(self)
