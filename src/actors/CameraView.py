import render.View as View

class CameraView(View):
  """Class representing view from the camera.

  Renderable component representing image, which
  the owning camera views at the moment.
  """

  _subViews: set

  def __init__(self):
    super().__init__(self)

  def update(self, views: set):
    self._subViews = views

  def render(self):
    pass
