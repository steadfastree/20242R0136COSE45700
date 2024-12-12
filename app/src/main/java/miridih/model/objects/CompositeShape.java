package miridih.model.objects;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class CompositeShape extends Shape {
  private final ArrayList<Shape> children = new ArrayList<>();

  public void addShape(Shape shape) {
    children.add(shape);
  }

  public void removeShape(Shape shape) {
    children.remove(shape);
  }

  public void selectShape(Shape shape) {
    if (!children.contains(shape)) {
      children.add(shape);
    }
  }

  public void clearSelectedShapes() {
    children.clear();
  }

  public ArrayList<Shape> getChildren() {
    return children;
  }

  @Override
  public boolean contains(double x, double y) {
    for (Shape shape : children) {
      if (shape.contains(x, y)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void draw(Graphics2D g2d) {
    for (Shape shape : children) {
      shape.draw(g2d);
    }
    // 모두 그리도록
  }

  @Override
  public void move(double dx, double dy) {
    for (Shape shape : children) {
      shape.move(dx, dy);
    }
  }
}
