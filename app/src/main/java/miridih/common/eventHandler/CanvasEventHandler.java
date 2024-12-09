package miridih.common.eventHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import miridih.controller.CanvasController;

public class CanvasEventHandler {
  private final CanvasController canvasController;
  private double lastX, lastY;
  
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
                        e.getY(),
                        e.getX() - lastX,
                        e.getY() - lastY);
                lastX = e.getX();
                lastY = e.getY();
      }
    };
  }

  public KeyAdapter getKeyAdapter() {
    return new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        System.out.println("key pressed" + e.getKeyCode());
        canvasController.keyPressed(e.getKeyCode());
      }

      @Override
      public void keyReleased(KeyEvent e) {
        System.out.println("key released" + e.getKeyCode());
        canvasController.keyReleased(e.getKeyCode());
      }
    };
  }
}
