package miridih.model;

import java.util.ArrayList;
import java.util.List;

import miridih.common.manager.SelectionManager;
import miridih.controller.state.Tool;
import miridih.model.objects.CompositeShape;
import miridih.model.objects.Shape;
import miridih.observer.ShapeChangeListener;
import miridih.observer.ToolChangeListener;

public class CanvasModel {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private final SelectionManager selectionManager = SelectionManager.getInstance();

    private List<ShapeChangeListener> shapeChangeListeners = new ArrayList<>();
    private ArrayList<ToolChangeListener> toolChangeListeners = new ArrayList<>();

    // 그리고 있는 도형의 좌표
    private double lastX, lastY;

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        notifyShapeChanged(); 
    }

    public void addShapeChangeListener(ShapeChangeListener listener) {
        shapeChangeListeners.add(listener);
    }

    public void removeShapeChangeListener(ShapeChangeListener listener) {
        shapeChangeListeners.remove(listener);
    }

    private void notifyShapeChanged() {
        for (ShapeChangeListener listener : shapeChangeListeners) {
            listener.onShapeChanged();
        }
    }

    public void addToolChangeListener(ToolChangeListener listener) {
        toolChangeListeners.add(listener);
    }

    public void setLastPoint(double x, double y){
        lastX = x;
        lastY = y;
    }

    public double getLastX() {
        return lastX;
    }

    public double getLastY() {
        return lastY;
    }




    public Shape clickShape(double x, double y) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.contains(x, y)) {
                return shape;
            }
        }
        return null;
    }


    public Shape getSelectedShape() {
        return selectionManager.getSelectedShapes().getChildren().isEmpty() ? null : selectionManager.getSelectedShapes().getChildren().get(0);
    }

    public ArrayList<Shape> getSelectedShapes() {
        return selectionManager.getSelectedShapes().getChildren();
    }

    public void moveSelectedShapes(double dx, double dy) {
        selectionManager.getSelectedShapes().move(dx, dy);
        notifyShapeChanged();
    }

    public void resizeSelectedShape(double x, double y) {
        Shape selectedShape = getSelectedShape();
        if (selectedShape != null) {
            selectedShape.setEnd(x, y);
            notifyShapeChanged();
        }
    }

    public void updateShape(Shape shape, double x, double y, double width, double height) {
        shape.setStart(x, y);
        shape.setEnd(x + width, y + height);
        notifyShapeChanged();
    }

    public void bringToFront(Shape shape) {
        shapes.remove(shape);
        shapes.add(shape);
        notifyShapeChanged();
    }

    public void sendToBack(Shape shape) {
        shapes.remove(shape);
        shapes.add(0, shape);
        notifyShapeChanged();
    }
}
