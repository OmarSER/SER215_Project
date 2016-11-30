package hangman_Project;

import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
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
	
	public LetterGrid(String insertWord, String imageDirectory, String imageType)
	{
		// TODO Auto-generated constructor stub
		gridColumns = 10;
		gridRows = 2;
		
		letterGridLayout = new GridLayout(gridRows, gridColumns);
		letterGridLayout.setVgap(5);
		size = gridRows * gridColumns;
		
		directoryImage = imageDirectory;
		typeImage = imageType;
		
		grid = new ArrayList<>();
		selectedWord = insertWord;
		
		//Centered grid
		setBorder(BorderFactory.createEmptyBorder(5, 17, 5, 5));
		setLayout(letterGridLayout);
		loadGrid();
	}
	
	//Load and build the grid
	private void loadGrid() 
	{
		// TODO Auto-generated method stub
		buildGrid();
		for(LetterTile tile:grid)
		{
			add(tile);
		}
		
	}

	private void buildGrid() 
	{
		// TODO Auto-generated method stub
		StringBuilder wordBuilder = new StringBuilder(selectedWord.toLowerCase());
		ArrayList<Character> tiles = new ArrayList<>();
		Random random = new Random();
		int i = 0;
		int j = 0;
		
		//Add letter of the selected word to the grid
		while(wordBuilder.length() > 0)
		{
			//Making sure that there are no repeated words
			if(!tiles.contains(wordBuilder.charAt(0)))
			{
				tiles.add(wordBuilder.charAt(0));
				i++;
			}
			
			wordBuilder.deleteCharAt(0);
		}
		
		//Add random letters to fill the grid
		for(; i < size; i++)
		{
			Character c = 'a';
			do
			{
				c = (char)(random.nextInt(26) + 'a');
			}while(tiles.contains(c));
			
			tiles.add(c);
		}
		
		for(i = 0; i < size; i++)
		{
			j = random.nextInt(tiles.size());
			grid.add(new LetterTile(tiles.get(j), directoryImage, typeImage));
			tiles.remove(j);
		}
	}
	
	//Add listener for each letter
	public void attachListeners(MouseListener l)
	{
		for(LetterTile tile:grid)
		{
			tile.addTileListener(l);
		}
	}
	
	public void removeListeners()
	{
		for(LetterTile tile:grid)
		{
			tile.removeTileListener();
		}
	}
}
