package miridih.command;

public interface Command {
    default void undo() {
    };

    void execute();

    default boolean isUndoable() {
        return false;
    };
}
