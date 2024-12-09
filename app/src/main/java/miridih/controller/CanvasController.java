package miridih.controller;

import java.util.ArrayList;

import miridih.controller.state.EllipseToolState;
import miridih.controller.state.MultiSelectToolState;
import miridih.controller.state.RectangleToolState;
import miridih.controller.state.SelectToolState;
import miridih.controller.state.Tool;
import miridih.controller.state.ToolState;
import miridih.model.CanvasModel;
import miridih.objects.Shape;
import miridih.observer.ShapeChangeListener;
import miridih.observer.ToolChangeListener;

public class CanvasController {
    private final CanvasModel canvasModel;
    public boolean isResizing = false;
    public boolean isDragging = false; // Resize, Dragging 여부는 State로 관리
    private ToolState currentToolState;

    public CanvasController(CanvasModel model) {
        canvasModel = model;
    }

    public void addShapeChangeListener(ShapeChangeListener listener) {
        canvasModel.addShapeChangeListener(listener);
    }

    public void addToolChangeListener(ToolChangeListener listener) {
        canvasModel.addToolChangeListener(listener);
    }

    // 마우스 이벤트 처리

    public void mousePressed(double x, double y) {
        currentToolState.mousePressed(x, y);
    }

    public void mouseReleased(double x, double y) {
        currentToolState.mouseReleased(x, y);
    }

    public void mouseDragged(double x, double y, double dx, double dy) {
        currentToolState.mouseDragged(x, y, dx, dy);
    }

    // 상태 변경

    public void setCurrentTool(Tool tool) {
        switch (tool) {
            case RECTANGLE:
                currentToolState = new RectangleToolState(this, canvasModel);
                break;
            case ELLIPSE:
                currentToolState = new EllipseToolState(this, canvasModel);
                break;
            case MULTI_SELECT:
                currentToolState = new MultiSelectToolState(this, canvasModel);
                break;
            case SELECT:
                currentToolState = new SelectToolState(this, canvasModel);
                break;
        }
    }

    // 도형 접근
    public ArrayList<Shape> getShapes() {
        return canvasModel.getShapes();
    }

    public Shape getSelectedShape() {
        return canvasModel.getSelectedShape();
    }

    public ArrayList<Shape> getSelectedShapes() {
        return canvasModel.getSelectedShapes();
    }

    // 도형 크기 변경
    public void resizeSelectedShape(double x, double y) {
        canvasModel.resizeSelectedShape(x, y);
    }


    // 도형 이동
    public void moveSelectedShapes(double dx, double dy) {
        canvasModel.moveSelectedShapes(dx, dy);
    }

    // 도형 업데이트
    public void updateSelectedShape(double x, double y, double width, double height) {
        Shape selectedShape = getSelectedShape();
        if (selectedShape != null) {
            canvasModel.updateShape(selectedShape, x, y, width, height);
        }
    }

    // 도형 순서 변경
    public void bringToFront(Shape shape) {
        canvasModel.bringToFront(shape);
    }

    public void sendToBack(Shape shape) {
        canvasModel.sendToBack(shape);
    }
}
