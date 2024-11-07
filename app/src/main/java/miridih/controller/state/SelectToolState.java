package miridih.controller.state;

import java.util.ArrayList;

import miridih.controller.CanvasController;
import miridih.model.CanvasModel;
import miridih.objects.Shape;

public class SelectToolState extends ToolState {
    public SelectToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        Shape selectedShape = canvasModel.getSelectedShape();
        
        if (selectedShape != null && selectedShape.isOnHandle(x, y)) {
            canvasController.isResizing = true;
        } else {
            canvasController.isDragging = true;
        }
        canvasModel.setStart(x, y);
    }

    @Override
    public void mouseReleased(double x, double y) {
        Shape clickedShape = canvasModel.clickShape(x, y);
        ArrayList<Shape> selectedShapes = canvasModel.getSelectedShapes();
        if (clickedShape != null) {
                if (selectedShapes.contains(clickedShape)) {
                    selectedShapes.remove(clickedShape);
                } else {
                    selectedShapes.add(clickedShape);
                }
            }
    }

    @Override
    public void mouseDragged(double x, double y, double dx, double dy) {
      if (canvasController.isDragging) {
        canvasModel.moveSelectedShapes(dx, dy);
      }
      else if(canvasController.isResizing) {
        canvasModel.resizeSelectedShape(dx, dy);
      }
    }
}

