package miridih.controller.state;

import miridih.command.CommandInvoker;
import miridih.command.SelectDraggedOutCommand;
import miridih.command.SelectDraggingCommand;
import miridih.common.manager.PointManager;
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
        PointManager.getInstance().setLastPoint(x, y);
        Shape clickedShape = canvasModel.clickShape(x, y);
        
        
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
        PointManager.getInstance().setLastPoint(x, y);
        SelectDraggedOutCommand command = new SelectDraggedOutCommand(canvasController, canvasModel, x, y);
        CommandInvoker.getInstance().executeCommand(command);
        // 이 과정에서 selectedShapes의 길이가 0이 아니라면 multiSelectedState로 이동
        

    }

    @Override
    public void mouseDragged(double x, double y) {
        SelectDraggingCommand command = new SelectDraggingCommand(canvasController, canvasModel, x, y);
        CommandInvoker.getInstance().executeCommand(command);
    }
    
    @Override
    public void mouseClicked(double x, double y){
      
    }

}

