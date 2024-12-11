package miridih.controller.state;

import java.awt.Color;

import miridih.command.CommandInvoker;
import miridih.command.RectangleDrawCommand;
import miridih.common.manager.PointManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class RectangleToolState extends ToolState {
    public RectangleToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        PointManager.getInstance().setLastPoint(x, y);
    }

    @Override
    public void mouseReleased(double x, double y) {
        RectangleDrawCommand command = new RectangleDrawCommand(canvasModel, x, y, Color.WHITE);
        CommandInvoker.getInstance().executeCommand(command);
    }

    @Override
    public void mouseDragged(double x, double y) {
    }

    @Override
    public void mouseClicked(double x, double y) {
    }

}
