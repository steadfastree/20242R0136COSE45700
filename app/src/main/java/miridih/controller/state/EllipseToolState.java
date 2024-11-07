package miridih.controller.state;

import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class EllipseToolState extends ToolState {
    public EllipseToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        System.out.println("EllipseToolState mousePressed");
    }

    @Override
    public void mouseReleased(double x, double y) {
        System.out.println("EllipseToolState mouseReleased");
    }

    @Override
    public void mouseDragged(double x, double y) {
        System.out.println("EllipseToolState mouseDragged");
    }
}
