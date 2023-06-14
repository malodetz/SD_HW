from engine.actors import Actor

class Level:
  _owningWorld: 'World'
  _actorsToCoords: dict[Actor, tuple[int, int]]
  _coordsToActors: dict[int, dict[int, set[Actor]]]

  def __init__(self) -> None:
    self._actorsToCoords = {}
    self._coordsToActors = {}

  def beginPlay(self) -> None:
    pass
  

  def spawnActor(self, actor: Actor, coords: tuple[int, int]) -> None:
    actor.setLevel(self)
    self.setCoordsActor(actor, coords)
    actor.beginPlay()

  def despawnActor(self, actor: Actor) -> None:
    self._actorsToCoords.pop(actor)

  def actors(self) -> list[Actor]:
    return self._actorsToCoords.keys()


  def coordsActor(self, actor: Actor) -> tuple[int, int]:
    return self._actorsToCoords[actor]
  
  def setCoordsActor(self, actor: Actor, coords: tuple[int, int]) -> None:
    if actor in self._actorsToCoords:
      xCoord: int
      yCoord: int
      xCoord, yCoord = self._actorsToCoords[actor]
      
      self._coordsToActors[xCoord][yCoord].discard(actor)
      if len(self._coordsToActors[xCoord][yCoord]) == 0:
        self._coordsToActors[xCoord].pop(yCoord)
        if len(self._coordsToActors[xCoord]) == 0:
          self._coordsToActors.pop(xCoord)

    xCoord: int
    yCoord: int
    xCoord, yCoord = coords
    if not xCoord in self._coordsToActors:
      self._coordsToActors[xCoord] = {}
    if not yCoord in self._coordsToActors[xCoord]:
      self._coordsToActors[xCoord][yCoord] = set()

    self._coordsToActors[xCoord][yCoord].add(actor)

    self._actorsToCoords[actor] = coords

  def addCoordsRelative(self, actor: Actor, offset: tuple[int, int]) -> None:
    xCoord: int
    yCoord: int
    xCoord, yCoord = self.coordsActor(actor)

    xCoordOffset: int
    yCoordOffset: int
    xCoordOffset, yCoordOffset = offset

    self.setCoordsActor(actor, (xCoord + xCoordOffset, yCoord + yCoordOffset))

  def getWorld(self) -> 'World':
    return self._owningWorld

  def tick(self) -> None:
    for actor in self._actorsToCoords.keys():
      actor.tick()

from .World import World
