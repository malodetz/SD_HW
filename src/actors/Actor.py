class Actor:
  _owningLevel: 'Level'
  _actorView: 'View'
  
  def __init__(self) -> None:
    pass
  
  def beginPlay() -> None:
    pass

  def getLevel(self) -> 'Level':
    return self._owningLevel
  
  def view(self) -> 'View':
    return self._actorView

  def tick() -> None:
    pass

from render import View
from level import Level