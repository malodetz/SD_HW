from engine.actors import Actor, Controller


class Pawn(Actor):
    _controller: Controller

    def __init__(self) -> None:
        super().__init__()

    def onPossess(self, controller: Controller) -> None:
        self._controller = controller

    def beginPlay(self) -> None:
        super().beginPlay()

    def getController(self) -> Controller:
        return self._controller
