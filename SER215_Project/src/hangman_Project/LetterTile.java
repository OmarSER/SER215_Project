package hangman_Project;

import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class LetterTile extends JLabel{
	//Letter displayed on tile
	private final char letterImage;
	
	//Directory containing images
	private final String directoryImage;
	
	//image type
	private final String typeImage;
	
	//Letter Tile width and height
	private final int prefferedWidth;
	private final int prefferedHeight;
	
	//Image path
	private String path;
	
	//image being displayed
	private BufferedImage image;
	
	//Listener to change letter upon click
	private MouseListener tileListener;
	
	//Default constructor
	public LetterTile()
	{
		this('a', "images/", ".png");
	}
	

}
