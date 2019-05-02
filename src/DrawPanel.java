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
	 * Height and Width of the main panel.
	 */
	private static final int PANEL_WIDTH = 600;
	private static final int PANEL_HEIGHT = 750;

	/**
	 * Constructor for Panel
	 */
	public DrawPanel()
	{
		this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		this.addMouseListener(this);
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
