from .Actor import Actor
from .CameraView import CameraView

from engine.render import View
from engine.level import Level

class CameraActor(Actor):
  """Actor representing camera. Can be exposed in the world.

  The camera is actor that can be place in the world and
  spectate some part of it. There can be multiple cameras
  placed in the world and each camera sees its own part.
  The image from the camera actor can be viewed from its
  CameraView, that is updated on every tick.

  The camera actor is positioned in the center of the camera view.
  """
  _cameraView: CameraView

  _xHalfHeightObserved: int
  _yHalfWidthObserved: int

  def __init__(self) -> None:
    super().__init__()
    self._cameraView = CameraView(self)
    self._xHalfHeightObserved = 0
    self._yHalfWidthObserved = 0

  def _isObserved(self, xCoord: int, yCoord: int) -> bool:
    xCoord: int
    yCoord: int
    xCoord, yCoord = self.coords()
    
    return xCoord in range(xCoord - self._xHalfHeightObserved, xCoord + self._xHalfHeightObserved + 1) and \
           yCoord in range(yCoord - self._yHalfWidthObserved, yCoord + self._yHalfWidthObserved + 1)

  def _observedActors(self) -> list[Actor]:
    observedActorsList: list[Actor] = []
    
    level: Level = self._owningLevel
    for actor in level.actors():
      xCoordActor: int
      yCoordActor: int
      xCoordActor, yCoordActor = level.coordsActor(actor)
      if self._isObserved(xCoordActor, yCoordActor):
        observedActorsList.append(actor)
    return observedActorsList

  def _composeDataForCameraView(self) ->  dict[View, tuple[int, int]]:
    xCoord: int
    yCoord: int
    xCoord, yCoord = self.coords()

    cameraViewData: dict[View, tuple[int, int]] = {}
    for actor in self._observedActors():
      xCoordRelative: int
      yCoordRelative: int
      xCoordRelative, yCoordRelative = actor.coords()
      cameraViewData[actor.view()] = (xCoordRelative - xCoord + self._xHalfHeightObserved, \
                                      yCoordRelative - yCoord + self._yHalfWidthObserved)
    return cameraViewData

  def setFOV(self, fov: tuple[int, int]) -> None:
    """Sets the field of view of the camera.

    Sets the observable part of level for the camera.
    Provided dimensions automatically increasing to the
    nearest odd numbers (as the camera located in the center
    of its "view").
    """
    self._xHalfHeightObserved, self._yHalfWidthObserved = fov
    self._xHalfHeightObserved //= 2
    self._yHalfWidthObserved //= 2
    self._cameraView.setResolution(self._xHalfHeightObserved * 2 + 1, \
                                   self._yHalfWidthObserved * 2 + 1)

  def tick(self) -> None:
    super().tick()
    self._cameraView.update(self._composeDataForCameraView())
