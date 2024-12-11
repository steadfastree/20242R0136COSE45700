package miridih.command;

import java.awt.Color;

import miridih.model.CanvasModel;
import miridih.model.objects.factory.EllipseFactory;

public class EllipseDrawCommand extends DrawCommand {

    public EllipseDrawCommand(CanvasModel canvasModel, double x, double y, Color color) {
        super(canvasModel, x, y, color);
        this.shapeFactory = EllipseFactory.getInstance();
    }
}
