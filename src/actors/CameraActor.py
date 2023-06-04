from .Actor import Actor
from .CameraView import CameraView
from render import View
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

  def __init__(self, xHalfHeightObserved: int, yHalfWidthObserved: int) -> None:
    super().__init__()
    self._xHalfHeightObserved = xHalfHeightObserved
    self._yHalfWidthObserved = yHalfWidthObserved
    self._cameraView = CameraView(self._xHalfHeightObserved, self._yHalfWidthObserved)

  def _isObserved(self, xCoord: int, yCoord: int) -> bool:
    xCoordsOnLevel: int
    yCoordsOnLevel: int
    xCoordsOnLevel, yCoordsOnLevel = self._owningLevel.coordsActor(self)
    
    return xCoord in range(xCoordsOnLevel - self._xHalfHeightObserved, xCoordsOnLevel + self._xHalfHeightObserved + 1) and \
           yCoord in range(yCoordsOnLevel - self._yHalfWidthObserved, yCoordsOnLevel + self._yHalfWidthObserved + 1)

  def _observedActors(self) -> list[Actor]:
    observed: list[Actor] = []
    
    level: Level = self._owningLevel    
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
    observedViews: list[View] = map(Actor.view, observedActors)

    cameraViewAfterTick: dict[View, tuple[int, int]] = dict(zip(observedViews, map(self._owningLevel.coordsActor, observedActors)))
    self._cameraView.update(cameraViewAfterTick)
