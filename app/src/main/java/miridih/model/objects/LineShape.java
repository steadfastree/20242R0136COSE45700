package miridih.model.objects;

import java.awt.Color;
import java.awt.Graphics2D;

public class LineShape extends Shape {
  @Override
  public boolean contains(double x, double y) {
    double centerX = (getStartX() + getEndX()) / 2;
    double centerY = (getStartY() + getEndY()) / 2;
    double a = Math.abs(getStartX() - getEndX()) / 2;
    double b = Math.abs(getStartY() - getEndY()) / 2;
    double dx = x - centerX;
    double dy = y - centerY;
    return (dx * dx) / (a * a) + (dy * dy) / (b * b) <= 1;
  }

  @Override
  public void draw(Graphics2D g2d) {
    int x1 = (int) getStartX();
    int x2 = (int) getEndX();
    int y1 = (int) getStartY();
    int y2 = (int) getEndY();

    g2d.setColor(Color.BLACK);
    g2d.drawLine(x1, y1, x2, y2);
  }
}
