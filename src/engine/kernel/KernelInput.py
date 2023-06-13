import curses

from engine.config import BindingsConfig

from .KernelInputHandler import KernelInputHandler

class KernelInput:
  """A class providing input for all registered listeners.

  Class implementing "observer" pattern. Awaits for events
  from the outer controller and notifies all registered
  listeners.
  """
  
  _screen: 'curses._CursesWindow'
  _inputManager: KernelInputHandler
  _bindingsConfig: BindingsConfig

  def __init__(self, screen: 'curses._CursesWindow') -> None:
    self._screen = screen
    self._inputManager = KernelInputHandler()

  def configureWith(self, bindingsConfig: BindingsConfig) -> None:
    self._bindingsConfig = bindingsConfig

  def awaitInput(self) -> None:
    ctrl: int = self._screen.getch()
    key: str = self._bindingsConfig.name(ctrl)
    self._inputManager.notify(key)
