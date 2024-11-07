package miridih.controller.state;

import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class RectangleToolState extends ToolState {
    public RectangleToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        canvasModel.setStart(x,y);
    }

    @Override
    public void mouseReleased(double x, double y) {
        canvasModel.setEnd(x,y);
        canvasModel.createShape();
    }

    @Override
    public void mouseDragged(double x, double y, double dx, double dy) {
        System.out.println(" RectangleToolState mouseDragged");
    }
}
