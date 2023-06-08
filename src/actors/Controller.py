from actors import Pawn

class Controller:
  _owningPawn: Pawn 

  def __init__(self, pawn: Pawn) -> None:
    self._owningPawn = pawn

  def beginPlay() -> None:
    pass
