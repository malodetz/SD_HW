from engine.actors import PlayerController

class MainPlayerController(PlayerController):
  
  def __init__(self) -> None:
    super().__init__()

  def _setupInput(self) -> None:
    # TODO: move to config #
    self._inputManager.bind(ord('w'), self.onMoveUp)
    self._inputManager.bind(ord('a'), self.onMoveLeft)
    self._inputManager.bind(ord('s'), self.onMoveDown)
    self._inputManager.bind(ord('d'), self.onMoveRight)

  def onMoveUp(self) -> None:
    self._owningPawn.coordsAddRelativeOffset(-1, 0)

  def onMoveDown(self) -> None:
    self._owningPawn.coordsAddRelativeOffset(1, 0)

  def onMoveLeft(self) -> None:
    self._owningPawn.coordsAddRelativeOffset(0, -1)

  def onMoveRight(self) -> None:
    self._owningPawn.coordsAddRelativeOffset(0, 1)