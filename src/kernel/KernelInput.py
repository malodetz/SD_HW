class KernelInput:
  """A class providing input for all registered listeners.

  Class implementing "observer" pattern. Awaits for events
  from the outer controller and notifies all registered
  listeners.
  """

  _listeners: list

  def __init__(self, screen):
    self._screen = screen

  def awaitInput(self):
    self._screen.getch()

  def listen(self, listener) -> None:
    pass

  def _notify() -> None:
    pass
