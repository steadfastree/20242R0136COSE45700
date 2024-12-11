package miridih.command;

import miridih.common.manager.PointManager;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;
import miridih.model.objects.factory.LineFactory;

public class LineDrawCommand extends DrawCommand {

    public LineDrawCommand(CanvasModel canvasModel, double x, double y) {
        super(canvasModel, x, y);
        this.shapeFactory = LineFactory.getInstance();
    }

    @Override
    public void doExecute() {
        Shape shape = this.shapeFactory.createShape();
        shape.setStart(PointManager.getInstance().getLastX(), PointManager.getInstance().getLastY());
        shape.setEnd(x, y);
        this.canvasModel.addShape(shape);
    }
}
