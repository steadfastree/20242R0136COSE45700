package miridih.controller.state;

import miridih.common.manager.SelectionManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class SelectToolState extends ToolState {
    private final SelectionManager selectionManager = SelectionManager.getInstance();

    public SelectToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        Shape clickedShape = canvasModel.clickShape(x, y);
        
        canvasModel.setStart(x, y);
        if (clickedShape != null) {
            selectionManager.selectShape(clickedShape); // 선택된 도형 추가
            canvasController.setCurrentTool(Tool.SINGLE_SELECTED);
            // 이후 SingleSelectedState로 이동
        } else {
            selectionManager.clearSelectedShapes(); // 클릭한 곳이 비어있으면 선택 해제
            // startX, startY를 기록

        }


        
    }

    @Override
    public void mouseReleased(double x, double y) {
        // 이 과정에서 selectedShapes의 길이가 0이 아니라면 multiSelectedState로 이동
        if(selectionManager.getSelectedShapesSize() > 1) {
            canvasController.setCurrentTool(Tool.MULTI_SELECTED);
        }
        else if(selectionManager.getSelectedShapesSize() == 1) {
            canvasController.setCurrentTool(Tool.SINGLE_SELECTED);
        }

    }

    @Override
    public void mouseDragged(double x, double y, double dx, double dy) {
        // 드래그 영역의 시작점과 끝점을 계산
        double startX = Math.min(canvasModel.getStartX(), x);
        double startY = Math.min(canvasModel.getStartY(), y);
        double endX = Math.max(canvasModel.getStartX(), x);
        double endY = Math.max(canvasModel.getStartY(), y);

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

