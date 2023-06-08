from render import View
from render import CompoundView


class CameraView(CompoundView):
  """Class representing view from the camera.

  Renderable component representing image, which
  the owning camera views at the moment.
  """

  def __init__(self, xHeight: int, yWidth: int) -> None:
    super().__init__(xHeight, yWidth)

  def update(self, views: dict[View, tuple[int, int]]) -> None:
    self.subViews = views
