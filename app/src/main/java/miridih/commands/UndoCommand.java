package miridih.commands;

import miridih.model.CanvasModel;

public class UndoCommand extends UndoableCommand {

    private Command undidCommand;

    public UndoCommand(CanvasModel canvasModel, Command command) {
        super(canvasModel);
        this.undidCommand = command;
    }

    @Override
    public void doExecute() {
        undidCommand.undo();
    }
}
