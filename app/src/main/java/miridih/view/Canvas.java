package miridih.view;

import miridih.command.CanvasCommand;
import miridih.controller.CanvasController;
import miridih.observer.ShapeChangeListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;

public class Canvas extends JPanel implements ShapeChangeListener {
    private final CanvasCommand canvasCommand;
    private final CanvasController canvasController;
    private double lastX, lastY;

    public Canvas(CanvasController controller) {
        canvasController = controller;
        canvasCommand = new CanvasCommand(controller);

        canvasController.addShapeChangeListener(this);
        // 배경 색
        setBackground(Color.WHITE);

        // 마우스 클릭 이벤트
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                canvasController.mousePressed(e.getX(), e.getY());
                lastX = e.getX();
                lastY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                canvasController.mouseReleased(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
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
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        canvasCommand.drawShapes(g2d);
        canvasCommand.drawSelectedShapes(g2d);
    }

    @Override
    public void onShapeChanged() {
        repaint();
    }
}
