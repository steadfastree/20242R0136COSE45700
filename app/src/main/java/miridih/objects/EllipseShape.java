package miridih.objects;

public class EllipseShape extends Shape{
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
  
}
