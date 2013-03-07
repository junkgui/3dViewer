import java.util.Enumeration;
import java.util.Vector;

public class Scene {
  Vector values_;
  public Scene() {
    values_ = new Vector();
  }

  public void add(GObject gob) {
    values_.addElement(gob);
  }

  public GObject getGObjectAt(int index) {
    return (GObject) values_.elementAt(index);
  }

  public Enumeration elements() {
    return values_.elements();
  }
}
  




