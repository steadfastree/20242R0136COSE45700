package miridih.command;

import java.awt.Graphics2D;
import java.util.ArrayList;

import miridih.common.manager.SelectionManager;
import miridih.controller.CanvasController;
import miridih.model.objects.Shape;

public class CanvasCommand implements CanvasCommandInterface {
    CanvasController controller;
    SelectionManager selectionManager = SelectionManager.getInstance();

    public CanvasCommand(CanvasController controller) {
        this.controller = controller;
    }

    @Override
    public void drawShapes(Graphics2D g2d) {
        ArrayList<Shape> shapes = controller.getShapes();
        shapes.forEach((shape) -> shape.draw(g2d));

    }

    @Override
    public void drawSelectedShapes(Graphics2D g2d) {
        ArrayList<Shape> selectedShapes = selectionManager.getSelectedShapes().getChildren();
        selectedShapes.forEach((shape) -> {
            shape.drawSelectionBox(g2d);
            shape.drawHandle(g2d);
        });
    }
}
