package miridih.command;

import miridih.controller.CanvasController;
import miridih.controller.state.Tool;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class SelectShapeOnMultiSelectedCommand extends SelectCommand {
  public SelectShapeOnMultiSelectedCommand(CanvasController canvasController, CanvasModel canvasModel, double x,
      double y) {
    super(canvasController, canvasModel, x, y);
  }

  @Override
  protected void doExecute() {
    Shape clickedShape = this.canvasModel.clickShape(x, y);
    if (clickedShape != null) {
      if (!this.selectionManager.getSelectedShapes().getChildren().contains(clickedShape)) {
        this.selectionManager.clearSelectedShapes();
        this.selectionManager.selectShape(clickedShape);
        this.canvasController.setCurrentTool(Tool.SINGLE_SELECTED);
      }
    } else {
      this.selectionManager.clearSelectedShapes();
      this.canvasController.setCurrentTool(Tool.SELECT);
    }
  }
}
