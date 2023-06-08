class Actor:
  _owningLevel: 'Level'
  
  _xOffsetActorView: int
  _yOffsetActorView: int

  _actorView: 'View'
  
  def __init__(self) -> None:
    self._actorView = None
  
  def beginPlay() -> None:
    pass

  def setView(self, view: 'View', xOffsetActorView: int = 0, yOffsetActorView: int = 0):
    self._actorView = view
    self._xOffsetActorView = xOffsetActorView
    self._yOffsetActorView = yOffsetActorView

  def setLevel(self, level) -> None:
    self._owningLevel = level

  def view(self) -> 'View':
    return self._actorView

  def tick(self) -> None:
    pass

from render import View
from level import Level