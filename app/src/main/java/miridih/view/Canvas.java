package miridih.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

import miridih.controller.CanvasController;
import miridih.objects.Shape;
import miridih.objects.Tool;

public class Canvas extends JPanel {
    private final CanvasController canvasController;
    private double lastX, lastY;

    public Canvas(CanvasController controller) {
        canvasController = controller;

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
                repaint(); // 마우스 액션 종료
            }
        });

        addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                canvasController.mouseDragged(
                    e.getX(), 
                    e.getY(),
                    e.getX() - lastX,
                    e.getY() - lastY
                );
                lastX = e.getX();
                lastY = e.getY();
                repaint();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        ArrayList<Shape> shapes = canvasController.getShapes();

        shapes.forEach((shape) -> drawShape(g2d, shape));
        ArrayList<Shape> selectedShapes = canvasController.getSelectedShapes();
        for(Shape shape : selectedShapes){
            drawSelectionBox(g2d, shape);
            drawHandle(g2d, shape);
        }
    }

    public void drawShape(Graphics2D g2d, Shape shape){
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
    }

    public void drawSelectionBox(Graphics2D g2d, Shape selectedShape){
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLUE);
        g2d.drawRect((int)selectedShape.getStartX(), (int)selectedShape.getStartY(), (int)(selectedShape.getEndX() - selectedShape.getStartX()), (int)(selectedShape.getEndY() - selectedShape.getStartY()));
    }

    public void drawHandle(Graphics2D g2d, Shape selectedShape){
        g2d.setColor(Color.BLUE);
        g2d.fillRect((int)selectedShape.getEndX() - 3, (int)selectedShape.getEndY() - 3, 6, 6);
    }
}
