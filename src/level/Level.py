from actors import Actor

class Level:
  _owningWorld: 'World'
  _actors: dict[Actor, tuple[int, int]]


  def __init__(self) -> None:
    self._actors = {}

  def beginPlay(self) -> None:
    pass
  

  def spawnActor(self, xCoord: int, yCoord: int, actor: Actor) -> None:
    self._actors[actor] = (xCoord, yCoord)

  def coordsActor(self, actor: Actor) -> tuple[int, int]:
    return self._actors[actor]

  def despawnActor(self, actor: Actor) -> None:
    self._actors.pop(actor)

  def actors(self) -> list[Actor]:
    return self._actors.keys()


  def getWorld(self) -> 'World':
    return self._owningWorld

  def tick(self) -> None:
    for actor in self._actors.values():
      actor.tick()

from .World import World
