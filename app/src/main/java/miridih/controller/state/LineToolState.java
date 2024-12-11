package miridih.controller.state;

import java.awt.Color;

import miridih.command.CommandInvoker;
import miridih.command.LineDrawCommand;
import miridih.common.manager.PointManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class LineToolState extends ToolState {
    public LineToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        PointManager.getInstance().setLastPoint(x, y);
    }

    @Override
    public void mouseReleased(double x, double y) {
        LineDrawCommand command = new LineDrawCommand(canvasModel, x, y, Color.BLACK);
        CommandInvoker.getInstance().executeCommand(command);
    }

    @Override
    public void mouseDragged(double x, double y) {
    }

    @Override
    public void mouseClicked(double x, double y) {

    }
}
