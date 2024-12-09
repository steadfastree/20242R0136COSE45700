package miridih.controller.state;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import miridih.controller.CanvasController;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class MultiSelectToolState extends ToolState {
    public MultiSelectToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        canvasController.isDragging = true;
        canvasModel.setStart(x, y);

        System.out.println("MultiSelectToolState mousePressed");
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

    @Override
    public void keyPressed(int keyCode) {
        
    }

    @Override
    public void keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_CONTROL || keyCode == KeyEvent.VK_META) {
          canvasController.setCurrentTool(Tool.SELECT);
          System.out.println("set to select");
        }
        
    }
}
