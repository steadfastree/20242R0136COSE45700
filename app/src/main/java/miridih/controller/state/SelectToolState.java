package miridih.controller.state;

import miridih.common.manager.SelectionManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class SelectToolState extends ToolState {
    private final SelectionManager selectionManager;

    public SelectToolState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
        this.selectionManager = SelectionManager.getInstance(); // 싱글톤
    }

    @Override
    public void mousePressed(double x, double y) {
        Shape clickedShape = canvasModel.clickShape(x, y);
        
        if (clickedShape != null) {
            selectionManager.selectShape(clickedShape); // 선택된 도형 추가
        } else {
            selectionManager.clearSelectedShapes(); // 클릭한 곳이 비어있으면 선택 해제
        }

        // press 했을때 도형이면 선택에 추가, 아니면 선택 해제
        // 릴리즈일 때 
        
        // if (selectedShape != null && selectedShape.isOnHandle(x, y)) {
        //     canvasController.isResizing = true;
        // } else {
        //     canvasController.isDragging = true;
        // } 일단 배제

        canvasModel.setStart(x, y);
    }

    @Override
    public void mouseReleased(double x, double y) {
        double dx = x - canvasModel.getStartX();
        double dy = y - canvasModel.getStartY();

        canvasModel.moveSelectedShapes(dx, dy);
        canvasModel.setEnd(x,y);

        // Shape clickedShape = canvasModel.clickShape(x, y);
        // ArrayList<Shape> selectedShapes = canvasModel.getSelectedShapes();
        // if (clickedShape != null) {
        //         if (selectedShapes.contains(clickedShape)) {
        //             selectedShapes.remove(clickedShape);
        //         } else {
        //             selectedShapes.add(clickedShape);
        //         }
        //     }
    }

    @Override
    public void mouseDragged(double x, double y, double dx, double dy) {
        // System.out.println("SelectToolState mouseDragged");
        // selectionManager.getSelectedShapes().move(dx, dy);
    }

}

