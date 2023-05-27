import actors.Actor as Actor 

class Chunk:
  _xSize: int
  _ySize: int

  _actors: set
  def spawnActor(self, actor: Actor):
    self._actors.add(actor)
