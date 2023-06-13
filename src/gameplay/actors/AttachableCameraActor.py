from engine.actors import Actor
from engine.actors import CameraActor

class AttachableCameraActor(CameraActor):
  _actorAttachedTo: Actor

  def __init__(self):
    super().__init__()
    self._actorAttachedTo = None

  def attachTo(self, actor: Actor) -> None:
    self._actorAttachedTo = actor
    self.setCoords(self._actorAttachedTo.coords())

  def tick(self) -> None:
    if self._actorAttachedTo is not None:
      self.setCoords(self._actorAttachedTo.coords())
    super().tick()
