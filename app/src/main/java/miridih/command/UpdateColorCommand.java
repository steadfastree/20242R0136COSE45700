package miridih.command;

import java.awt.Color;

import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class UpdateColorCommand extends UndoableCommand {
    Shape shape;
    Color color;

    public UpdateColorCommand(CanvasModel canvasModel, Shape shape, Color color) {
        super(canvasModel);
        this.shape = shape;
        this.color = color;
    }

    @Override
    protected void doExecute() {
        canvasModel.updateColor(shape, color);
    }
}
