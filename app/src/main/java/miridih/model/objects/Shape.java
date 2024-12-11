package miridih.model.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Shape implements Serializable {
    private double startX, startY, endX, endY;
    private Color color;

    public void setStart(double x, double y) {
        startX = x;
        startY = y;
    }

    public void setEnd(double x, double y) {
        endX = x;
        endY = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean contains(double x, double y);

    public abstract void draw(Graphics2D g2d);

    public void move(double dx, double dy) {
        startX += dx;
        startY += dy;
        endX += dx;
        endY += dy;
    }

    public void drawSelectionBox(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLUE);
        g2d.drawRect((int) this.getStartX(), (int) this.getStartY(),
                (int) (this.getEndX() - this.getStartX()),
                (int) (this.getEndY() - this.getStartY()));
    }

    public void drawHandle(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect((int) this.getEndX() - 3, (int) this.getEndY() - 3, 6, 6);
    }

    public boolean isOnHandle(double x, double y) { // 해당 포인트가 끝점 주변 5px 이내에 있는지 확인
        return Math.abs(x - endX) <= 5 && Math.abs(y - endY) <= 5;

    }

    public double getWidth() {
        return endX - startX;
    }

    public double getHeight() {
        return endY - startY;
    }
}
