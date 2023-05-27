import curses

import rendering.WindowRenderer as WindowRenderer
import kernel.SystemController as SystemController

class Kernel:
  _renderer: WindowRenderer
  _inputController: SystemController

  def __init__(self):
    self.screen = curses.initscr()
  

  def init(self) -> None:
    curses.noecho()
    curses.cbreak()

    self._renderer = WindowRenderer(self.screen)
    self._inputController = SystemController(self.screen)

  def destroy(self) -> None:
    curses.nocbreak()
    curses.echo()

  def run(self) -> None:
    self.init()
    while True:
      self._inputController.awaitInput()

      self._renderer.render()
    self.destroy()
