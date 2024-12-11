package miridih.command;

import miridih.model.CanvasModel;

public class UpdateSelectedShapeCommand extends UndoableCommand {
    double x, y, w, h;

    public UpdateSelectedShapeCommand(CanvasModel canvasModel, double x, double y, double w, double h) {
        super(canvasModel);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    protected void doExecute() {
        canvasModel.updateShape(x, y, w, h);
    }

}
