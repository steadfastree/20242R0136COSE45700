package miridih.common.eventHandler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import miridih.controller.CanvasController;

public class CanvasEventHandler {
  private final CanvasController canvasController;
  
  public CanvasEventHandler(CanvasController canvasController) {
    this.canvasController = canvasController;
  }

  public MouseAdapter getMouseAdapter() {
    return new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        canvasController.mousePressed(e.getX(), e.getY());
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        canvasController.mouseReleased(e.getX(), e.getY());
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        canvasController.mouseDragged(
                        e.getX(),
                        e.getY());
      }

      @Override
      public void mouseClicked(MouseEvent e){
        canvasController.mouseClicked(e.getX(), e.getY());
      }
    };
  }

}
