package miridih.controller.state;

import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class SelectToolState extends ToolState {
    public SelectToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        System.out.println("SelectToolState mousePressed");
    }

    @Override
    public void mouseReleased(double x, double y) {
        System.out.println("SelectToolState mouseReleased");
    }

    @Override
    public void mouseDragged(double x, double y) {
        System.out.println("SelectToolState mouseDragged");
    }
}
