package miridih.controller;

import java.util.ArrayList;

import miridih.controller.state.Tool;
import miridih.controller.state.ToolState;
import miridih.model.CanvasModel;
import miridih.objects.Shape;
import miridih.observer.ShapeChangeListener;
import miridih.observer.ToolChangeListener;

public class CanvasController {
    private final CanvasModel canvasModel;
    private boolean isResizing = false;
    private boolean isDragging = false;
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

    public void mousePressed(double x, double y) {

        if (getCurrentTool() == Tool.SELECT) {
            Shape selectedShape = getSelectedShape();
            if (selectedShape != null && selectedShape.isOnHandle(x, y)) {
                isResizing = true;
            } else {
                isDragging = true;
            }
        }
        if (getCurrentTool() == Tool.MULTI_SELECT) {
            isDragging = true;
        }
        canvasModel.setStart(x, y);
    }

    public void mouseReleased(double x, double y) {
        isResizing = false;
        isDragging = false;
        canvasModel.setEnd(x, y);
        canvasModel.handleClick(x, y);
    }

    public void mouseDragged(double x, double y, double dx, double dy) {
        if (isResizing) {
            resizeSelectedShape(x, y);
        } else if (isDragging) {
            moveSelectedShapes(dx, dy);
        }
    }

    public void setCurrentTool(Tool tool) {
        canvasModel.setCurrentTool(tool);
    }

    public Tool getCurrentTool() {
        return canvasModel.getCurrentTool();
    }

    public ArrayList<Shape> getShapes() {
        return canvasModel.getShapes();
    }

    public Shape getSelectedShape() {
        return canvasModel.getSelectedShape();
    }

    public ArrayList<Shape> getSelectedShapes() {
        return canvasModel.getSelectedShapes();
    }

    public void resizeSelectedShape(double x, double y) {
        canvasModel.resizeSelectedShape(x, y);
    }

    public void moveSelectedShapes(double dx, double dy) {
        canvasModel.moveSelectedShapes(dx, dy);
    }

    public void updateSelectedShape(double x, double y, double width, double height) {
        Shape selectedShape = getSelectedShape();
        if (selectedShape != null) {
            canvasModel.updateShape(selectedShape, x, y, width, height);
        }
    }

    public void bringToFront(Shape shape) {
        canvasModel.bringToFront(shape);
    }

    public void sendToBack(Shape shape) {
        canvasModel.sendToBack(shape);
    }
}
