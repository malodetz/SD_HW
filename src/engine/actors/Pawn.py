from engine.actors import Actor, Controller

class Pawn(Actor):
  _controller: Controller
  
  def __init__(self, controller: Controller) -> None:
    super().__init__(self)
    self._controller = controller

  def beginPlay() -> None:
    super().beginPlay()

  def getController(self) -> Controller:
    return self._controller
