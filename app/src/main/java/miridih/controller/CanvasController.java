package miridih.controller;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import miridih.model.CanvasModel;
import miridih.objects.Shape;
import miridih.objects.Tool;
import miridih.observer.ShapeChangeListener;

public class CanvasController extends MouseAdapter {
    private final CanvasModel canvasModel;
    private boolean isResizing = false;
    private boolean isDragging = false;

    public CanvasController(CanvasModel model) {
        canvasModel = model;
    }

    public void addShapeChangeListener(ShapeChangeListener listener) {
        canvasModel.addShapeChangeListener(listener);
    }

    public void mousePressed(double x, double y) {
        if(getCurrentTool() == Tool.SELECT ){
            Shape selectedShape = getSelectedShape();
            if(selectedShape != null && selectedShape.isOnHandle(x, y)){
                isResizing = true;
            }
            else{
                isDragging = true;
            }
        }
        if(getCurrentTool() == Tool.MULTI_SELECT){
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

    public void moveSelectedShape(double dx, double dy) {
        canvasModel.moveSelectedShape(dx, dy);
    }

    public void moveSelectedShapes(double dx, double dy) {
        canvasModel.moveSelectedShapes(dx, dy);
    }
}
