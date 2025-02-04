import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class PolygonButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  private PolygonCommand polygonCommand;
  private UndoManager undoManager;
  public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
    super("Polygon");
    this.undoManager = undoManager;
    addActionListener(this);
    view = jFrame;
    drawingPanel = jPanel;
    mouseHandler = new MouseHandler();
  }
  public void actionPerformed(ActionEvent event) {
    view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    // Change cursor when button is clicked
    drawingPanel.addMouseListener(mouseHandler);
    drawingPanel.addMouseMotionListener(mouseHandler);
  // Start listening for mouseclicks on the drawing panel
  }
  private class MouseHandler extends MouseAdapter {
    private int pointCount = 0;
    public void mouseClicked(MouseEvent event) {
    if (SwingUtilities.isLeftMouseButton(event)) {
        if(pointCount == 0){
        polygonCommand = new PolygonCommand(View.mapPoint(event.getPoint()));
        undoManager.beginCommand(polygonCommand);
        pointCount = 1;
        }
        else if(pointCount == 1){
            polygonCommand.addPolygonPoint(View.mapPoint(event.getPoint()));
        }
    } else if (SwingUtilities.isRightMouseButton(event)) {
        pointCount = 0;
        polygonCommand.endPolygonPoint();
        drawingPanel.removeMouseListener(this);
        drawingPanel.removeMouseMotionListener(this);
        view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        undoManager.endCommand(polygonCommand);
      }
    }
    public void mouseMoved(MouseEvent event) {
        if(pointCount == 1)
        polygonCommand.setMousePoint(View.mapPoint(event.getPoint()));
    
    }
  }
}