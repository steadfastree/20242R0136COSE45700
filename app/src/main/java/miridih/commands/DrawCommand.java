package miridih.commands;

import miridih.model.CanvasModel;
import miridih.model.objects.factory.ShapeFactory;
import miridih.model.objects.Shape;

public abstract class DrawCommand extends UndoableCommand {
    double x, y;
    protected ShapeFactory shapeFactory;

    protected DrawCommand(CanvasModel canvasModel, double x, double y) {
        super(canvasModel);
        this.x = x;
        this.y = y;
    }

    @Override
    public void doExecute() {
        Shape shape = this.shapeFactory.createShape();
        shape.setStart(Math.min(canvasModel.getLastX(), x),
                Math.min(canvasModel.getLastY(), y));
        shape.setEnd(Math.max(canvasModel.getLastX(), x),
                Math.max(canvasModel.getLastY(), y));
        this.canvasModel.addShape(shape);
    }
}
