package miridih.controller.state;

import java.awt.Color;

import miridih.command.CommandInvoker;
import miridih.command.EllipseDrawCommand;
import miridih.common.manager.PointManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class EllipseToolState extends ToolState {
    public EllipseToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        PointManager.getInstance().setLastPoint(x, y);
    }

    @Override
    public void mouseReleased(double x, double y) {
        EllipseDrawCommand command = new EllipseDrawCommand(canvasModel, x, y, Color.WHITE);
        CommandInvoker.getInstance().executeCommand(command);
    }

    @Override
    public void mouseDragged(double x, double y) {
    }

    @Override
    public void mouseClicked(double x, double y) {

    }
}
