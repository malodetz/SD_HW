import curses

from .KernelInputHandler import KernelInputHandler

class KernelInput:
  """A class providing input for all registered listeners.

  Class implementing "observer" pattern. Awaits for events
  from the outer controller and notifies all registered
  listeners.
  """
  
  _screen: 'curses._CursesWindow'
  _inputManager: KernelInputHandler

  def __init__(self, screen: 'curses._CursesWindow') -> None:
    self._screen = screen
    self._inputManager = KernelInputHandler()

  def awaitInput(self) -> None:
    ctrl: int = self._screen.getch()
    self._inputManager.notify(ctrl)
