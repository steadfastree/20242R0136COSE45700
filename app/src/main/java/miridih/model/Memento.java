package miridih.model;

public class Memento {
    private String backup;
    private CanvasModel canvasModel;

    public Memento(CanvasModel canvasModel) {
        this.canvasModel = canvasModel;
        this.backup = canvasModel.backup();
    }

    public void restore() {
        canvasModel.restore(backup);
    }
}
