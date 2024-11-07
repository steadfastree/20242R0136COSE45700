package miridih.command;

import miridih.controller.CanvasController;
import miridih.objects.Shape;

public class CanvasPanelCommand implements CanvasPanelCommandInterface {
    CanvasController controller;

    public CanvasPanelCommand(CanvasController controller) {
        this.controller = controller;
    }

    @Override
    public void bringToFront() {
        Shape selectedShape = controller.getSelectedShape();
        controller.bringToFront(selectedShape);
    }

    @Override
    public void sendToBack() {
        Shape selectedShape = controller.getSelectedShape();
        controller.sendToBack(selectedShape);
    }
}
