class ScreenConfig:
  _resolution: tuple[int, int]

  def __init__(self) -> None:
    super().__init__()
    self.setScreenSize((0, 0))

  def setScreenSize(self, resolution: tuple[int, int]) -> None:
    self._resolution = resolution
