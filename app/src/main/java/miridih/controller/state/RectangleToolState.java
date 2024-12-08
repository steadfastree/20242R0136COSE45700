package miridih.controller.state;

import java.awt.Rectangle;

import miridih.controller.CanvasController;
import miridih.factory.RectangleFactory;
import miridih.model.CanvasModel;
import miridih.objects.Shape;

public class RectangleToolState extends ToolState {
    public RectangleToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        canvasModel.setStart(x,y);
    }

    @Override
    public void mouseReleased(double x, double y) {
        canvasModel.setEnd(x, y);
        
        // RectangleToolState에서 직접 Rectangle 도형을 생성
        Shape rectangleShape = new RectangleFactory().createShape();
        rectangleShape.setStart(Math.min(canvasModel.getStartX(), canvasModel.getEndX()),
                              Math.min(canvasModel.getStartY(), canvasModel.getEndY()));
        rectangleShape.setEnd(Math.max(canvasModel.getStartX(), canvasModel.getEndX()),
                            Math.max(canvasModel.getStartY(), canvasModel.getEndY()));
        
        // Model은 단순히 도형을 저장하고 관리하는 역할만 수행
        canvasModel.addShape(rectangleShape);
    }

    @Override
    public void mouseDragged(double x, double y, double dx, double dy) {
        System.out.println(" RectangleToolState mouseDragged");
    }
}
