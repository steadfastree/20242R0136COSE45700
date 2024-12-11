package miridih.command;

import miridih.model.CanvasModel;

public class BringToFrontCommand extends UndoableCommand {
    public BringToFrontCommand(CanvasModel canvasModel) {
        super(canvasModel);
    }

    @Override
    public void doExecute() {
        canvasModel.bringToFront();
    }
}
