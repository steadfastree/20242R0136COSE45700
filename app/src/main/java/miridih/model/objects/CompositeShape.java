package miridih.model.objects;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class CompositeShape extends Shape {
  private final List<Shape> children = new ArrayList<>();

  public void addShape(Shape shape) {
    children.add(shape);
  }

  public void removeShape(Shape shape) {
    children.remove(shape);
  }

  @Override
  public boolean contains(double x, double y) {
    return false;
  }

  @Override
  public void draw(Graphics2D g2d) {
    // 모두 그리도록
  }

}
