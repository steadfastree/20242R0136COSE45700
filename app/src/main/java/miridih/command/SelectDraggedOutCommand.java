package miridih.command;

import miridih.controller.CanvasController;
import miridih.controller.state.Tool;
import miridih.model.CanvasModel;

public class SelectDraggedOutCommand extends SelectCommand {
    public SelectDraggedOutCommand(CanvasController canvasController, CanvasModel canvasModel, double x, double y) {
        super(canvasController, canvasModel, x, y);
    }

    @Override
    protected void doExecute() {
        if (this.selectionManager.getSelectedShapesSize() > 1) {
            canvasController.setCurrentTool(Tool.MULTI_SELECTED);
        } else if (this.selectionManager.getSelectedShapesSize() == 1) {
            canvasController.setCurrentTool(Tool.SINGLE_SELECTED);
        }
    }
}
