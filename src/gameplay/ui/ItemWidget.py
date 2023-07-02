from engine.ui import BoxWidget

class ItemWidget(BoxWidget):
    _inventory: 'InventoryWidget'

    def __init__(self, xHeight: int, yWidth: int, inventory: 'InventoryWidget') -> None:
        super().__init__(xHeight, yWidth)
        self._inventory = inventory

    def _showContextualMenu(self):
        pass

    def onClick(self) -> None:
        self._showContextualMenu()
    
from .InventoryWidget import InventoryWidget