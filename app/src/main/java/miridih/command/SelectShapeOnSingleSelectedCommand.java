package miridih.command;

import miridih.controller.CanvasController;
import miridih.controller.state.Tool;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class SelectShapeOnSingleSelectedCommand extends SelectCommand {
  public SelectShapeOnSingleSelectedCommand(CanvasController canvasController, CanvasModel canvasModel, double x,
      double y) {
    super(canvasController, canvasModel, x, y);
  }

  @Override
  protected void doExecute() {
    boolean isOnHandle = this.selectionManager.getSelectedShapes().getChildren().get(0).isOnHandle(x, y);
    Shape clickedShape = this.canvasModel.clickShape(x, y);
    // model에서 onHandle인지도 체크 가능.
    if (isOnHandle) {
      this.canvasController.setCurrentTool(Tool.RESIZE);
    } else {
      if (clickedShape != null) {
        this.selectionManager.clearSelectedShapes();
        this.selectionManager.selectShape(clickedShape); // 선택된 도형 추가
        // 이후 SingleSelectedState로 이동
      } else {
        this.selectionManager.clearSelectedShapes();
        this.canvasController.setCurrentTool(Tool.SELECT);
        // 클릭한 곳이 비어있으면 선택 해제 후 selectToolState로 이동
        // startX, startY를 기록
      }
    }
  }

}
