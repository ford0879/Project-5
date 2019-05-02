import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
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
	
	/**
	 * Used for creating comparisons of station ID's
	 */
    HammingDist showStationHammDist = new HammingDist();
    
    /**
     * Holds all stationID names
     */
	private List<String> allStationIDs = new ArrayList<String>();
	
	HashMap<String, Integer> stationIDSortMap = new HashMap<String, Integer>();
	
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
    JPanel panel2 = new JPanel(new GridLayout(8,2));
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
	JTextArea similarStations = new JTextArea(20,10);
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
	
	//
	// MISCELLANEOUS FUNCTIONS
	//
    /**
     * Slider for selecting Hamming Distance
     */
    JSlider hammDistSelect = new JSlider(1,4);
	/**
	 * Dropdown box for what station is being compared
	 */
	JComboBox<Object> compareWith;
	/**
	 * JLabel meant to fill empty columns
	 */
	JLabel emptyCol = new JLabel("");
	/**
	 * Scrollbar for list of stations
	 */
	JScrollPane scroll = new JScrollPane (similarStations, 
			   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	
	/**
	 * Constructor for Frame
	 * @param title
	 */
	public DrawFrame(String title)
	{
		super(title);

	    this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	    this.setLayout(new GridLayout(1,0));

	    // Forbids user from typing in desired Hamming Distance but shows selection from slider
	    hammDistSelected.setEditable(false);

	    similarStations.setEditable(false);
	    similarStations.setBackground(Color.WHITE);
	    add(scroll);
	    
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
        
        // Updates what value is shown by the slider
        hammDistSelect.addChangeListener((e) ->
        {
        	hammDistSelected.setText(String.valueOf(hammDistSelect.getValue()));
        }
        );
        
        
    	// Attempt to read Mesonet file and fills compareWith dropbox
        try
        {
			read("Mesonet.txt");
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
        
        showStation.addActionListener((e) ->
        	{
        		String sameStations = showStationHammDist.similarStations(compareWith.getSelectedItem().toString(), 
        				hammDistSelect.getValue());
        		similarStations.setText(sameStations);
        	}
        );
        
        // Takes user input and inserts new station into list of stations
        addStation.addActionListener((e) -> 
        	{
        		compareWith.addItem(addStationBox.getText());
        		allStationIDs.add(addStationBox.getText());
        		
        		// Adds items to map to be sorted
        		for (int index = 0; index < allStationIDs.size(); index++) 
        		{
        		      stationIDSortMap.put(allStationIDs.get(index), index + 1);
        	    }
        		
        		// Sorts map
        		Map<String,Integer> sorted = new TreeMap<>(stationIDSortMap);
        		Collection<String> sortedKeys = sorted.keySet();
        		ArrayList<String> sortedStationIDs = new ArrayList<String>(sortedKeys);
        		
        		// Prepares box for sorted station IDs
        		compareWith.removeAllItems();
        		
        		// Adds newly sorted station IDs
        		for(int index = 0; index < sortedStationIDs.size(); index++)
        		{
        			compareWith.addItem(sortedStationIDs.get(index));
        		}
        	}
        );
        
        // Checks selected station and compares Hamming Distance to all other stations
        calculateHD.addActionListener((e) ->
        {
            ArrayList<Integer> stationDistances = showStationHammDist.compareAllStationIDs(compareWith.getSelectedItem().toString());
            distance0.setText("1");
            distance1.setText(stationDistances.get(0).toString());
            distance2.setText(stationDistances.get(1).toString());
            distance3.setText(stationDistances.get(2).toString());
            distance4.setText(stationDistances.get(3).toString());
        }
        );
	    
	    // Adds components to sub-panels
        panel1.add(hammDistLabel);
        panel1.add(hammDistSelected);
        panel1.add(hammDistSelect);
        panel1.add(showStation);
        panel1.add(scroll);

        panel2.add(compareWithLabel);
        panel2.add(compareWith);
        panel2.add(calculateHD);
        panel2.add(emptyCol);
        panel2.add(distance0);
        panel2.add(distanceLabel0);
        panel2.add(distance1);
        panel2.add(distanceLabel1);
        panel2.add(distance2);
        panel2.add(distanceLabel2);
        panel2.add(distance3);
        panel2.add(distanceLabel3);
        panel2.add(distance4);
        panel2.add(distanceLabel4);
        panel2.add(addStation);
        panel2.add(addStationBox);
        
        // Adds sub-panels to main panel
        panel0.add(panel1);
        panel0.add(panel2);
       // panel0.add(panel3);

        
        // Adds main panel to frame
        this.add(panel0);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	/**
     * Reads information from Mesonet.txt and assigns station IDs split from file into array.
     * The first 3 lines of Mesonet.txt are skipped.
     * @param filename
     * @throws IOException
     */
    public void read(String filename) throws IOException
    {
        // Use a buffered Reader on the file:
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String strg = br.readLine();
        
        
        // Splits first column of file to be used for Hamming Distance calculation
        while (strg != null)
        {
        	allStationIDs.add(strg);
        	strg = br.readLine();
        }

        String[] stationNames = allStationIDs.toArray(new String[]{});
        compareWith = new JComboBox<Object>(stationNames);
        
        br.close();
    }
}