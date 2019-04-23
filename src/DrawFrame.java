import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class to create application frame
 * @author Derek Ford
 *
 */
public class DrawFrame extends JFrame
{
	/**
	 * Make eclipse happy
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Height and Width of the frame.
	 */
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 750;
	
	/** panel to hold all panels */
    JPanel panel0 = new JPanel(new GridLayout(2, 2));
	/** panel for Hamm Slider, Show Station, and Station box */
    JPanel panel1 = new JPanel();
    /** panel for Compare with, Calculate HD, and distance display*/
    JPanel panel2 = new JPanel(new GridLayout(3, 0));
    /** panel for add Station  */
    JPanel panel3 = new JPanel(new GridLayout(3, 0));
    /** panel for Free Zone */
    JPanel panel4 = new JPanel();
	
	
	/**
	 * Constructor for Frame
	 * @param title
	 */
	public DrawFrame(String title)
	{
		super(title);

	    this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	    this.setLayout(new GridLayout(2, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
}