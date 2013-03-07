//package linearAlgebra;

//import javax.swing.*;
import java.awt.*;
public class SimpleRenderer implements Renderer{
    double distance_;
    RenderPanel rp_ = null;
    public SimpleRenderer(double distance) {
	distance_ =distance;
    }
    public Panel render(Scene s) {
	rp_ =  new RenderPanel(s, distance_);
	return rp_;
    }
    
    public RenderPanel getPanel() {
	return rp_;
    }
    
}

