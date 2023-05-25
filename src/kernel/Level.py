import actors.Actor as Actor

class Level:
  _actors: set

  def __init__(self):
    pass

  def spawnActor(self, actor: Actor):
    self._actors.add(actor)