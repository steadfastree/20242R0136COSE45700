package miridih.model.objects.factory;

import miridih.model.objects.RectangleShape;
import miridih.model.objects.Shape;

public class RectangleFactory implements ShapeFactory {

  @Override
  public Shape createShape() {
    return new RectangleShape();
  }
}
