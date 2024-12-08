package miridih.factory;

import miridih.objects.RectangleShape;
import miridih.objects.Shape;

public class RectangleFactory implements ShapeFactory {

  @Override
  public Shape createShape() {
    return new RectangleShape();
  }
}
