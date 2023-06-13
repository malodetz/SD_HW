from engine.render import View
from engine.render import CompoundView


class CameraView(CompoundView):
  """Class representing view from the camera.

  Renderable component representing image, which
  the owning camera views at the moment.
  """
  _owningCamera: 'CameraActor'

  def __init__(self, owningCamera: 'CameraActor') -> None:
    self._owningCamera = owningCamera
    super().__init__(0, 0)

  def update(self, views: dict[View, tuple[int, int]]) -> None:
    self.subViews = views

  def setResolution(self, xHeight: int, yWidth: int) -> None:
    if self.xHeight == xHeight and self.yWidth == yWidth:
      return
    super().setResolution(xHeight, yWidth)
    self._owningCamera.setFOV((xHeight, yWidth))    

from engine.actors import CameraActor
