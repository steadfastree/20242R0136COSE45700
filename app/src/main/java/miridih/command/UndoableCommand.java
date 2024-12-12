package miridih.command;

import miridih.model.CanvasModel;
import miridih.model.Memento;

public abstract class UndoableCommand implements Command {
    Memento memento;
    protected CanvasModel canvasModel;

    protected UndoableCommand(CanvasModel canvasModel) {
        this.canvasModel = canvasModel;
    }

    @Override
    public void execute() {
        this.memento = new Memento(this.canvasModel);
        doExecute();
    }

    @Override
    public void undo() {
        this.memento.restore();
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

    protected abstract void doExecute();
}
