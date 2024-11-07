package miridih.controller.state;

import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class RectangleToolState extends ToolState {
    public RectangleToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        System.out.println("RectangleToolState mousePressed");
    }

    @Override
    public void mouseReleased(double x, double y) {
        System.out.println("RectangleToolState mouseReleased");
    }

    @Override
    public void mouseDragged(double x, double y) {
        System.out.println("RectangleToolState mouseDragged");
    }
}
