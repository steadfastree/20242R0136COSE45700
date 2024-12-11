package miridih.command;

import java.awt.Color;

import miridih.common.manager.SelectionManager;
import miridih.controller.CanvasController;
import miridih.model.objects.Shape;

public class CanvasPanelCommand implements CanvasPanelCommandInterface {
    CanvasController controller;
    SelectionManager selectionManager = SelectionManager.getInstance();

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

    @Override
    public void updateShapeFromPanel(String x, String y, String w, String h) {
        Shape selectedShape = selectionManager.getSelectedShapes().getChildren().get(0);
        if (selectedShape != null) {
            try {
                double _x = Double.parseDouble(x);
                double _y = Double.parseDouble(y);
                double _w = Double.parseDouble(w);
                double _h = Double.parseDouble(h);

                controller.updateSelectedShape(_x, _y, _w, _h);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    @Override
    public void undo() {
        controller.undo();
    }

    @Override
    public void redo() {
        controller.redo();
    }

    @Override
    public void updateColor(Color color) {
        controller.updateColor(color);
    }
}
