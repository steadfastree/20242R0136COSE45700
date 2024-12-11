package miridih.command;

import miridih.controller.CanvasController;
import miridih.controller.state.Tool;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class SelectShapeCommand extends SelectCommand {
    public SelectShapeCommand(CanvasController canvasController, CanvasModel canvasModel, double x, double y) {
        super(canvasController, canvasModel, x, y);
    }

    @Override
    protected void doExecute() {
      Shape clickedShape = canvasModel.clickShape(this.x, this.y);
        if (clickedShape != null) {
            this.selectionManager.selectShape(clickedShape); // 선택된 도형 추가
            this.canvasController.setCurrentTool(Tool.SINGLE_SELECTED);
            // 이후 SingleSelectedState로 이동
        } else {
            this.selectionManager.clearSelectedShapes(); // 클릭한 곳이 비어있으면 선택 해제
            // startX, startY를 기록

        }
      
        if(this.selectionManager.getSelectedShapesSize() > 1) {
          this.canvasController.setCurrentTool(Tool.MULTI_SELECTED);
      }
      else if(this.selectionManager.getSelectedShapesSize() == 1) {
          this.canvasController.setCurrentTool(Tool.SINGLE_SELECTED);
      }
    }
  
}
