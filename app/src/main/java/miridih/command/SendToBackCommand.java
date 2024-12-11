package miridih.command;

import miridih.model.CanvasModel;

public class SendToBackCommand extends UndoableCommand {
    public SendToBackCommand(CanvasModel canvasModel) {
        super(canvasModel);
    }

    @Override
    public void doExecute() {
        canvasModel.sendToBack();
    }

}
