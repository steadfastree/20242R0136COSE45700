package miridih.controller.state;

import miridih.controller.CanvasController;
import miridih.model.CanvasModel;
import miridih.model.objects.factory.RectangleFactory;
import miridih.model.objects.Shape;

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
        
    }

    @Override
    public void keyPressed(int keyCode) {
        System.out.println("RectangleToolState keyPressed");
    }

    @Override
    public void keyReleased(int keyCode) {
        System.out.println("RectangleToolState keyReleased");
    }
}
