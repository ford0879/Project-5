import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

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
	
	//
	//  PANELS
	//
	
	/**
	 * panel to hold all panels
	 */
	DrawPanel mainPanel = new DrawPanel();
	/** panel to hold all panels */
    JPanel panel0 = new JPanel(new GridLayout(2,2));
	/** panel for Hamm Slider, Show Station, and Station box */
    JPanel panel1 = new JPanel(new GridLayout(4,0));
    /** panel for Compare with, Calculate HD, addStation, and distance display*/
    JPanel panel2 = new JPanel(new GridLayout(8,1));
    /** panel for Free Zone */
    JPanel panel3 = new JPanel();
    
    //
    // BUTTONS
    //
    /**
     * Button to compare given station with other stations of specific hamming distance
     */
    JButton showStation = new JButton("Show Station");
    /**
     * Button to compare given station with all stations
     */
    JButton calculateHD = new JButton("Calculate HD");
    /**
     * Button to add a station to list of stations
     */
    JButton addStation = new JButton("Add Station");
    
    //
    // LABELS
    //
    /**
     * Instructions for Hamming Distance Slider
     */
    JLabel hammDistLabel = new JLabel("Enter Hamming Distance");
	/**
	 * Shows where comparing station is located
	 */
	JLabel compareWithLabel = new JLabel("Compare with:");
	/**
	 * Shows where station Hamming Distance compared to Distance 0 is
	 */
	JLabel distanceLabel0 = new JLabel("Distance 0");
	/**
	 * Shows where station Hamming Distance compared to Distance 1 is
	 */
	JLabel distanceLabel1 = new JLabel("Distance 1");
	/**
	 * Shows where station Hamming Distance compared to Distance 2 is
	 */
	JLabel distanceLabel2 = new JLabel("Distance 2");
	/**
	 * Shows where station Hamming Distance compared to Distance 3 is
	 */
	JLabel distanceLabel3 = new JLabel("Distance 3");
	/**
	 * Shows where station Hamming Distance compared to Distance 4 is
	 */
	JLabel distanceLabel4 = new JLabel("Distance 4");
	
	//
	// TEXTBOXES
	//
    /**
     * Text box to show selected Hamming Distance from slider
     */
    JTextField hammDistSelected = new JTextField("");   
    /**
     * Text box to show comparison of selection station with all station of selected Hamming Distance
     */
	JTextField similarStations = new JTextField("");
	/**
	 * Shows station Hamming Distance compared to Distance 0
	 */
	JTextField distance0 = new JTextField("");	
	/**
	 * Shows station Hamming Distance compared to Distance 1
	 */
	JTextField distance1 = new JTextField("");
	/**
	 * Shows station Hamming Distance compared to Distance 2
	 */
	JTextField distance2 = new JTextField("");
	/**
	 * Shows station Hamming Distance compared to Distance 3
	 */
	JTextField distance3 = new JTextField("");
	/**
	 * Shows station Hamming Distance compared to Distance 4
	 */
	JTextField distance4 = new JTextField("");
	/**
	 * Text box where user adds station to be compared
	 */
	JTextField addStationBox = new JTextField("");
	
	// MISCELLANEOUS FUNCTIONS
    /**
     * Slider for selecting Hamming Distance
     */
    JSlider hammDistSelect = new JSlider(1,4);
	/**
	 * Dropdown box for what station is being compared
	 */
	JComboBox compareWith = new JComboBox();
	
	
	/**
	 * Constructor for Frame
	 * @param title
	 */
	public DrawFrame(String title)
	{
		super(title);

	    this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	    this.setLayout(new GridLayout(2,2));

	    // Forbids user from typing in desired Hamming Distance but shows selection from slider
	    hammDistSelected.setEditable(false);

	    similarStations.setEditable(false);
	    similarStations.setBackground(Color.WHITE);
	    
	    // Forbids user from typing where compared distances appear
	    distance0.setEditable(false);
	    distance1.setEditable(false);
	    distance2.setEditable(false);
	    distance3.setEditable(false);
	    distance4.setEditable(false);
	    
	    // Shows what value slider is set to
	    hammDistSelect.setMajorTickSpacing(1);
        hammDistSelect.setPaintTicks(true);
        hammDistSelect.setPaintLabels(true);
	    
	    // Adds components to sub-panels
        panel1.add(showStation);
        panel1.add(hammDistLabel);
        panel1.add(hammDistSelected);
        panel1.add(hammDistSelect);
        panel1.add(similarStations);

        panel2.add(compareWithLabel);
        panel2.add(compareWith);
        panel2.add(calculateHD);
        panel2.add(distanceLabel0);
        panel2.add(distanceLabel1);
        panel2.add(distanceLabel2);
        panel2.add(distanceLabel3);
        panel2.add(distanceLabel4);
        panel2.add(distance0);
        panel2.add(distance1);
        panel2.add(distance2);
        panel2.add(distance3);
        panel2.add(distance4);
        panel2.add(addStation);
        panel2.add(addStationBox);
        
        // Adds sub-panels to main panel
        panel0.add(panel1);
        panel0.add(panel2);
        panel0.add(panel3);
        

        
        // Adds main panel to frame
        this.add(panel0);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
}