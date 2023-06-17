import curses

from .KernelInput import KernelInput
from .KernelOutput import KernelOutput
from .KernelInputHandler import KernelInputHandler

from .GameInstance import GameInstance

from engine.render import Renderer
from engine.render import RenderedView

from engine.config import Config

from gameplay.game.RoguelikeGameInstance import RoguelikeGameInstance

from utils import Graphic

class Kernel:
  _screen: 'curses._CursesWindow'

  _kernelInput: KernelInput
  _kernelOutput: KernelOutput

  _renderer: Renderer
  _gameInstance: GameInstance

  def __init__(self) -> None:
    self._setupCurses()

    config = Config()
    config.bindingsConfig.bind(curses.KEY_RESIZE, "Resize")

    self._kernelInput = KernelInput(self._screen)
    KernelInputHandler().bind("Resize", self._onScreenResize)
    self._kernelInput.configureWith(config.bindingsConfig)

    self._renderer = Renderer()
    self._kernelOutput = KernelOutput(self._screen)

    self._gameInstance = RoguelikeGameInstance()
    self._onScreenResize()

  def _setupCurses(self) -> None:
    self._screen = curses.initscr()
    curses.start_color()
    curses.use_default_colors()

    curses.init_pair(Graphic.red, curses.COLOR_RED, -1)
    curses.init_pair(Graphic.yellow, curses.COLOR_YELLOW, -1)
    curses.init_pair(Graphic.green, curses.COLOR_GREEN, -1)
    curses.init_pair(Graphic.cyan, curses.COLOR_CYAN, -1)
    curses.init_pair(Graphic.blue, curses.COLOR_BLUE, -1)
    curses.init_pair(Graphic.magenta, curses.COLOR_MAGENTA, -1)
    curses.init_pair(Graphic.black, curses.COLOR_BLACK, -1)
    curses.init_pair(Graphic.white, curses.COLOR_WHITE, -1)

    curses.noecho()
    curses.cbreak()
    curses.curs_set(0)

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
