package miridih.command;

import miridih.model.CanvasModel;

public class ResizeSelectedShapeCommand extends NotUndoableCommand {
    double x, y;

    public ResizeSelectedShapeCommand(CanvasModel canvasModel, double x, double y) {
        super(canvasModel);
        this.x = x;
        this.y = y;
    }

    @Override
    protected void doExecute() {
        this.canvasModel.resizeSelectedShape(this.x, this.y);
    }
  
}
