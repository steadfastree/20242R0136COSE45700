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

    @Override
    protected void doExecute() {
        List<Command> commandsCopy = new ArrayList<>(commands);
        for (Command command : commandsCopy) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        System.out.println(commands.size());
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}
