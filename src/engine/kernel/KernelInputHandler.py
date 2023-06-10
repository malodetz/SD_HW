from typing import Callable
from utils import Singleton

@Singleton
class KernelInputHandler:
  _bindings: dict[int, list[Callable]]

  def __init__(self) -> None:
    self._bindings = {}

  def bind(self, key: int, action: Callable) -> None:
    if self._bindings.get(key) is None:
      self._bindings[key] = []
    self._bindings[key].append(action)

  def notify(self, key: int) -> None:
    if self._bindings.get(key) is None:
      return
    for action in self._bindings[key]:
      action()
