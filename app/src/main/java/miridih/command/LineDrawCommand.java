package miridih.command;

import miridih.model.CanvasModel;
import miridih.model.objects.factory.LineFactory;

public class LineDrawCommand extends DrawCommand {

    public LineDrawCommand(CanvasModel canvasModel, double x, double y) {
        super(canvasModel, x, y);
        this.shapeFactory = LineFactory.getInstance();
    }
}
