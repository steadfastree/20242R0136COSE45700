package miridih.model;

import java.util.ArrayList;
import java.util.List;

import miridih.factory.ShapeFactory;
import miridih.objects.Shape;
import miridih.objects.Tool;
import miridih.observer.ShapeChangeListener;

public class CanvasModel {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
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

    public Tool getCurrentTool() {
        return currentTool;
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
        Shape selectedShape = selectShape(x, y);
        if (currentTool == Tool.SELECT) {
            selectedShapes.clear();
            if(selectedShape != null){
                selectedShapes.add(selectedShape);
            }
            
        } 
        else if(currentTool == Tool.MULTI_SELECT){
            if(selectedShape != null){
                if(selectedShapes.contains(selectedShape)){
                    selectedShapes.remove(selectedShape);
                }
                else{
                    selectedShapes.add(selectedShape);
                }
            }
        }
        else {
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
        newShape.setStart(Math.min(startX, endX), Math.min(startY, endY));
        newShape.setEnd(Math.max(startX, endX), Math.max(startY, endY));
        if (currentTool != null) {
            newShape.setTool(currentTool);
            shapes.add(newShape);
            selectedShapes.add(newShape);
            setCurrentTool(Tool.SELECT);
            notifyShapeChanged();
        }
    }

    public Shape getSelectedShape() {
        return selectedShapes.get(0);
    }

    public ArrayList<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public void moveSelectedShape(double dx, double dy) {
        if (selectedShapes.get(0) != null) {
            selectedShapes.get(0).move(dx, dy);
            notifyShapeChanged();
        }
    }

    public void moveSelectedShapes(double dx, double dy) {
        for(Shape shape : selectedShapes){
            shape.move(dx, dy);
        }
        notifyShapeChanged();
    }

    public void resizeSelectedShape(double x, double y) {
        if(selectedShapes.get(0) != null){
            selectedShapes.get(0).setEnd(x, y);
            notifyShapeChanged();
        }
    }
}
