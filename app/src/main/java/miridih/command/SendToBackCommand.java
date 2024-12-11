package miridih.command;

import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class SendToBackCommand extends UndoableCommand {
    Shape shape;

    public SendToBackCommand(CanvasModel canvasModel, Shape shape) {
        super(canvasModel);
        this.shape = shape;
    }

    @Override
    public void doExecute() {
        canvasModel.sendToBack(shape);
    }

}
