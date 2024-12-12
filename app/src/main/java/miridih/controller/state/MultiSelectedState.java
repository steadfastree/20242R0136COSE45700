package miridih.controller.state;

import miridih.command.ClickShapeOnMultiSelectedCommand;
import miridih.command.CommandInvoker;
import miridih.command.MoveSelectedShapesByDraggingCommand;
import miridih.command.SelectShapeOnMultiSelectedCommand;
import miridih.common.manager.PointManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class MultiSelectedState extends ToolState {
  public MultiSelectedState(CanvasController canvasController, CanvasModel canvasModel) {
    super(canvasController, canvasModel);
  }

  @Override
  public void mousePressed(double x, double y) {
    PointManager.getInstance().setLastPoint(x, y);
    SelectShapeOnMultiSelectedCommand command = new SelectShapeOnMultiSelectedCommand(canvasController, canvasModel, x,
        y);
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
  public void mouseClicked(double x, double y) {
    PointManager.getInstance().setLastPoint(x, y);
    ClickShapeOnMultiSelectedCommand command = new ClickShapeOnMultiSelectedCommand(canvasController, canvasModel, x,
        y);
    CommandInvoker.getInstance().executeCommand(command);
  }
}
