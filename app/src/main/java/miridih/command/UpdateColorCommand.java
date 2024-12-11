package miridih.command;

import miridih.model.CanvasModel;

import java.awt.Color;

public class UpdateColorCommand extends UndoableCommand {
    Color color;

    public UpdateColorCommand(CanvasModel canvasModel, Color color) {
        super(canvasModel);
        this.color = color;
    }

    @Override
    protected void doExecute() {
        canvasModel.updateColor(color);
    }
}
