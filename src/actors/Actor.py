class Actor:
  _owningLevel: 'Level'
  _actorView: 'View'
  
  def __init__(self) -> None:
    self._actorView = None
  
  def beginPlay() -> None:
    pass


  def setLevel(self, level) -> None:
    self._owningLevel = level

  def view(self) -> 'View':
    return self._actorView

  def tick(self) -> None:
    pass

from render import View
from level import Level