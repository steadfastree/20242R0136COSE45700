package miridih.common.manager;

public class PointManager {
    private static final PointManager instance = new PointManager();
    private double lastX;
    private double lastY;

    private PointManager() {
    }

    public static PointManager getInstance() {
        return instance;
    }

    public void setLastPoint(double lastX, double lastY) {
        this.lastX = lastX;
        this.lastY = lastY;
    }

    public double getLastX() {
        return lastX;
    }

    public void setLastX(double lastX) {
        this.lastX = lastX;
    }

    public double getLastY() {
        return lastY;
    }

    public void setLastY(double lastY) {
        this.lastY = lastY;
    }
} 