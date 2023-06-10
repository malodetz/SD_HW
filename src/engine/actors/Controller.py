from engine.actors import Pawn
from engine.kernel import KernelInputHandler

class Controller:
  _input: KernelInputHandler
  _owningPawn: Pawn 

  def __init__(self) -> None:
    self._owningPawn = None

  def onPossess(self, pawn: Pawn) -> None:
    self._owningPawn = pawn

  def beginPlay() -> None:
    pass
