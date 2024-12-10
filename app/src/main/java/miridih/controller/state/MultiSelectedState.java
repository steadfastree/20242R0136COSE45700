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
        
    }

    @Override
    public void mouseDragged(double x, double y, double dx, double dy) {
      canvasModel.moveSelectedShapes(dx, dy);
    }
  
}
