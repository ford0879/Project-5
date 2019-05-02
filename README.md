![Project 5 UML](https://github.com/ford0879/Project-5/blob/master/Project%205%20UML.pdf){width=60%}

In order to determine how many classes I needed I looked back on previous labs and projects.
I don't have much going on in the main class. The code for that is simply calling the DrawFrame class.
The DrawFrame class creates the JFrame for the application and I put in every variable I would need to display the information.
The DrawPanel class is for creating the general panel that will hold all the sub-panels which hold on the JButtons, JTextFields, etc.
The HammingDistance class is where most of the required calculations are held.
In the HammingDistance class, I have a constructor with no parameters that is used to call the read method.
The read method will go through the txt file and fill and arraylist with the information.
Using that information, I have 3 different methods of calculation. 
The first method, compareAllStationIDs takes in a given stationID and finds the hamming distance when compared to the other stationIDs.
The second method, compareBothStationIDs finds the hamming distance between 2 specific stationIDs.
The last method, similarStations is used to create a list of stations that are similar to a given stationID and specific hamming distance.
The DrawPanel class simply has a constructor to create a panel that fits all sub-panels and it also holds the mouse events.
The DrawFrame class is where the majority of the coding was performed.
Although there are 2 methods, the DrawFrame method is where buttons, textfields, sliders, and other components are added to their specific JPanel.
I used 4 JPanels plus the DrawPanel.
1 JPanel holds the other 2 JPanels.
Another JPanel holds components for the hamming distance slider, show station button, and the text field that shows other stations with the same value as the chosen station.
The third JPanel holds components for displaying all hamming distances, an add station button, and text field for adding stations.
The final JPanel is for the "Free Zone" where we needed to create our own idea.
I used this JPanel for adding a remove station feature.
Throughout the DrawFrame method, there are action listeners that will catch when buttons are pressed or information changed and then completes the proper functions.
All of the components are added to their given JPanel which is put onto the general DrawPanel.
The final method is for reading the given file and storing the information for use in the DrawFrame method.
