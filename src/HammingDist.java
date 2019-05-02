import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HammingDist
{
	/**
	 * Station ID's to be used for Hamming Distance
	 */
	private String firstID;
	private String secondID;
	private ArrayList<String> compareTo = new ArrayList<String>();
	
	public HammingDist()
	{
    	// Attempt to read Mesonet file
        try
        {
			read("Mesonet.txt");
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
	}
	
    /**
     * Constructs station ID's that will be used in Hamming Distance process
     */
    public HammingDist(String firstID, String secondID)
    {
    	this.firstID = firstID;
    	this.secondID = secondID;
    	
    	// Attempt to read Mesonet file
        try
        {
			read("Mesonet.txt");
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
        
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
        	compareTo.add(strg);
        	strg = br.readLine();
        }

        br.close();
    }
    
    /**
     * Finds Hamming Distance of given station ID compared to other station IDs
     * @return
     */
    public ArrayList<Integer> compareAllStationIDs(String stationID)
    {
    	// Separate ID strings into characters for one on one comparison
    	String str = compareTo.toString().replaceAll(",", "");
    	char[] allStationIDs = str.substring(1, str.length()-1).replaceAll(" ", "").toCharArray();
    	char[] stationsID = stationID.toCharArray();
    	
    	
    	// Calculated Hamming Distances of all stations compared to given station
    	ArrayList<Integer> hamCount = new ArrayList<Integer>();
    	
    	// Stores how many stations are Hamming Distance 1-4
    	int[] distance = new int[4];
    	
    	// Cycles through each char, compares them, and if necessary adds to Distance counter
        for(int index = 0; index < allStationIDs.length; index += 4)
         {
        	// Keeps track of differences between IDs
        	int distCount = 0;
        	
        	// Checks each character to see which are different and counts the differences
    	    if(allStationIDs[index] != stationsID[0])
    	    	{
    	    		++distCount;
    	    	}
    	    
    	    if(allStationIDs[index+1] != stationsID[1])
    			{
    				++distCount;
    			}
    	    
    	    if(allStationIDs[index+2] != stationsID[2])
    			{
    				++distCount;
    			}
    
    	    if(allStationIDs[index+3] != stationsID[3])
    			{
    				++distCount;
    			}
    	    hamCount.add(distCount);    	    
         }
        
        
        // Counts how many times a certain distance happens
        for(int index = 0; index < hamCount.size(); ++index)
        {
        	if(hamCount.get(index) == 1)
        	{
        		++distance[0];
        	}
        	
        	if(hamCount.get(index) == 2)
        	{
        		++distance[1];
        	}
        	
        	if(hamCount.get(index) == 3)
        	{
        		++distance[2];
        	}
        	
        	if(hamCount.get(index) == 4)
        	{
        		++distance[3];
        	}
        }
        // Reset hamCount variable in order to be filled with proper distances
        hamCount.clear();
        
        // Stores Hamming Distances ranging from 1-4
        hamCount.add(distance[0]);
        hamCount.add(distance[1]);
        hamCount.add(distance[2]);
        hamCount.add(distance[3]);
        
    	return hamCount;
    }
    
    /**
     * Finds Hamming Distance of given station ID compared to each other
     * @return
     */
    public int compareBothStationIDs()
    {
    	// Separate ID strings into characters for one on one comparison
    	char[] stationOne = firstID.toCharArray();
    	char[] stationTwo = secondID.toCharArray();
    	
    	// Counter for Calculated Hamming Distance
    	int hamCount = 0;
        
    	// Compares each char and if necessary adds to Hamming Distance counter
    	    if(stationOne[0] != stationTwo[0])
    	    	{
    	    		++hamCount;
    	    	}
    	    
    	    if(stationOne[1] != stationTwo[1])
    			{
    				++hamCount;
    			}
    	    
    	    if(stationOne[2] != stationTwo[2])
    			{
    				++hamCount;
    			}
    
    	    if(stationOne[3] != stationTwo[3])
    			{
    				++hamCount;
    			}
    	    
         
    	
    	return hamCount;
    }
    
    /**
     * Information about hemming distance of given stations
     * 
     * @return "The Hamming Distance of NRMN and NOWA: 3.
     * 	Out of 119, for NRMN, number of nodes are: 0, 0, 23, 96 and
     * for NOWA, number of nodes are: 0, 5, 16, 98 respectively."
     * 
     * nodes are how many stations are 1 distance, 2 distance, 3 distance, and 4 distance
     */
    @Override
    public String toString()
    {
        // Calculate Hemming Distance of given stations compared to all stations
    	ArrayList<Integer> firstStatDistance = new ArrayList<Integer>();
    	firstStatDistance.addAll(compareAllStationIDs(this.firstID));

    	ArrayList<Integer> secondStatDistance = new ArrayList<Integer>();
    	secondStatDistance.addAll(compareAllStationIDs(this.secondID));

    	return String.format("The Hamming Distance of %s and %s: %d.\n"
    			+ "Out of 119, for %s, number of nodes are: %d, %d, %d, %d and\n"
    			+ "for %s, number of nodes are: %d, %d, %d, %d respectively.", 
    			  this.firstID, this.secondID, compareBothStationIDs(),
    			  this.firstID, firstStatDistance.get(0), firstStatDistance.get(1), firstStatDistance.get(2), firstStatDistance.get(3),
    			  this.secondID, secondStatDistance.get(0), secondStatDistance.get(1), secondStatDistance.get(2), secondStatDistance.get(3));
    }
}