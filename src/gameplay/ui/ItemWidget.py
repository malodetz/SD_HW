from engine.ui import BoxWidget

class ItemWidget(BoxWidget):
    _contextualMenuOpened: bool
    _inventory: 'InventoryWidget'

    def __init__(self, xHeight: int, yWidth: int, inventory: 'InventoryWidget') -> None:
        super().__init__(xHeight, yWidth)
        self._inventory = inventory
        self._contextualMenuOpened = False

    def showContextualMenu(self):
        if self._contextualMenuOpened:
            return
        # TODO: modify
        # self._widgetView
        self._contextualMenuOpened = True

    def hideContextualMenu(self):
        if not self._contextualMenuOpened:
            return
        self._contextualMenuOpened = False

    def onClick(self) -> None:
        self._inventory.onChosen(self)
        if self._contextualMenuOpened is False:
            self.showContextualMenu()
        else:
            self.hideContextualMenu()
    
from .InventoryWidget import InventoryWidget