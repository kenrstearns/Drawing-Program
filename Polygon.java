import java.awt.*;
public class Polygon extends Item {
    private Point firstPoint;
    private Point lastPoint;
    private Point mousePoint;
    private LineCommand line1;
    private LineCommand line2;
    public Polygon(Point point1, Point point2) {
      this.firstPoint = point1;
      this.lastPoint = point2;
      this.mousePoint = null;
      line1 = new LineCommand(point1);
      line2 = new LineCommand(point2);
    }
    public Polygon(Point point1) {
      this.firstPoint = point1;
      lastPoint = point1;
      line1 = new LineCommand(firstPoint);
      line2 = new LineCommand(lastPoint);
    }
    public Polygon() {
        firstPoint = null;
        lastPoint = null;
        line1 = new LineCommand();
        line2 = new LineCommand();
    }
    public boolean includes(Point point) {
      return ((distance(point, firstPoint ) < 10.0) || (distance(point, lastPoint)< 10.0));
    }
    public void addPoint(Point point) {
        if(lastPoint == null){
            firstPoint = point;
            lastPoint = point;
        }
        else{
        LineCommand lineCommand = new LineCommand(lastPoint, point);
        lineCommand.execute();
        lastPoint = point;
        line2.setPt1(lastPoint);
        }
    }
    public void endPoint(){
        line1.setPt2(lastPoint);
        line2.setPt2(firstPoint);        
       }
    public void setMousePoint(Point point) {
        mousePoint = point;
        line1.setPt2(point);
        line2.setPt2(point);
    }
    public void setFirstPoint(Point point) {
      firstPoint = point;
    }
    public void setLastPoint(Point point) {
      lastPoint = point;
    }
    public Point getFirstPoint() {
      return firstPoint;
    }
    public Point getLastPoint() {
      return lastPoint;
    }
    public Point getMousePoint() {
        return mousePoint;
    }
    public String toString() {
      return "Line  from " + firstPoint + " to " + lastPoint;
    }
  }
