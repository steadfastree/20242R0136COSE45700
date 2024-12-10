package miridih.model.objects.factory;

import miridih.model.objects.EllipseShape;
import miridih.model.objects.Shape;

public class EllipseFactory implements ShapeFactory {

  @Override
  public Shape createShape() {
    return new EllipseShape();
  }
}
