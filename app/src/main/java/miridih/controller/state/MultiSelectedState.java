package miridih.controller.state;

import miridih.common.manager.SelectionManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class MultiSelectedState extends ToolState {
  private final SelectionManager selectionManager = SelectionManager.getInstance();

    public MultiSelectedState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        canvasModel.setLastPoint(x, y);
        Shape clickedShape = canvasModel.clickShape(x, y);
        

        if(clickedShape != null){
          if(!selectionManager.getSelectedShapes().getChildren().contains(clickedShape)){
            selectionManager.clearSelectedShapes();
            selectionManager.selectShape(clickedShape);
            canvasController.setCurrentTool(Tool.SINGLE_SELECTED);
          } 
        } else{
            selectionManager.clearSelectedShapes();
            canvasController.setCurrentTool(Tool.SELECT);
          
        }
    }

    @Override
    public void mouseReleased(double x, double y) {
      canvasModel.setLastPoint(x, y);
    }

    @Override
    public void mouseDragged(double x, double y) {
      canvasModel.moveSelectedShapes(x - canvasModel.getLastX(), y - canvasModel.getLastY());
      canvasModel.setLastPoint(x, y);
    }

    @Override
    public void mouseClicked(double x, double y){
      canvasModel.setLastPoint(x, y);
      selectionManager.clearSelectedShapes();
      Shape clickedShape = canvasModel.clickShape(x, y);
      if(clickedShape != null){
        selectionManager.selectShape(clickedShape);
        canvasController.setCurrentTool(Tool.SINGLE_SELECTED);
      } else{
        canvasController.setCurrentTool(Tool.SELECT);
      }
    }
  
}
