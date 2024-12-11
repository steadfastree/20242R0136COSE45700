package miridih.controller.state;

import miridih.command.CommandInvoker;
import miridih.command.ResizeSelectedShapeCommand;
import miridih.common.manager.PointManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class ResizeState extends ToolState {
    public ResizeState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
    }

    @Override
    public void mouseReleased(double x, double y) {
      this.canvasController.setState(new SingleSelectedState(this.canvasController, this.canvasModel));
      PointManager.getInstance().setLastPoint(x, y);
    }

    @Override
    public void mouseDragged(double x, double y) {
      ResizeSelectedShapeCommand command = new ResizeSelectedShapeCommand(canvasModel, x, y);
      CommandInvoker.getInstance().executeCommand(command);
      PointManager.getInstance().setLastPoint(x, y);
    }

    @Override
    public void mouseClicked(double x, double y) {
    }
  
}
