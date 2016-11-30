package hangman_Project;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

@SuppressWarnings("serial")
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
	
	public LetterTile(char imageLetter, String imageDirectory, String imageType) 
	{
		// TODO Auto-generated constructor stub
		letterImage = imageLetter;
		directoryImage = imageDirectory;
		typeImage = imageType;
		
		prefferedWidth = prefferedHeight = 50;
		
		setPreferredSize(new Dimension(prefferedWidth, prefferedHeight));
		path = directoryImage + letterImage + typeImage;
		image = loadImage(path);
	}
	
	//Loads image from a file
	private BufferedImage loadImage(String imagePath) {
		// TODO Auto-generated method stub
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(new File(imagePath));
		}catch (IOException e)
		{
			System.err.println("ERROR: IMAGE NOT FOUND, TRY SOMETHING ELSE");
			System.exit(1);
		}
		
		return img;
	}	
	
	//Change the tile after its selected so player won't be able to select it again
	public char guess()
	{
		loadNewImage("guessed");
		removeTileListener();
		return letterImage;
	}
	
	//Load new HangMan image
	private void loadNewImage(String suffix)
	{
		path = directoryImage + letterImage + "_" + suffix + typeImage;
		image = loadImage(path);
		repaint();
	}
	
	//Add listener
	public void addTileListener(MouseListener l)
	{
		tileListener = l;
		addMouseListener(tileListener);
	}
	
	//Remove used letter tile
	public void removeTileListener()
	{
		removeMouseListener(tileListener);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, prefferedWidth, prefferedHeight, null);
	}

}
