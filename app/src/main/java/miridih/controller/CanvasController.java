package miridih.controller;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import miridih.model.CanvasModel;
import miridih.objects.Shape;
import miridih.objects.Tool;

public class CanvasController extends MouseAdapter {
    private final CanvasModel canvasModel;

    public CanvasController(CanvasModel model) {
        canvasModel = model;
    }

    public void mousePressed(double x, double y) {
        canvasModel.setStart(x, y);
    }

    public void mouseReleased(double x, double y) {
        canvasModel.setEnd(x, y);
        canvasModel.handleClick(x, y);
    }

    public void setCurrentTool(Tool tool) {
        canvasModel.setCurrentTool(tool);
    }

    public ArrayList<Shape> getShapes() {
        return canvasModel.getShapes();
    }
}
