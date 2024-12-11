package miridih.command;

import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class UpdateSelectedShapeCommand extends UndoableCommand {
    Shape shape;
    double x, y, w, h;

    public UpdateSelectedShapeCommand(CanvasModel canvasModel, Shape shape, double x, double y, double w, double h) {
        super(canvasModel);
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    protected void doExecute() {
        if (shape != null) {
            canvasModel.updateShape(shape, x, y, w, h);
        }
    }

}
