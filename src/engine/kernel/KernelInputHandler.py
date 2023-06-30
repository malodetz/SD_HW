from typing import Callable
from utils import Singleton


@Singleton
class KernelInputHandler:
    _bindings: dict[str, list[Callable]]

    def __init__(self) -> None:
        self._bindings = {}

    def bind(self, key: str, action: Callable) -> None:
        if self._bindings.get(key) is None:
            self._bindings[key] = []
        self._bindings[key].append(action)

    def notify(self, key: str) -> None:
        if self._bindings.get(key) is None:
            return
        for action in self._bindings[key]:
            action()
