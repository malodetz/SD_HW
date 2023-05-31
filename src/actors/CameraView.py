from render import CompoundView

class CameraView(CompoundView):
  """Class representing view from the camera.

  Renderable component representing image, which
  the owning camera views at the moment.
  """

  def __init__(self):
    super().__init__(self)

  def update(self, views: dict):
    self.subViews = views
