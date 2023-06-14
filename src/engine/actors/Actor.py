class Actor:
  _owningLevel: 'Level'
  
  _xOffsetActorView: int
  _yOffsetActorView: int

  _actorView: 'View'
  
  def __init__(self) -> None:
    self._actorView = None
    self._owningLevel = None
  
  def beginPlay(self) -> None:
    pass

  def setView(self, view: 'View', xOffsetActorView: int = 0, yOffsetActorView: int = 0) -> None:
    self._actorView = view
    self._xOffsetActorView = xOffsetActorView
    self._yOffsetActorView = yOffsetActorView

  def view(self) -> 'View':
    return self._actorView

  def coords(self) -> tuple[int, int]:
    return self._owningLevel.coordsActor(self)

  def setCoords(self, coords: tuple[int, int]) -> None:
    self._owningLevel.setCoordsActor(self, coords)

  def addCoordsRelative(self, xCoordOffset: int, yCoordOffset: int) -> None:
    self._owningLevel.addCoordsRelative(self, (xCoordOffset, yCoordOffset))

  def onCollision(self, actor: 'Actor') -> None:
    pass

  def setLevel(self, level) -> None:
    self._owningLevel = level

  def tick(self) -> None:
    pass

from engine.render import View
from engine.level import Level