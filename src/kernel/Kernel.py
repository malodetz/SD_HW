import curses

from render import Renderer
from .KernelInput import KernelInput
from .GameInstance import GameInstance

class Kernel:
  _renderer: Renderer
  _kernelInput: KernelInput
  _gameInstance: GameInstance

  def __init__(self):
    self.screen = curses.initscr()
    curses.noecho()
    curses.cbreak()

    self._renderer = Renderer(self.screen)
    self._kernelInput = KernelInput(self.screen)
    self._gameInstance = GameInstance()    

  def run(self) -> None:
    while True:
      self._kernelInput.awaitInput()
      self._gameInstance.tick()
      self._renderer.render()
