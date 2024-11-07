package miridih.command;

import miridih.controller.CanvasController;
import miridih.objects.Shape;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class CanvasCommand implements CanvasCommandInterface {
    CanvasController controller;

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
        ArrayList<Shape> selectedShapes = controller.getSelectedShapes();
        for (Shape shape : selectedShapes) {
            drawSelectionBox(g2d, shape);
            drawHandle(g2d, shape);
        }
    }

    private void drawSelectionBox(Graphics2D g2d, Shape selectedShape) {
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLUE);
        g2d.drawRect((int) selectedShape.getStartX(), (int) selectedShape.getStartY(),
                (int) (selectedShape.getEndX() - selectedShape.getStartX()),
                (int) (selectedShape.getEndY() - selectedShape.getStartY()));
    }

    private void drawHandle(Graphics2D g2d, Shape selectedShape) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect((int) selectedShape.getEndX() - 3, (int) selectedShape.getEndY() - 3, 6, 6);
    }

}
