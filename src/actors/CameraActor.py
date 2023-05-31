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

  def _isObserved(self, xCoord: int, yCoord: int) -> bool:
    return xCoord in range(super().xCoord() - self._xHalfHeightObserved, super().xCoord() + self._xHalfWidthObserved + 1) and \
           yCoord in range(super().yCoord() - self._yHalfHeightObserved, super().yCoord() + self._yHalfWidthObserved + 1)

  def _observedActors(self) -> list[Actor]:
    observed: list[Actor] = []
    
    level: Level = self.getLevel()    
    for actor in level.actors():
      actorXCoord: int
      actorYCoord: int
      actorXCoord, actorYCoord = level.coordsActor(actor)
      if self._isObserved(actorXCoord, actorYCoord):
        observed.append(actor)

    return observed

  def tick(self) -> None:
    super().tick()
    observedActors: list[Actor] = self._observedActors()
    self._cameraView.update(observedActors)
