package com.bank.additional;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class flip {

	private static BufferedImage loadImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
		}
		return img;
	}
	
	private static void saveImage(BufferedImage bi, String path,String type) throws IOException {
		File file = new File(path);
        ImageIO.write(bi, type, file);
	}
	
	public static void main(String[] args) throws IOException {
		int w,h;
        BufferedImage bi;
        Graphics2D g2d;
        bi=loadImage("C:\\Users\\ujjwa\\Desktop\\images\\watermarked.png");
        h = bi.getHeight();
		w = bi.getWidth();
        BufferedImage newbi = new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
        g2d = newbi.createGraphics();
        AffineTransform org = g2d.getTransform();
		AffineTransform at = new AffineTransform();
		at.rotate(Math.PI,w/3,h/3);
		g2d.setTransform(at);
		g2d.drawImage(bi,null, 0,0);
		saveImage(newbi,"C:\\Users\\ujjwa\\Desktop\\images\\rotated.png","png");
        g2d.setTransform(org);
        g2d.dispose();
 	}
}