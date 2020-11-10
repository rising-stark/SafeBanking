package com.bank.additional;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class transparent {

	private static BufferedImage loadImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
		}
		return img;
	}
	
	public static void main(String[] args){
		try {
		int width = 1920;
        int height = 1080;
 
        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
 
        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bi.createGraphics();
 
        // fill all the image with white
        Color co = new Color(200,150,100,255);
        g2d.setColor(co);
        //g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, width, height);
  
        // create a string with yellow
        g2d.setComposite(AlphaComposite.Src);
        g2d.setColor(Color.black);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 55)); 
        g2d.drawString("teri nazar", 500, 520);
 
        // Disposes of this graphics context and releases any system resources that it is using. 
        g2d.dispose();
 
        // Save as PNG
        File file = new File("C:\\Users\\ujjwa\\Desktop\\myimage.png");
        ImageIO.write(bi, "png", file);
        
        /*BufferedImage newbi=bi;
        bi = loadImage("C:\\Users\\ujjwa\\Desktop\\images\\6.jpg");
        //BufferedImage newbi = loadImage("C:\\Users\\ujjwa\\Desktop\\Practice\\Winter\\WinterImages\\Numbers\\English (Transparent)\\1\\3.png");
        g2d = bi.createGraphics();
        
        Image img;
		ImageObserver obs;
		img=(Image)newbi;
		int dx1,dx2,dy1,dy2,sx1,sx2,sy1,sy2;
		sx1=sy1=0;
		sx2=newbi.getWidth();
		sy2=newbi.getHeight();
		dx1=1200;
		dy1=300;
		
		obs=null;
		
		
		//g2d.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, obs);
		AffineTransform org = g2d.getTransform();
		AffineTransform at = new AffineTransform();
		
		//g2d.setTransform(at);
		//at.setToShear(0.6, 0.2);
		//g2d.setTransform(at);
		//g2d.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, obs);
		//at.setToRotation(Math.PI / 4,dx1,dy1);
		
		
		
	/*	//at.translate(129, -235);
		dx1 = (int) Math.round(dx1 / 0.83);
		dy1 = (int) Math.round(dy1 / 1.02);
		int rotx=dx1;
		int roty=dy1;
		System.out.println(dx1);
		System.out.println(dy1);
		int k = (int) Math.round((dx1 - 0.69 * dy1) / (1 - 0.69 * 0.42));
		dy1 = (int) Math.round((dy1 - 0.42 * dx1) / (1 - 0.69 * 0.42));
		dx1=k;
		
		at.scale(0.83,1.02);	
		at.rotate(-Math.PI/8,rotx,roty);
		at.shear(0.69, 0.42);
		
		
		System.out.println(dx1);
		System.out.println(dy1);
		g2d.setTransform(at);
		Composite orc = g2d.getComposite();
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.65f);
		g2d.setComposite(c);
		g2d.setFont(new Font("Georgia", Font.BOLD, 200));
		g2d.drawImage(img, dx1, dy1, dx1+300, dy1+300, sx1, sy1, sx2, sy2, obs);
		g2d.drawLine(dx1, dy1, dx2, dy2);
		
		g2d.setTransform(org);*/
		/*
		//g2d.setComposite(orc);
		newbi = loadImage("C:\\Users\\ujjwa\\Desktop\\images\\12.png");
		img=(Image)newbi;
		sx1=sy1=0;
		sx2=newbi.getWidth();
		sy2=newbi.getHeight();
		dx1=500;
		dy1=200;
		dx2=dx1+300;
		dy2=dy1+300;
		obs=null;
		
		g2d.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, obs);*/
		
		
		/*file = new File("C:\\Users\\ujjwa\\Desktop\\Exp1.png");
		ImageIO.write(bi, "png", file);*/
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
}
