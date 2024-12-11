package miridih.command;

import java.awt.Color;

public interface CanvasPanelCommandInterface {
    public void bringToFront();

    public void sendToBack();

    public void updateShapeFromPanel(String x, String y, String w, String h);

    public void updateColor(Color color);

    public void undo();

    public void redo();
}
