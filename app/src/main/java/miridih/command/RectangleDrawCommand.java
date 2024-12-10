package miridih.command;

import miridih.model.CanvasModel;
import miridih.model.objects.factory.RectangleFactory;

public class RectangleDrawCommand extends DrawCommand {
    public RectangleDrawCommand(CanvasModel canvasModel, double x, double y) {
        super(canvasModel, x, y);
        this.shapeFactory = RectangleFactory.getInstance();
    }
}
