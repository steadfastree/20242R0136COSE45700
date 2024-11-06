package miridih.observer;

import miridih.objects.Tool;

public interface ToolChangeListener {
    void onToolChanged(Tool newTool);
}
