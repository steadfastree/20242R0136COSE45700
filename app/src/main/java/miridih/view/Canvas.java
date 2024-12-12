package miridih.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import miridih.command.CanvasCommand;
import miridih.common.eventHandler.CanvasEventHandler;
import miridih.controller.CanvasController;
import miridih.observer.SelectionChangeListener;
import miridih.observer.ShapeChangeListener;

public class Canvas extends JPanel implements ShapeChangeListener, SelectionChangeListener {
    private final CanvasCommand canvasCommand;
    private final CanvasController canvasController;
    private final CanvasEventHandler canvasEventHandler;

    public Canvas(CanvasController controller) {
        canvasController = controller;
        canvasCommand = new CanvasCommand(controller);
        canvasEventHandler = new CanvasEventHandler(controller);

        canvasController.addShapeChangeListener(this);
        canvasController.addSelectionChangeListener(this);

        // 배경 색
        setBackground(Color.WHITE);

        // 마우스 클릭 이벤트
        addMouseListener(canvasEventHandler.getMouseAdapter());
        addMouseMotionListener(canvasEventHandler.getMouseAdapter());
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

    @Override
    public void onSelectionChanged() {
        repaint();
    }
}
