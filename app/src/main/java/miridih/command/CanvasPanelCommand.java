package miridih.command;

import java.awt.Color;

import miridih.common.manager.SelectionManager;
import miridih.controller.CanvasController;

public class CanvasPanelCommand implements CanvasPanelCommandInterface {
    CanvasController controller;
    SelectionManager selectionManager = SelectionManager.getInstance();

    public CanvasPanelCommand(CanvasController controller) {
        this.controller = controller;
    }

    @Override
    public void bringToFront() {
        controller.bringToFront();
    }

    @Override
    public void sendToBack() {
        controller.sendToBack();
    }

    @Override
    public void updateShapeFromPanel(String x, String y, String w, String h) {
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
