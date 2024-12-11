package miridih.command;

import miridih.common.manager.PointManager;
import miridih.common.manager.SelectionManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public abstract class SelectCommand extends NotUndoableCommand {
  double x, y;
  protected final SelectionManager selectionManager;
  protected final PointManager pointManager;
  protected final CanvasController canvasController;

  
    public SelectCommand(CanvasController canvasController, CanvasModel canvasModel, double x, double y) {
        super(canvasModel);
        this.canvasController = canvasController;
        this.selectionManager = SelectionManager.getInstance();
        this.pointManager = PointManager.getInstance();
        this.x = x;
        this.y = y;
    }

    @Override
    protected void doExecute() {
    }
  
}
