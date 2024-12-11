package miridih.controller.state;

import miridih.command.CommandInvoker;
import miridih.command.MoveSelectedShapesByDraggingCommand;
import miridih.command.SelectShapeOnMultiSelectedCommand;
import miridih.common.manager.PointManager;
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
        PointManager.getInstance().setLastPoint(x, y);
        SelectShapeOnMultiSelectedCommand command = new SelectShapeOnMultiSelectedCommand(canvasController, canvasModel, x, y);
        CommandInvoker.getInstance().executeCommand(command);
    }

    @Override
    public void mouseReleased(double x, double y) {
      PointManager.getInstance().setLastPoint(x, y);
    }

    @Override
    public void mouseDragged(double x, double y) {
      MoveSelectedShapesByDraggingCommand command = new MoveSelectedShapesByDraggingCommand(canvasModel, x, y);
      CommandInvoker.getInstance().executeCommand(command);
      PointManager.getInstance().setLastPoint(x, y);
    }

    @Override
    public void mouseClicked(double x, double y){
      PointManager.getInstance().setLastPoint(x, y);
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
