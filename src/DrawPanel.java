import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener
{
	/**
	 * Makes eclipse happy
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for Panel
	 */
	public DrawPanel()
	{
		this.setPreferredSize(new Dimension(200,500));
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
