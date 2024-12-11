package miridih.command;

import miridih.controller.CanvasController;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class SelectDraggingCommand extends SelectCommand {

    public SelectDraggingCommand(CanvasController canvasController, CanvasModel canvasModel, double x, double y) {
        super(canvasController, canvasModel, x, y);
    }

    @Override
    protected void doExecute() {
        double startX = Math.min(this.pointManager.getLastX(), x);
        double startY = Math.min(this.pointManager.getLastY(), y);
        double endX = Math.max(this.pointManager.getLastX(), x);
        double endY = Math.max(this.pointManager.getLastY(), y);

        // 드래그 영역 내의 도형 선택
        for (Shape shape : canvasModel.getShapes()) {
            // 도형의 각 점을 확인하여 드래그 영역에 포함되는지 체크
            if (shape.contains(startX, startY) || shape.contains(endX, endY) ||
                shape.contains(startX, endY) || shape.contains(endX, startY)) {
                selectionManager.selectShape(shape);
            }
        }
    }
  
}
