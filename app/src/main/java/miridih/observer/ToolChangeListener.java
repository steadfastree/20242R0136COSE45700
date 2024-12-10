package miridih.observer;

import miridih.controller.state.Tool;

public interface ToolChangeListener {
    void onToolChanged(Tool newTool);
}
