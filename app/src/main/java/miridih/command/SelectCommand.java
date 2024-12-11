package miridih.command;

import miridih.common.manager.SelectionManager;
import miridih.model.CanvasModel;

public abstract class SelectCommand extends NotUndoableCommand {
  protected final SelectionManager selectionManager;
  
    public SelectCommand(CanvasModel canvasModel) {
        super(canvasModel);
        this.selectionManager = SelectionManager.getInstance();
    }

    @Override
    protected void doExecute() {
    }
  
}
