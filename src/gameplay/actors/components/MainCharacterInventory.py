from gameplay.ui import InventoryWidget

class Item:
    pass

class MainCharacterInventory:
    _ITEMS_COUNT: int = 5
    _storage: list[Item]
    _widget: InventoryWidget

    def __init__(self) -> None:
        self._storage = []
        for _ in range(self._ITEMS_COUNT):
            self._storage.append(Item())
    
    def putItem(self, num: int, item: Item) -> None:
      if num not in range(self._ITEMS_COUNT):
          return
      self._storage[num] = item
    
    def popItem(self, num: int) -> None:
        self.putItem(num, Item())
