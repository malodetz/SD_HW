from engine.render import View

class Widget:
  _widgetView: View
  
  def __init__(self) -> None:
    self._widgetView = None

  def setView(self, widgetView: View) -> None:
    self._widgetView = widgetView
  