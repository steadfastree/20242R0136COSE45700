package miridih.command;

import miridih.controller.CanvasController;
import miridih.controller.state.Tool;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class ClickShapeOnMultiSelectedCommand extends SelectCommand {
    public ClickShapeOnMultiSelectedCommand(CanvasController canvasController, CanvasModel canvasModel, double x, double y) {
        super(canvasController, canvasModel, x, y);
    }

    @Override
    protected void doExecute() {
      this.selectionManager.clearSelectedShapes();
      Shape clickedShape = this.canvasModel.clickShape(x, y);
      if(clickedShape != null){
        this.selectionManager.selectShape(clickedShape);
        this.canvasController.setCurrentTool(Tool.SINGLE_SELECTED);
      } else{
        this.canvasController.setCurrentTool(Tool.SELECT);
      }
    }
  
}
