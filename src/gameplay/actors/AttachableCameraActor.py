from engine.actors import Actor
from engine.actors import CameraActor

class AttachableCameraActor(CameraActor):
  _actorAttachedTo: Actor

  def __init__(self, xHalfHeightObserved: int, yHalfWidthObserved: int):
    super().__init__(xHalfHeightObserved, yHalfWidthObserved)
    self._actorAttachedTo = None

  def attachTo(self, actor: Actor) -> None:
    self._actorAttachedTo = actor

  def tick(self) -> None:
    super().tick()

    if self._actorAttachedTo is not None:
      self.setCoords(self._actorAttachedTo.coords())
