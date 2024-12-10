package miridih.controller.state;

import miridih.commands.CommandInvoker;
import miridih.commands.EllipseDrawCommand;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;
import miridih.model.objects.factory.EllipseFactory;
import miridih.model.objects.Shape;

public class EllipseToolState extends ToolState {
    public EllipseToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        canvasModel.setLastPoint(x, y);
    }

    @Override
    public void mouseReleased(double x, double y) {
        // canvasModel.setLastPoint(x, y);

        // Shape ellipseShape = EllipseFactory.getInstance().createShape();
        // ellipseShape.setStart(Math.min(canvasModel.getLastX(), x),
        // Math.min(canvasModel.getLastY(), y));
        // ellipseShape.setEnd(Math.max(canvasModel.getLastX(), x),
        // Math.max(canvasModel.getLastY(), y));

        // // Model은 단순히 도형을 저장하고 관리하는 역할만 수행
        // canvasModel.addShape(ellipseShape);

        EllipseDrawCommand command = new EllipseDrawCommand(canvasModel, x, y);
        CommandInvoker.getInstance().executeCommand(command);
    }

    @Override
    public void mouseDragged(double x, double y) {
        // System.out.println("EllipseToolState mouseDragged");
    }

    @Override
    public void mouseClicked(double x, double y) {

    }
}
