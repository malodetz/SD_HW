from .Actor import Actor
from .CameraView import CameraView

from engine.render import View
from engine.level import Level

from engine.render import RenderedView

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

  def __init__(self, xHalfHeightObserved: int, yHalfWidthObserved: int) -> None:
    super().__init__()
    self.setView(RenderedView([['@']]))

    self._xHalfHeightObserved = xHalfHeightObserved
    self._yHalfWidthObserved = yHalfWidthObserved
    self._cameraView = CameraView(self._xHalfHeightObserved * 2 + 1, self._yHalfWidthObserved * 2 + 1)

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


  def tick(self) -> None:
    self._cameraView.update(self._composeDataForCameraView())
    super().tick()
