from engine.ui import RelationalCompoundWidget
from engine.ui import BoxWidget

class InventoryWidget(BoxWidget):
    def __init__(self, xHeight: int, yWidth: int) -> None:
        super().__init__(xHeight, yWidth)

        list: RelationalCompoundWidget = RelationalCompoundWidget(xHeight, yWidth)
        
        for i in range(5):
          list.addSubWidget(ItemWidget(xHeight // 5, yWidth, self), (xHeight // 5 * i, 0))

        self.setItem(list)

from .ItemWidget import ItemWidget
