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
    private double startX, startY, endX, endY;

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

    // private void notifyToolChanged() {
    //     for (ToolChangeListener listener : toolChangeListeners) {
    //         listener.onToolChanged(currentTool);
    //     }
    // }

    // public void setCurrentTool(Tool tool) {
    //     if (currentTool != tool) {
    //         // selectedShapes.clear(); tool 관리는 controller에서 담당
    //     }
    //     currentTool = tool;
    //     notifyToolChanged();
    // }

    // public Tool getCurrentTool() {
    //     return currentTool;
    // }

    public void setStart(double x, double y) {
        startX = x;
        startY = y;
    }

    public void setEnd(double x, double y) {
        endX = x;
        endY = y;
    }

    // Select는 View의 상태를 바꾸는 것이므로, Select State에서 별개로 Selectionmanager를 이용해 처리.

    // public void handleClick(double x, double y) { //마우스 이벤트 처리가 state에서 이루어지도록.
    //     Shape clickedShape = clickShape(x, y);
    //     if (currentTool == Tool.SELECT) {
    //         // 빈 공간을 클릭한 경우
    //         if (clickedShape == null) {
    //             selectedShapes.clear();
    //         }
    //         // 새로운 도형을 클릭한 경우
    //         else if (!selectedShapes.contains(clickedShape)) {
    //             selectedShapes.clear();
    //             selectedShapes.add(clickedShape);
    //         }
    //         // 현재 선택된 도형을 다시 클릭한 경우는 아무 동작 하지 않음
    //     } else if (currentTool == Tool.MULTI_SELECT) {
    //         if (clickedShape != null) {
    //             if (selectedShapes.contains(clickedShape)) {
    //                 selectedShapes.remove(clickedShape);
    //             } else {
    //                 selectedShapes.add(clickedShape);
    //             }
    //         }
    //     } else {
    //     }
    //     notifyShapeChanged();
    // }

    public Shape clickShape(double x, double y) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.contains(x, y)) {
                return shape;
            }
        }
        return null;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
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
