package miridih.command;

import java.awt.Color;

import miridih.common.manager.PointManager;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;
import miridih.model.objects.factory.ShapeFactory;

public abstract class DrawCommand extends UndoableCommand {
    double x, y;
    Color color;
    protected ShapeFactory shapeFactory;

    protected DrawCommand(CanvasModel canvasModel, double x, double y, Color color) {
        super(canvasModel);
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void doExecute() {
        Shape shape = this.shapeFactory.createShape();
        shape.setColor(color);
        shape.setStart(Math.min(PointManager.getInstance().getLastX(), x),
                Math.min(PointManager.getInstance().getLastY(), y));
        shape.setEnd(Math.max(PointManager.getInstance().getLastX(), x),
                Math.max(PointManager.getInstance().getLastY(), y));
        this.canvasModel.addShape(shape);
    }
}
