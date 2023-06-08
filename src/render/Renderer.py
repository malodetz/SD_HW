from .View import View
from .CompoundView import CompoundView
from .RenderedView import RenderedView
from .RenderedViewBuilder import RenderedViewBuilder

class Renderer:
  """Class for rendering and displaying views visible at the screen.  

  """

  def __init__(self):
    pass

  def renderView(self, view: View) -> RenderedView:    
    if isinstance(view, CompoundView):
      return self._renderCompoundView(view)
    
    return self._renderSingleView(view)

  def _renderSingleView(self, view: View) -> RenderedView:
    renderedViewBuilder: RenderedViewBuilder = RenderedViewBuilder(view.xHeight, view.yWidth)
    renderedViewBuilder.nest(0, 0, view)
    return renderedViewBuilder.build()
  

  def _renderCompoundView(self, view: CompoundView) -> RenderedView:
    renderedViewBuilder: RenderedViewBuilder = RenderedViewBuilder(view.xHeight, view.yWidth) 
    for subView, (x, y) in view.subViews.items():
      if subView is None:
        continue
      renderedSubView: RenderedView = self.renderView(subView)
      renderedViewBuilder.nest(x, y, renderedSubView)
    
    return renderedViewBuilder.build()
 