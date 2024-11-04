package miridih.objects;

public abstract class Shape {
    private Tool tool;
    private double startX, startY, endX, endY;

    public void setStart(double x, double y) {
        startX = x;
        startY = y;
    }

    public void setEnd(double x, double y) {
        endX = x;
        endY = y;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public Tool getTool() {
        return tool;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public abstract boolean contains(double x, double y);
    // public abstract void draw();

    public double getWidth() {
        return endX - startX;
    }

    public double getHeight() {
        return endY - startY;
    }
}
