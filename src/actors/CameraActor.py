from actors import Actor, CameraView
from level import Level

class CameraActor(Actor):
  """Actor representing camera. Can be exposed in the world.

  The camera is actor that can be place in the world and
  spectate some part of it. There can be multiple cameras
  placed in the world and each camera sees its own part.
  The image from the camera actor can be viewed from its
  CameraView, that is updated on every tick.
  """
  _cameraView: CameraView

  _xHalfHeightObserved: int
  _yHalfWidthObserved: int

  def __init__(self) -> None:
    super().__init__(self)

  def _isObserved(self, actor: Actor) -> bool:
    return actor.xCoord in range(super().xCoord() - self._xHalfHeightObserved, super().xCoord() + self._xHalfWidthObserved + 1) and \
           actor.yCoord in range(super().yCoord() - self._yHalfHeightObserved, super().yCoord() + self._yHalfWidthObserved + 1)

  def _observedActors(self) -> list:
    owningLevel: Level = super().getLevel()
    return list(filter(self._isObserved, owningLevel._actors))

  def tick(self) -> None:
    super().tick()
    observedActors = self._observedActors()
    self._cameraView.update(observedActors)
