import java.awt.*;
import java.text.*;
public class PolygonCommand extends Command {
  private Polygon poly;
  private int pointCount;
  public PolygonCommand() {
    poly = new Polygon();
    pointCount = 0;
  }
  public PolygonCommand(Point point) {
    poly = new Polygon(point);
    pointCount = 1;
  }
  public PolygonCommand(Point point1, Point point2) {
    poly = new Polygon(point1, point2);
    pointCount = 2;
  }
  public void addPolygonPoint(Point point) {
    poly.addPoint(point);
  }
  public void execute() {
    model.addItem(poly);
  }
  public boolean undo() {
    model.removeItem(poly);
    return true;
  }
  public boolean redo() {
    execute();
    return true;
  }
  public boolean end() {
    if (poly.getFirstPoint() == null) {
      undo();
      return false;
    }
    if (poly.getLastPoint() == null) {
       poly.setLastPoint(poly.getFirstPoint());
    }
    return true;
  }
  public void setMousePoint(Point point){
    poly.setMousePoint(point);
  }
  public void endPolygonPoint(){
    poly.endPoint();
  }
}
