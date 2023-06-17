from .CompoundWidget import CompoundWidget

class TextWidget(CompoundWidget):
  _text: str
  
  def __init__(self) -> None:
    super().__init__()

  def setText(self, text: str) -> None:
    self._text = text
