from engine.actors import Actor, Controller

class Pawn(Actor):
  _controller: Controller
  
  def __init__(self) -> None:
    super().__init__()

  def possess(self, controller: Controller) -> None:
    self._controller = controller
    controller.onPossess(self)

  def beginPlay(self) -> None:
    super().beginPlay()

  def getController(self) -> Controller:
    return self._controller
