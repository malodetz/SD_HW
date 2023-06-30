from engine.actors import PlayerController


class MainPlayerController(PlayerController):
    def __init__(self) -> None:
        super().__init__()

    def _setupInput(self) -> None:
        self._inputManager.bind("MoveUp", self.onMoveUp)
        self._inputManager.bind("MoveLeft", self.onMoveLeft)
        self._inputManager.bind("MoveDown", self.onMoveDown)
        self._inputManager.bind("MoveRight", self.onMoveRight)

    def onMoveUp(self) -> None:
        self._owningPawn.addCoordsRelative(-1, 0)

    def onMoveDown(self) -> None:
        self._owningPawn.addCoordsRelative(1, 0)

    def onMoveLeft(self) -> None:
        self._owningPawn.addCoordsRelative(0, -1)

    def onMoveRight(self) -> None:
        self._owningPawn.addCoordsRelative(0, 1)
