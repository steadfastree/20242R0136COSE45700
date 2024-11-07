package miridih.controller.state;

import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public abstract class ToolState {
    private final CanvasController canvasController;
    private final CanvasModel canvasModel;

    public ToolState(CanvasController canvasController, CanvasModel canvasModel) {
        this.canvasController = canvasController;
        this.canvasModel = canvasModel;
    }

    public abstract void mousePressed(double x, double y);
    public abstract void mouseReleased(double x, double y);
    public abstract void mouseDragged(double x, double y);
}
