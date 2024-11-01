package miridih.model;

import java.util.ArrayList;

import miridih.objects.Shape;
import miridih.objects.Tool;

public class CanvasModel {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Shape selectedShape = null;
    private Tool currentTool = null;

    // 그리고 있는 도형의 좌표
    private double startX, startY, endX, endY;

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setCurrentTool(Tool tool) {
        currentTool = tool;
    }

    public void setStart(double x, double y) {
        startX = x;
        startY = y;
    }

    public void setEnd(double x, double y) {
        endX = x;
        endY = y;
    }

    public void createShape() {
        Shape newShape = new Shape();
        newShape.setStart(startX, startY);
        newShape.setEnd(endX, endY);
        if (currentTool != null) {
            newShape.setTool(currentTool);
            shapes.add(newShape);
            selectedShape = newShape;
        }
    }
}
