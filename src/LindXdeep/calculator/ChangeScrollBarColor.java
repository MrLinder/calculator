package LindXdeep.calculator;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.metal.MetalScrollBarUI;

public class ChangeScrollBarColor extends MetalScrollBarUI {
	
	Desing color = Desing.inut();
	
	@Override
	protected void paintThumb( Graphics g, JComponent c, Rectangle tb ) {
        g.setColor(color.getColorFocus());
        if ( scrollbar.getOrientation() == JScrollBar.VERTICAL ) {
          g.fillRect( tb.x, tb.y, tb.width, tb.height );
        } else {
          g.fillRect( tb.x, tb.y, tb.width, tb.height );
        }
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle tb) {
        g.setColor( color.getColorBack());
        g.fillRect(tb.x, tb.y, tb.width, tb.height);
    }
}
