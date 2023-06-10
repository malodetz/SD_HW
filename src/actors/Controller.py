from actors import Pawn
from kernel import KernelInputHandler

class Controller:
  _input: KernelInputHandler
  _owningPawn: Pawn 

  def __init__(self) -> None:
    self._owningPawn = None

  def possess(self, pawn: Pawn) -> None:
    self._owningPawn = pawn

  def beginPlay() -> None:
    pass
