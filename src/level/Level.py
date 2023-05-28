from actors import Actor
from .World import World

class Level:
  _owningWorld: World
  _actors: set

  def __init__(self):
    self._actors = set()

  def spawnActor(self, actor: Actor):
    self._actors.add(actor)

  def despawnActor(self, actor: Actor):
    self._actors.remove(actor)


  def getWorld(self) -> World:
    return self._owningWorld
  