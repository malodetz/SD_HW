import curses

from .KernelInput import KernelInput
from .KernelOutput import KernelOutput
from .GameInstance import GameInstance

from engine.render import Renderer
from engine.render import RenderedView

class Kernel:
  _kernelInput: KernelInput
  _kernelOutput: KernelOutput

  # TODO: move to the kernel output?
  _renderer: Renderer
  _gameInstance: GameInstance


  def __init__(self) -> None:
    screen = curses.initscr()
    curses.noecho()
    curses.cbreak()

    self._kernelInput = KernelInput(screen)
    self._kernelOutput = KernelOutput(screen)
    self._gameInstance = GameInstance()    
    self._renderer = Renderer()

  def run(self) -> None:
    while True: 
      self._gameInstance.tick()

      renderedView: RenderedView = self._renderer.renderView(self._gameInstance.view())
      self._kernelOutput.show(renderedView)

      self._kernelInput.awaitInput()
