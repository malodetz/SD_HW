from .Widget import Widget

class CompoundWidget(Widget):
  _child: dict[Widget, tuple[int, int]]

  def __init__(self) -> None:
    super().__init__()
    self._child = {}
