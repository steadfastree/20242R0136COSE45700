package miridih.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import miridih.controller.CanvasController;
import miridih.objects.Shape;
import miridih.objects.Tool;

public class Canvas extends JPanel {
    private final CanvasController canvasController;

    public Canvas(CanvasController controller) {
        canvasController = controller;

        // 배경 색
        setBackground(Color.WHITE);

        // 마우스 클릭 이벤트
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                canvasController.mousePressed(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                canvasController.mouseReleased(e.getX(), e.getY());
                repaint(); // 마우스 버튼을 놓을 때 도형 그리기
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        ArrayList<Shape> shapes = canvasController.getShapes();

        shapes.forEach((shape) -> {
            Tool tool = shape.getTool();
            double startX = shape.getStartX();
            double startY = shape.getStartY();
            double endX = shape.getEndX();
            double endY = shape.getEndY();

            switch (tool) {
                case RECTANGLE:
                    g2d.drawRect((int) startX, (int) startY, (int) (endX - startX), (int) (endY - startY));
                    break;
                case ELLIPSE:
                    g2d.drawOval((int) startX, (int) startY, (int) (endX - startX), (int) (endY - startY));
                    break;
            }
        });
    }
}
