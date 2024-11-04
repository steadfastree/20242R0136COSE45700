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
        if (currentTool != tool) {
            selectedShapes.clear();
        }
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
        Shape clickedShape = clickShape(x, y);
        if (currentTool == Tool.SELECT) {
            // 빈 공간을 클릭한 경우
            if (clickedShape == null) {
                selectedShapes.clear();
                notifyShapeChanged();
            }
            // 새로운 도형을 클릭한 경우
            else if (!selectedShapes.contains(clickedShape)) {
                selectedShapes.clear();
                selectedShapes.add(clickedShape);
                notifyShapeChanged();
            }
            // 현재 선택된 도형을 다시 클릭한 경우는 아무 동작 하지 않음
        } 
        else if(currentTool == Tool.MULTI_SELECT){
            if(clickedShape != null){
                if(selectedShapes.contains(clickedShape)){
                    selectedShapes.remove(clickedShape);
                }
                else{
                    selectedShapes.add(clickedShape);
                }
            }
        }
        else {
            createShape();
        }
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

    public void createShape() {
        Shape newShape = ShapeFactory.createShape(currentTool);
        newShape.setStart(Math.min(startX, endX), Math.min(startY, endY));
        newShape.setEnd(Math.max(startX, endX), Math.max(startY, endY));
        if (currentTool != null) {
            newShape.setTool(currentTool);
            shapes.add(newShape);
            setCurrentTool(Tool.SELECT);
            selectedShapes.add(newShape);
            notifyShapeChanged();
        }
    }

    public Shape getSelectedShape() {
        return selectedShapes.isEmpty() ? null : selectedShapes.get(0);
    }

    public ArrayList<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public void moveSelectedShapes(double dx, double dy) {
        for(Shape shape : selectedShapes){
            shape.move(dx, dy);
        }
        notifyShapeChanged();
    }

    public void resizeSelectedShape(double x, double y) {
        Shape selectedShape = getSelectedShape();
        if(selectedShape != null){
            selectedShape.setEnd(x, y);
            notifyShapeChanged();
        }
    }
}
