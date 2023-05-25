import Actor
import controllers.Controller as Controller

class Pawn(Actor):
  _controller: Controller
  
  def __init__(self, controller: Controller) -> None:
    super().__init__(self)
    self._controller = controller

  def getController(self) -> Controller:
    return self._controller
  
