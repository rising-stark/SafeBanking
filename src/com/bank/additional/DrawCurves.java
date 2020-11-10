package com.bank.additional;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class DrawCurves {

	static Random ran = new Random();
	static String pathDir = "C:\\Users\\ujjwa\\Desktop\\Practice\\Winter\\WinterImages\\";

	private static BufferedImage loadImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	private static void drawGrid(Graphics2D g, int w, int h) {

		g.drawLine(0, 0, w, 0);
		g.drawLine(0, h / 6, w, h / 6);
		g.drawLine(0, 2 * h / 6, w, 2 * h / 6);
		g.drawLine(0, 3 * h / 6, w, 3 * h / 6);
		g.drawLine(0, 4 * h / 6, w, 4 * h / 6);
		g.drawLine(0, 5 * h / 6, w, 5 * h / 6);
		g.drawLine(0, h, w, h);

		g.drawLine(0, 0, 0, h);
		g.drawLine(w / 6, 0, w / 6, h);
		g.drawLine(2 * w / 6, 0, 2 * w / 6, h);
		g.drawLine(3 * w / 6, 0, 3 * w / 6, h);
		g.drawLine(4 * w / 6, 0, 4 * w / 6, h);
		g.drawLine(5 * w / 6, 0, 5 * w / 6, h);
		g.drawLine(w, 0, w, h);

		g.drawLine(0, 0, w, h);
		g.drawLine(w, 0, 0, h);
	}

	private static void displayImage() {
		try {
			int i, dx1, dx2, dy1, dy2, sx1, sx2, sy1, sy2;
			BufferedImage bi;
			Image img;
			ImageObserver obs;

			int a = 0, b = 0;
			int x = 100, y = 150;
			int coord[] = new int[12];
			getCoord(coord);
			bi = loadImage(pathDir + "Curves\\blank5.png");
			Graphics2D g = bi.createGraphics();
			for (i = 0; i < 6; i++) {
				img = (Image) loadImage(pathDir + "Numbers\\English (Transparent)\\" + 3 + "\\" + 5 + ".png");
				sx1 = sy1 = 0;
				sx2 = img.getWidth(null);
				sy2 = img.getHeight(null);
				dx1 = coord[2 * i];
				dy1 = coord[2 * i + 1];
				dx2 = dx1 + x;
				dy2 = dy1 + y;
				obs = null;
				g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, obs);
			}
			File file = new File("C:\\Users\\ujjwa\\Desktop\\Practice\\Winter\\WinterImages\\Curves\\blank41.png");
			ImageIO.write(bi, "png", file);
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getCoord(int[] coord) {
		int i, l, h;
		int x = 100, y = 150;
		switch (4) {
		case 1: {
			int sinarr[] = { 0, 180, 320 - x, 540 - y, 320, 0, 640 - x, 180 - y, 640, 180, 960 - x, 540 - y, 960, 540,
					1280 - x, 900 - y, 1280, 900, 1600 - x, 1080 - y, 1600, 540, 1920 - x, 900 - y };
			for (i = 0; i < 6; i++) {
				l = sinarr[4 * i];
				h = sinarr[4 * i + 2];
				coord[2 * i] = ran.nextInt(Math.abs(h - l)) + l;
				l = sinarr[4 * i + 1];
				h = sinarr[4 * i + 3];
				coord[2 * i + 1] = ran.nextInt(Math.abs(h - l)) + l;
			}
		}
			break;
		case 2: {
			int cosarr[] = { 0, 0, 320 - x, 180 - y, 320, 360, 640 - x, 720 - y, 640, 900, 960 - x, 1080 - y, 960, 900,
					1280 - x, 1080 - y, 1280, 360, 1600 - x, 720 - y, 1600, 0, 1920 - x, 180 - y };
			for (i = 0; i < 6; i++) {
				l = cosarr[4 * i];
				h = cosarr[4 * i + 2];
				coord[2 * i] = ran.nextInt(Math.abs(h - l)) + l;
				l = cosarr[4 * i + 1];
				h = cosarr[4 * i + 3];
				coord[2 * i + 1] = ran.nextInt(Math.abs(h - l)) + l;
			}
		}
			break;
		case 3: {
			int exparr[] = { 0, 900, 320 - x, 1080 - y, 320, 800, 640 - x, 1000 - y, 640, 640, 960 - x, 850 - y, 960, 480,
					1280 - x, 690 - y, 1280, 250, 1600 - x, 500 - y, 1700, 0, 1920 - x, 250 - y };
			for (i = 0; i < 6; i++) {
				l = exparr[4 * i];
				h = exparr[4 * i + 2];
				coord[2 * i] = ran.nextInt(h - l) + l;
				l = exparr[4 * i + 1];
				h = exparr[4 * i + 3];
				coord[2 * i + 1] = ran.nextInt(h - l) + l;
			}
		}
			break;
		case 4: {
			int logarr[] = { 50, 0, 200 - x, 350 - y,200, 350, 325 - x, 550 - y, 320, 580, 640 - x, 780 - y, 640, 760,
					950 - x, 900 - y, 1000, 860, 1350 - x, 1050 - y, 1360, 900, 1680 - x, 1080 - y };
			for (i = 0; i < 6; i++) {
				l = logarr[4 * i];
				h = logarr[4 * i + 2];
				coord[2 * i] = ran.nextInt(Math.abs(h - l)) + l;
				l = logarr[4 * i + 1];
				h = logarr[4 * i + 3];
				coord[2 * i + 1] = ran.nextInt(Math.abs(h - l)) + l;
			}
		}
			break;
		case 5: {
			int stlinearr[] = { 0, 900, 320 - x, 1080 - y, 320, 720, 640 - x, 900 - y, 640, 540, 960 - x, 720 - y, 960, 360,
					1280 - x, 540 - y, 1280, 180, 1600 - x, 540 - y, 1600, 0, 1920 - x, 180 - y };
			for (i = 0; i < 6; i++) {
				l = stlinearr[4 * i];
				h = stlinearr[4 * i + 2];
				coord[2 * i] = ran.nextInt(Math.abs(h - l)) + l;
				l = stlinearr[4 * i + 1];
				h = stlinearr[4 * i + 3];
				coord[2 * i + 1] = ran.nextInt(Math.abs(h - l)) + l;
			}
		}
			break;
		}
	}

	private static void drawCurve(int w, int h) throws IOException {
		for (int j = 1; j < 6; j++) {
			BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = bi.createGraphics();
			// g.setComposite(AlphaComposite.Clear);
			g.setColor(Color.black);
			drawGrid(g, w, h);
			switch (j) {
			case 1:
				g.translate(0, h / 2);
				for (int i = 0; i <= w; i++) {
					int r = 30;
					int x = i;
					int y = (int) (Math.sin(2 * Math.PI * i / w) * (h / 2));
					g.fillOval(x, -y, r, r);
				}
				break;
			case 2:
				g.translate(0, h / 2);
				for (int i = 0; i <= w; i++) {
					int r = 30;
					int x = i;
					int y = (int) (Math.cos(2 * Math.PI * i / w) * (h / 2));
					g.fillOval(x, -y, r, r);
				}
				break;
			case 3:
				g.translate(0, h / 2);
				for (int i = 0; i <= w; i++) {
					int r = 30;
					int x = i;
					int y = (int) (Math.tan(2 * Math.PI * i / w) * (h / 2));
					g.fillOval(x, -y, r, r);
				}
				break;
			case 4:
				g.translate(0, h);
				for (int i = 0; i <= w; i++) {
					int r = 30;
					int x = i;
					int y = (int) ((Math.exp(1.0 * x / w) - 1) * (h / 2 + 90));
					g.fillOval(x, -y, r, r);
				}
				break;
			case 5:
				g.translate(0, h);
				for (int i = 0; i <= w; i++) {
					int r = 30;
					int x = i;
					int y = (int) (Math.log(1.0 * x / w) * (h / 4));
					g.fillOval(x, y, r, r);
				}
			}
			File file = new File(
					"C:\\Users\\ujjwa\\Desktop\\Practice\\Winter\\WinterImages\\Curves\\blank" + j + ".png");
			ImageIO.write(bi, "png", file);
			g.dispose();
		}
	}

	public static void main(String[] args) throws IOException {

		// drawCurve(1920, 1080);
		displayImage();
		// g2d.setComposite(AlphaComposite.Src);
	}
}
