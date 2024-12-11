package miridih.model;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import miridih.common.manager.SelectionManager;
import miridih.model.objects.Shape;
import miridih.observer.ShapeChangeListener;
import miridih.observer.ToolChangeListener;

public class CanvasModel {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private SelectionManager selectionManager = SelectionManager.getInstance();

    private List<ShapeChangeListener> shapeChangeListeners = new ArrayList<>();
    private ArrayList<ToolChangeListener> toolChangeListeners = new ArrayList<>();

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        notifyShapeChanged();
    }

    public void addShapeChangeListener(ShapeChangeListener listener) {
        shapeChangeListeners.add(listener);
    }

    public void removeShapeChangeListener(ShapeChangeListener listener) {
        shapeChangeListeners.remove(listener);
    }

    private void notifyShapeChanged() {
        for (ShapeChangeListener listener : shapeChangeListeners) {
            listener.onShapeChanged();
        }
    }

    public void addToolChangeListener(ToolChangeListener listener) {
        toolChangeListeners.add(listener);
    }

    public Shape clickShape(double x, double y) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.contains(x, y)) {
                return shape;
            }
        }
        return null;
    }

    public Shape getSelectedShape() {
        return selectionManager.getSelectedShapes().getChildren().isEmpty() ? null
                : selectionManager.getSelectedShapes().getChildren().get(0);
    }

    public ArrayList<Shape> getSelectedShapes() {
        return selectionManager.getSelectedShapes().getChildren();
    }

    public void moveSelectedShapes(double dx, double dy) {
        selectionManager.getSelectedShapes().move(dx, dy);
        notifyShapeChanged();
    }

    public void resizeSelectedShape(double x, double y) {
        Shape selectedShape = getSelectedShape();
        if (selectedShape != null) {
            selectedShape.setEnd(x, y);
            notifyShapeChanged();
        }
    }

    public void updateColor(Color color) {
        Shape selectedShape = getSelectedShape();
        if (selectedShape != null) {
            selectedShape.setColor(color);
            notifyShapeChanged();
        }
    }

    public void updateShape(double x, double y, double width, double height) {
        Shape selectedShape = getSelectedShape();
        if (selectedShape != null) {
            selectedShape.setStart(x, y);
            selectedShape.setEnd(x + width, y + height);
            notifyShapeChanged();
        }
    }

    public void bringToFront() {
        Shape selectedShape = getSelectedShape();
        if (selectedShape != null) {
            shapes.remove(selectedShape);
            shapes.add(selectedShape);
            notifyShapeChanged();
        }
    }

    public void sendToBack() {
        Shape selectedShape = getSelectedShape();
        if (selectedShape != null) {
            shapes.remove(selectedShape);
            shapes.add(0, selectedShape);
            notifyShapeChanged();
        }
    }

    public String backup() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this.shapes);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            return "";
        }
    }

    public void restore(String state) {
        try {
            byte[] data = Base64.getDecoder().decode(state);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            this.shapes = (ArrayList<Shape>) ois.readObject();
            ois.close();
            notifyShapeChanged();
        } catch (ClassNotFoundException e) {
            System.out.print("ClassNotFoundException occurred.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("IOException occurred.");
        }
    }
}
