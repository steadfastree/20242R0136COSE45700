package miridih.controller.state;

import miridih.common.manager.PointManager;
import miridih.common.manager.SelectionManager;
import miridih.controller.CanvasController;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;

public class SingleSelectedState extends ToolState {
  private final SelectionManager selectionManager = SelectionManager.getInstance();

    public SingleSelectedState(CanvasController canvasController, CanvasModel canvasModel) {
        super(canvasController, canvasModel);
    }

    @Override
    public void mousePressed(double x, double y) {
        PointManager.getInstance().setLastPoint(x, y);
        Shape clickedShape = canvasModel.clickShape(x, y);
        
        
        if (clickedShape != null) {
          selectionManager.clearSelectedShapes();
          selectionManager.selectShape(clickedShape); // 선택된 도형 추가
            // 이후 SingleSelectedState로 이동
        } else {
            selectionManager.clearSelectedShapes(); 
            
            canvasController.setCurrentTool(Tool.SELECT);
            // 클릭한 곳이 비어있으면 선택 해제 후 selectToolState로 이동
            // startX, startY를 기록

        }
        // System.out.println("SingleSelectedState mousePressed");
    }

    @Override
    public void mouseReleased(double x, double y) {
      PointManager.getInstance().setLastPoint(x, y);
        // System.out.println("SingleSelectedState mouseReleased");
    }

    @Override
    public void mouseDragged(double x, double y) {

      canvasModel.moveSelectedShapes(x - PointManager.getInstance().getLastX(), y - PointManager.getInstance().getLastY());
      PointManager.getInstance().setLastPoint(x, y);
    }

    @Override
    public void mouseClicked(double x, double y){

    }
  
}
