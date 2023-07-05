from engine.ui import RelationalCompoundWidget
from engine.ui import BoxWidget

class InventoryWidget(BoxWidget):
    _chosen: 'ItemWidget'

    def __init__(self, xHeight: int, yWidth: int) -> None:
        super().__init__(xHeight, yWidth)
        self._chosen = None

        list: RelationalCompoundWidget = RelationalCompoundWidget(xHeight, yWidth)

        for i in range(5):
          item: 'ItemWidget' = ItemWidget(xHeight // 5, yWidth, self)
          list.addSubWidget(item, (xHeight // 5 * i, 0))

        self.setItem(list)

    def onChosen(self, item: 'ItemWidget') -> None:
        if self._chosen is not item and self._chosen is not None:
          self._chosen.hideContextualMenu()
        self._chosen = item

from .ItemWidget import ItemWidget
