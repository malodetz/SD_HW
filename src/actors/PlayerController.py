from actors import Controller

class PlayerController(Controller):
  
  def __init__(self) -> None:
    super().__init__(self)

  def setupInput(self):
    pass

  def beginPlay() -> None:
    super().beginPlay()
