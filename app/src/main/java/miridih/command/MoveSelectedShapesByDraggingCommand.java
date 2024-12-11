package miridih.command;

import miridih.common.manager.PointManager;
import miridih.model.CanvasModel;

public class MoveSelectedShapesByDraggingCommand extends NotUndoableCommand {
  protected final PointManager pointManager;
  double x, y;

  public MoveSelectedShapesByDraggingCommand(CanvasModel canvasModel, double x, double y) {
    super(canvasModel);
    this.x = x;
    this.y = y;
    this.pointManager = PointManager.getInstance();
  }

  @Override
  public void doExecute() {
    this.canvasModel.moveSelectedShapes(x - pointManager.getLastX(), y - pointManager.getLastY());
  }
}
