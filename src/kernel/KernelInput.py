import curses

from typing import Callable

class KernelInput:
  """A class providing input for all registered listeners.

  Class implementing "observer" pattern. Awaits for events
  from the outer controller and notifies all registered
  listeners.
  """
  
  _screen: 'curses._CursesWindow'
  _bindings: dict[int, list[Callable]]

  def __init__(self, screen: 'curses._CursesWindow') -> None:
    self._screen = screen
    self._bindings = {}

  def awaitInput(self) -> None:
    ctrl: int = self._screen.getch()
    self._notify(ctrl)

  def bind(self, key: int, action: Callable) -> None:
    if self._bindings.get(key) is None:
      self._bindings[key] = []
    self._bindings[key].append(action)

  def _notify(self, key: int) -> None:
    if self._bindings.get(key) is None:
      return
    for action in self._bindings[key]:
      action()
