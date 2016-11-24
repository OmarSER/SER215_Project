package hangman_Project;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Hangman extends JLabel{
	private final int prefferedWidth;
	private final int prefferedHeight;
	
	//base name for images
	private final String baseName;
	
	private final String directoryImage;
	private final String typeImage;
	private String path;
	private BufferedImage image;
	
	//Default constructor
	public Hangman()
	{
		this("hangman", "images/", ".png");
	}
	
	//Creates new HangMan given the base, dir, and type
	public Hangman(String imageBaseName, String imageDirectory, String imageType)
	{
		//Our desired width and height
		prefferedWidth = 450;
		prefferedHeight = 250;
		
		baseName = imageBaseName;
		directoryImage = imageDirectory;
		typeImage = imageType;
		
		setPreferredSize(new Dimension(prefferedWidth, prefferedHeight));
		path =  directoryImage + baseName + "_0" + typeImage;
		image = loadImage(path);
	}
	
	//Loads the image
	private BufferedImage loadImage(String imagePath)
	{
		BufferedImage img = null;
		
		try
		{
			img = ImageIO.read(new File(imagePath));
		}catch (IOException e)
		{
			System.err.println("ERROR: FILE COULD NOT BE FOUND");
			System.exit(1);
		}
		
		return img;
	}
	
	//Load next image
	public void nextImage(int imageNumber)
	{
		loadNewImage(String.valueOf(imageNumber));
	}
	
	//Display lost image
	public void loseImage()
	{
		loadNewImage("lose");
	}
	
	//Display winning image
	public void winImage()
	{
		loadNewImage("win");
	}

	private void loadNewImage(String suffix) 
	{
		// TODO Auto-generated method stub
		path = directoryImage + baseName + "_" + suffix + typeImage;
		image = loadImage(path);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, prefferedWidth, prefferedHeight, null);
	}
}
