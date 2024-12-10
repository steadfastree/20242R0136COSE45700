package miridih.controller.state;

import miridih.controller.CanvasController;
import miridih.model.CanvasModel;
import miridih.model.objects.factory.EllipseFactory;
import miridih.model.objects.factory.RectangleFactory;
import miridih.model.objects.Shape;


public class EllipseToolState extends ToolState {
    public EllipseToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        canvasModel.setStart(x,y);
    }

    @Override
    public void mouseReleased(double x, double y) {
        canvasModel.setEnd(x,y);

        Shape ellipseShape = new EllipseFactory().createShape();
        ellipseShape.setStart(Math.min(canvasModel.getStartX(), canvasModel.getEndX()),
                              Math.min(canvasModel.getStartY(), canvasModel.getEndY()));
        ellipseShape.setEnd(Math.max(canvasModel.getStartX(), canvasModel.getEndX()),
                            Math.max(canvasModel.getStartY(), canvasModel.getEndY()));
        
        // Model은 단순히 도형을 저장하고 관리하는 역할만 수행
        canvasModel.addShape(ellipseShape);
    }

    @Override
    public void mouseDragged(double x, double y, double dx, double dy) {
        // System.out.println("EllipseToolState mouseDragged");
    }
}
