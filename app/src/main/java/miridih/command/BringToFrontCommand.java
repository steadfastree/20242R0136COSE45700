package miridih.command;

import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class BringToFrontCommand extends UndoableCommand {
    Shape shape;

    public BringToFrontCommand(CanvasModel canvasModel, Shape shape) {
        super(canvasModel);
        this.shape = shape;
    }

    @Override
    public void doExecute() {
        canvasModel.bringToFront(shape);
    }
}
