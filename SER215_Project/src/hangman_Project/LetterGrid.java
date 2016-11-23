package hangman_Project;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;


public class LetterGrid extends JPanel{
	//Number of columns
	private final int gridColumns;
	
	//Number of rows
	private final int gridRows;
	
	//Letter grid layout
	private final GridLayout letterGridLayout;
	
	//Grid size
	private final int size;
	
	private final String directoryImage;
	private final String typeImage;
	
	//Word chosen to be used
	private final String selectedWord;
	
	//Array of letters to be displayed
	private final ArrayList<LetterTile> grid;
	
	//Default constructor
	public LetterGrid()
	{
		this("selectedWord", "images/", ".png");
	}

}
