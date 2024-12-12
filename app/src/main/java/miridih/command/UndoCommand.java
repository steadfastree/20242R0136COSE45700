package miridih.command;

import miridih.model.CanvasModel;

public class UndoCommand extends UndoableCommand {
    private Command command;

    public UndoCommand(CanvasModel canvasModel, Command command) {
        super(canvasModel);
        this.command = command;
    }

    @Override
    public void doExecute() {
        command.undo();
    }
}
