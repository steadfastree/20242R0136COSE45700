package miridih.model;

import java.util.ArrayList;

import miridih.objects.Shape;
import miridih.objects.Tool;

public class CanvasModel {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Shape selectedShape = null;
    private Tool currentTool = null;

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setCurrentTool(Tool tool) {
        currentTool = tool;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public Tool getCurrentTool() {
        return currentTool;
    }
}
