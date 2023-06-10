from engine.actors import Controller
from engine.kernel.KernelInputHandler import KernelInputHandler

class PlayerController(Controller):
  _inputManager: KernelInputHandler
  
  def __init__(self) -> None:
    super().__init__()
    self._inputManager = KernelInputHandler()
    self._setupInput()

  def _setupInput(self):
    pass

  def beginPlay() -> None:
    super().beginPlay()
