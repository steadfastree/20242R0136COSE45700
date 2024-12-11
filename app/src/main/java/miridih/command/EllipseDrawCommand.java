package miridih.command;

import miridih.model.CanvasModel;
import miridih.model.objects.factory.EllipseFactory;

public class EllipseDrawCommand extends DrawCommand {

    public EllipseDrawCommand(CanvasModel canvasModel, double x, double y) {
        super(canvasModel, x, y);
        this.shapeFactory = EllipseFactory.getInstance();
    }
}
