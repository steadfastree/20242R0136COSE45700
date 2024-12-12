package miridih.controller.state;

import miridih.command.CommandInvoker;
import miridih.command.SelectDraggedOutCommand;
import miridih.command.SelectDraggingCommand;
import miridih.command.SelectShapeCommand;
import miridih.common.manager.PointManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class SelectToolState extends ToolState {
    public SelectToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        PointManager.getInstance().setLastPoint(x, y);
        SelectShapeCommand command = new SelectShapeCommand(canvasController, canvasModel, x, y);
        CommandInvoker.getInstance().executeCommand(command);
    }

    @Override
    public void mouseReleased(double x, double y) {
        PointManager.getInstance().setLastPoint(x, y);
        SelectDraggedOutCommand command = new SelectDraggedOutCommand(canvasController, canvasModel, x, y);
        CommandInvoker.getInstance().executeCommand(command);
    }

    @Override
    public void mouseDragged(double x, double y) {
        SelectDraggingCommand command = new SelectDraggingCommand(canvasController, canvasModel, x, y);
        CommandInvoker.getInstance().executeCommand(command);
    }

    @Override
    public void mouseClicked(double x, double y) {
    }
}
