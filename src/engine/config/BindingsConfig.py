class BindingsConfig:
    _keyBindings: dict[int, str]

    def __init__(self) -> None:
        self._keyBindings = {}
        self._keyBindings[ord("w")] = "MoveUp"
        self._keyBindings[ord("a")] = "MoveLeft"
        self._keyBindings[ord("s")] = "MoveDown"
        self._keyBindings[ord("d")] = "MoveRight"
        self._keyBindings[ord("p")] = "Pause"

    def bind(self, key: int, name: str) -> None:
        self._keyBindings[key] = name

    def name(self, key: int) -> str:
        return self._keyBindings.get(key)
