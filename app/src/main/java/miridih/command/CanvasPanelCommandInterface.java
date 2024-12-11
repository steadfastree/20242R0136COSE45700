package miridih.command;

public interface CanvasPanelCommandInterface {
    public void bringToFront();

    public void sendToBack();

    public void updateShapeFromPanel(String x, String y, String w, String h);

    public void undo();

    public void redo();
}
