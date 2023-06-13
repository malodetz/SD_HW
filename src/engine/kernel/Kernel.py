import curses

from .KernelInput import KernelInput
from .KernelOutput import KernelOutput
from .KernelInputHandler import KernelInputHandler

from .GameInstance import GameInstance

from engine.render import Renderer
from engine.render import RenderedView

from engine.config import Config

from gameplay.game.RoguelikeGameInstance import RoguelikeGameInstance

class Kernel:
  _screen: 'curses._CursesWindow'

  _kernelInput: KernelInput
  _kernelOutput: KernelOutput

  # TODO: move to the kernel output?
  _renderer: Renderer
  _gameInstance: GameInstance

  def __init__(self) -> None:
    self._screen = curses.initscr()
    curses.noecho()
    curses.cbreak()
    curses.curs_set(0)

    config = Config()
    config.bindingsConfig.bind(curses.KEY_RESIZE, "Resize")

    self._kernelInput = KernelInput(self._screen)
    KernelInputHandler().bind("Resize", self._onScreenResize)
    self._kernelInput.configureWith(config.bindingsConfig)

    self._renderer = Renderer()
    self._kernelOutput = KernelOutput(self._screen)

    self._gameInstance = RoguelikeGameInstance()
    self._onScreenResize()

  def _onScreenResize(self) -> None:
    xSizeScreen: int
    ySizeScreen: int
    xSizeScreen, ySizeScreen = self._screen.getmaxyx()
    self._gameInstance.view().setResolution(xSizeScreen, ySizeScreen)

  def run(self) -> None:
    while True: 
      self._gameInstance.tick()

      renderedView: RenderedView = self._renderer.renderView(self._gameInstance.view())
      self._kernelOutput.show(renderedView)

      self._kernelInput.awaitInput()
