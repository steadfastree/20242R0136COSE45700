package miridih.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import miridih.model.CanvasModel;
import miridih.objects.Shape;
import miridih.objects.Tool;

public class Canvas extends JPanel {
    private final CanvasModel canvasModel;
    private double startX, startY, endX, endY;

    public Canvas(CanvasModel model) {
        canvasModel = model;
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                startX = e.getX();
                startY = e.getY();
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                System.out.println(canvasModel.getCurrentTool());
                Shape newShape = new Shape();
                newShape.setStart(startX, startY);
                newShape.setEnd(endX, endY);
                newShape.setTool(canvasModel.getCurrentTool());
                canvasModel.addShape(newShape);
                repaint(); // 마우스 버튼을 놓을 때 도형 그리기
            }
        });

        
    }

    public void setCurrentTool(Tool tool) {
        canvasModel.setCurrentTool(tool);
    }

    

    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        ArrayList<Shape> shapes = canvasModel.getShapes();

        shapes.forEach((shape) -> {
            Tool tool = shape.getTool();
            double startX = shape.getStartX();
            double startY = shape.getStartY();
            double endX = shape.getEndX();
            double endY = shape.getEndY();

            switch(tool) {
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
