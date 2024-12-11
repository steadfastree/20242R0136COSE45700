package miridih.command;

import java.awt.Color;

import miridih.model.CanvasModel;
import miridih.model.objects.factory.RectangleFactory;

public class RectangleDrawCommand extends DrawCommand {
    public RectangleDrawCommand(CanvasModel canvasModel, double x, double y, Color color) {
        super(canvasModel, x, y, color);
        this.shapeFactory = RectangleFactory.getInstance();
    }
}
