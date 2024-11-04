package miridih.model;

import java.util.ArrayList;
import java.util.List;

import miridih.factory.ShapeFactory;
import miridih.objects.Shape;
import miridih.objects.Tool;
import miridih.observer.ShapeChangeListener;

public class CanvasModel {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Shape selectedShape = null;
    private Tool currentTool = Tool.SELECT;

    private List<ShapeChangeListener> listeners = new ArrayList<>();

    // 그리고 있는 도형의 좌표
    private double startX, startY, endX, endY;

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void addShapeChangeListener(ShapeChangeListener listener) {
        listeners.add(listener);
    }

    public void removeShapeChangeListener(ShapeChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyShapeChanged() {
        for (ShapeChangeListener listener : listeners) {
            listener.onShapeChanged();
        }
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

    public void handleClick(double x, double y) {
        if (currentTool == Tool.SELECT) {
            selectedShape = selectShape(x, y);
            System.out.println(selectedShape.getEndX());
        } else {
            createShape();
        }
    }

    public Shape selectShape(double x, double y) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.contains(x, y)) {
                return shape;
            }
        }
        return null;
    }

    public void createShape() {
        Shape newShape = ShapeFactory.createShape(currentTool);
        newShape.setStart(startX, startY);
        newShape.setEnd(endX, endY);
        if (currentTool != null) {
            newShape.setTool(currentTool);
            shapes.add(newShape);
            selectedShape = newShape;
            notifyShapeChanged();
        }
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }
}
