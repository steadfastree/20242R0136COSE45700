package miridih.controller.state;

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
        // // RectangleToolState에서 직접 Rectangle 도형을 생성
        // Shape rectangleShape = RectangleFactory.getInstance().createShape();
        // rectangleShape.setStart(Math.min(canvasModel.getLastX(), x),
        // Math.min(canvasModel.getLastY(), y));
        // rectangleShape.setEnd(Math.max(canvasModel.getLastX(), x),
        // Math.max(canvasModel.getLastY(), y));

        // // Model은 단순히 도형을 저장하고 관리하는 역할만 수행
        // canvasModel.addShape(rectangleShape);

        RectangleDrawCommand command = new RectangleDrawCommand(canvasModel, x, y);
        CommandInvoker.getInstance().executeCommand(command);
    }

    @Override
    public void mouseDragged(double x, double y) {
        // System.out.println("RectangleToolState mouseDragged");
    }

    @Override
    public void mouseClicked(double x, double y) {

    }

}
