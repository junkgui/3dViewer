import java.awt.event.*;
import java.awt.Dimension;

public class RotateThread extends Thread {
  private RenderPanel pict;
  private Scene scene;
  private Matrix matrix;
  private TransformationFactory tf;
  private double x, y;
  private MyMouseAdapter ma_;
  private View v_;
  private int sizex, sizey;
  private double scale;
  RotateThread(Scene se, RenderPanel rp, MouseAdapter ma, View v) {
    tf = new TransformationFactory();
    scene = se;
    pict = rp;
    v_ = v;
    Dimension d = v.getSize();
    sizex = d.width;
    sizey = d.height;
    scale = 1;
    ma_ = (MyMouseAdapter) ma;
   
  }

  private Matrix rotation() {
    Matrix m = (tf.buildYRotation(((double)-ma_.px)/1000));
    m =  m.multiply(tf.buildXRotation(((double)ma_.py)/1000));

    return m;
  }

  public void run() {
    //double angle = 0;
    while (true) {
      GObject gob = scene.getGObjectAt(0);

      Dimension d = v_.getSize();
      if (sizex != d.width || sizey != d.height) {
        scale = ((((double)d.width)/((double)sizex)+(((double)d.height)/((double)sizey))))/2;
        gob = gob.globalTransform(rotation().multiply(tf.buildUniformScaling(scale)));
      } else {
        gob = gob.globalTransform(rotation());
      }
      sizex = d.width;
      sizey = d.height;
      Scene newS = new Scene();
      newS.add(gob);
      pict.setSize(v_.getSize());
      pict.setScene(newS, v_);
      scene = newS;
      pict.repaint(0);
      //yield();
      try {
        sleep(20);
      } catch (Exception e) {
      }
    }
  }
}










