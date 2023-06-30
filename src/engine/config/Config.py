from .ScreenConfig import ScreenConfig
from .BindingsConfig import BindingsConfig


class Config:
    screenConfig: ScreenConfig
    bindingsConfig: BindingsConfig

    def __init__(self) -> None:
        self.screenConfig = ScreenConfig()
        self.bindingsConfig = BindingsConfig()
