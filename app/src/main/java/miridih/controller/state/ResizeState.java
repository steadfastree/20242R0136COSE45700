package miridih.controller.state;

import miridih.command.CommandInvoker;
import miridih.command.CompositeCommand;
import miridih.command.ResizeSelectedShapeCommand;
import miridih.common.manager.PointManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;

public class ResizeState extends ToolState {
  private CompositeCommand compositeCommand = null;

  public ResizeState(CanvasController canvasController, CanvasModel canvasModel) {
    super(canvasController, canvasModel);
    if (compositeCommand == null) {
      compositeCommand = new CompositeCommand(canvasModel);
    }
  }

  @Override
  public void mousePressed(double x, double y) {
  }

  @Override
  public void mouseReleased(double x, double y) {
    this.canvasController.setState(new SingleSelectedState(this.canvasController, this.canvasModel));
    PointManager.getInstance().setLastPoint(x, y);

    if (compositeCommand != null) {
      CommandInvoker.getInstance().executeCommand(compositeCommand);
      compositeCommand = null;
    }
  }

  @Override
  public void mouseDragged(double x, double y) {
    if (compositeCommand != null) {
      ResizeSelectedShapeCommand command = new ResizeSelectedShapeCommand(canvasModel, x, y);
      command.execute();
      compositeCommand.addCommand(command);
    }
    PointManager.getInstance().setLastPoint(x, y);
  }

  @Override
  public void mouseClicked(double x, double y) {
  }
}
