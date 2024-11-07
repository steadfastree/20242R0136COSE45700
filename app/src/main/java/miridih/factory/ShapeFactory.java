package miridih.factory;

import miridih.controller.state.Tool;
import miridih.objects.EllipseShape;
import miridih.objects.RectangleShape;
import miridih.objects.Shape;

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
