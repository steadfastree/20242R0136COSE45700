package miridih.command;

import java.util.ArrayList;
import java.util.List;

import miridih.model.CanvasModel;

public class CompositeCommand extends UndoableCommand {
    private final List<Command> commands = new ArrayList<>();

    public CompositeCommand(CanvasModel canvasModel) {
        super(canvasModel);
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public boolean canUndo() {
        return commands.size() > 0;
    }

    @Override
    protected void doExecute() {
        commands.getLast().execute();
    }

    @Override
    public void undo() {
        System.out.println(commands.size());
        commands.getFirst().undo();
    }
}
