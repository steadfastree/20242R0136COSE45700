package miridih.controller.state;

import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class MultiSelectToolState extends ToolState {
    public MultiSelectToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        System.out.println("MultiSelectToolState mousePressed");
    }

    @Override
    public void mouseReleased(double x, double y) {
        System.out.println("MultiSelectToolState mouseReleased");
    }

    @Override
    public void mouseDragged(double x, double y) {
        System.out.println("MultiSelectToolState mouseDragged");
    }
}
