package miridih.factory;

import miridih.objects.EllipseShape;
import miridih.objects.RectangleShape;
import miridih.objects.Shape;
import miridih.objects.Tool;

public class ShapeFactory {
  public static Shape createShape(Tool tool) {
    switch(tool) {
      case RECTANGLE -> {
          return new RectangleShape();
          }
      case ELLIPSE -> {
          return new EllipseShape();
          }
      default -> {
          throw new Error();
        }
    }
  }
}
