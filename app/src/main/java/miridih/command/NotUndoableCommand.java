package miridih.command;

import miridih.model.CanvasModel;

public abstract class NotUndoableCommand implements Command {
    protected CanvasModel canvasModel;

    protected NotUndoableCommand(CanvasModel canvasModel) {
        this.canvasModel = canvasModel;
    }

    @Override
    public void execute() {
        doExecute();
    }

    @Override
    public boolean isUndoable() {
        return false; 
    }

    protected abstract void doExecute();
}