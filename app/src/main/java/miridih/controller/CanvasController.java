package miridih.controller;

import java.awt.Color;
import java.util.ArrayList;

import miridih.command.BringToFrontCommand;
import miridih.command.CommandInvoker;
import miridih.command.CompositeCommand;
import miridih.command.SendToBackCommand;
import miridih.command.UpdateColorCommand;
import miridih.command.UpdateSelectedShapeCommand;
import miridih.common.manager.SelectionManager;
import miridih.controller.state.EllipseToolState;
import miridih.controller.state.LineToolState;
import miridih.controller.state.MultiSelectedState;
import miridih.controller.state.RectangleToolState;
import miridih.controller.state.ResizeState;
import miridih.controller.state.SelectToolState;
import miridih.controller.state.SingleSelectedState;
import miridih.controller.state.Tool;
import miridih.controller.state.ToolState;
import miridih.model.CanvasModel;
import miridih.model.objects.Shape;
import miridih.observer.SelectionChangeListener;
import miridih.observer.ShapeChangeListener;
import miridih.observer.ToolChangeListener;

public class CanvasController {
    private final CanvasModel canvasModel;
    private final SelectionManager selectionManager = SelectionManager.getInstance();
    public boolean isResizing = false;
    private ToolState currentToolState;
    private CompositeCommand compositeCommand = null;

    public CanvasController(CanvasModel model) {
        canvasModel = model;
    }

    public void addSelectionChangeListener(SelectionChangeListener listener) {
        selectionManager.addSelectionChangeListener(listener);
    }

    public void addShapeChangeListener(ShapeChangeListener listener) {
        canvasModel.addShapeChangeListener(listener);
    }

    public void addToolChangeListener(ToolChangeListener listener) {
        canvasModel.addToolChangeListener(listener);
    }

    /* 마우스 이벤트 처리 */
    public void mousePressed(double x, double y) {
        currentToolState.mousePressed(x, y);
    }

    public void mouseReleased(double x, double y) {
        currentToolState.mouseReleased(x, y);
    }

    public void mouseDragged(double x, double y) {
        currentToolState.mouseDragged(x, y);
    }

    public void mouseClicked(double x, double y) {
        currentToolState.mouseClicked(x, y);
    }

    /* 상태 변경 */
    public void setState(ToolState state) {
        currentToolState = state;
    }

    public void setCurrentTool(Tool tool) {
        System.out.println("setCurrentTool: " + tool);
        switch (tool) {
            case RECTANGLE:
                currentToolState = new RectangleToolState(this, canvasModel);
                break;
            case ELLIPSE:
                currentToolState = new EllipseToolState(this, canvasModel);
                break;
            case LINE:
                currentToolState = new LineToolState(this, canvasModel);
                break;
            case SELECT:
                currentToolState = new SelectToolState(this, canvasModel);
                break;
            case SINGLE_SELECTED:
                currentToolState = new SingleSelectedState(this, canvasModel);
                break;
            case MULTI_SELECTED:
                currentToolState = new MultiSelectedState(this, canvasModel);
                break;
            case RESIZE:
                currentToolState = new ResizeState(this, canvasModel);
                break;
            default:
                break;
        }
    }

    /* 도형 상태 처리 */
    public ArrayList<Shape> getShapes() {
        return canvasModel.getShapes();
    }

    public Shape getSelectedShape() {
        return canvasModel.getSelectedShape();
    }

    public ArrayList<Shape> getSelectedShapes() {
        return canvasModel.getSelectedShapes();
    }

    // 도형 색 변경
    public void startUpdateColor(Color color) {
        if (compositeCommand == null) {
            compositeCommand = new CompositeCommand(canvasModel);
            UpdateColorCommand command = new UpdateColorCommand(canvasModel, color);
            // CommandInvoker.getInstance().executeCommand(command);
            command.execute();
            compositeCommand.addCommand(command);
        }
    }

    public void updateColor(Color color) {
        if (compositeCommand != null) {
            UpdateColorCommand command = new UpdateColorCommand(canvasModel, color);
            // CommandInvoker.getInstance().executeCommand(command);
            command.execute();
            compositeCommand.addCommand(command);
        }
    }

    public void endUpdateColor() {
        if (compositeCommand != null) {
            CommandInvoker.getInstance().executeCommand(compositeCommand);
            compositeCommand = null;
        }
    }

    // 도형 크기 변경
    public void resizeSelectedShape(double x, double y) {
        canvasModel.resizeSelectedShape(x, y);
    }

    // 도형 이동
    public void moveSelectedShapes(double dx, double dy) {
        canvasModel.moveSelectedShapes(dx, dy);
    }

    // 도형 업데이트
    public void updateSelectedShape(double x, double y, double w, double h) {
        UpdateSelectedShapeCommand command = new UpdateSelectedShapeCommand(canvasModel, x, y, w, h);
        CommandInvoker.getInstance().executeCommand(command);
    }

    // 도형 순서 변경
    public void bringToFront() {
        BringToFrontCommand command = new BringToFrontCommand(canvasModel);
        CommandInvoker.getInstance().executeCommand(command);
    }

    public void sendToBack() {
        SendToBackCommand command = new SendToBackCommand(canvasModel);
        CommandInvoker.getInstance().executeCommand(command);
    }

    // undo / redo
    public void undo() {
        CommandInvoker.getInstance().undo(canvasModel);
    }

    public void redo() {
        CommandInvoker.getInstance().redo(canvasModel);
    }
}
