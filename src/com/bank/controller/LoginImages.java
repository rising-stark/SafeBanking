package com.bank.controller;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.model.LoginLogDetails;

@WebServlet("/LoginImages")
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class LoginImages extends HttpServlet {

	private static final long serialVersionUID = 1L;	
	static Random ran = new Random();
	//static String pathDir = "C:\\Users\\ujjwa\\Desktop\\Practice\\Winter\\WinterImages\\Current\\";
	static String pathDir = "/images/";
	//static String pathDir = "/opt/tomcat/webapps/ROOT/WEB-INF/classes/images/";
	private static final int X = 180;
	private static final int Y = 145;
	private static final int effects=5;
	private static final int hide = 106;
	
	public LoginImages() {
		super();
	}
	
	private static BufferedImage loadImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(Registration2.class.getResource(pathDir+path));
			//File file = new File(pathDir+path);
			//img = ImageIO.read(file);
	} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
    
	private static void displayImage(Graphics2D g, int[] coord, int[][] pref, int[][] index) {
		try {
			int i, dx1, dx2, dy1, dy2, sx1, sx2, sy1, sy2;
			Image img;
			ImageObserver obs;
			ArrayList<Integer> list1 = new ArrayList<Integer>();

			for (i = 1; i <= 3; i++) {
				list1.add(i);
			}

			for (i = 0; i < 6; i++) {
				int j, ord, rotx, roty;
				float sx, sy, shx, shy;
				AffineTransform org = g.getTransform();
				Composite orc = g.getComposite();
				AffineTransform at = new AffineTransform();
				Collections.shuffle(list1);
				img = (Image) loadImage(
						index[i][0]+"/" + index[i][1] + "/" + index[i][2] + ".png");

				sx = sy = shx = shy = rotx = roty = sx1 = sy1 = 0;
				sx2 = img.getWidth(null);
				sy2 = img.getHeight(null);
				dx1 = coord[2 * i];
				dy1 = coord[2 * i + 1];
				obs = null;

				// Correcting the coordinates. ord 1=scale 2=shear 3=rotate
				for (j = 2; j >= 0; j--) {
					ord = list1.get(j);
					if (ord == 1) {
						sx = (float) (pref[2][i] / 100.0);
						sy = (float) (pref[3][i] / 100.0);
						dx1 = (int) Math.round(dx1 / sx);
						dy1 = (int) Math.round(dy1 / sy);
					} else if (ord == 3) {
						rotx = dx1;
						roty = dy1;
					} else {
						shx = (float) (pref[6][i] / 100.0);
						shy = (float) (pref[7][i] / 100.0);
						int k = (int) Math.round((dx1 - shx * dy1) / (1 - shx * shy));
						dy1 = (int) Math.round((dy1 - shy * dx1) / (1 - shx * shy));
						dx1 = k;
					}
				}

				// Applying transform
				for (j = 2; j >= 0; j--) {
					ord = list1.get(j);
					if (ord == 1)
						at.scale(sx, sy);
					else if (ord == 3)
						at.rotate(pref[4][i] * Math.PI / 180.0, rotx, roty);
					else
						at.shear(shx, shy);
				}
				g.setTransform(at);
				Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (pref[5][i] / 100.0));
				g.setComposite(c);
				// g2d.setFont(new Font("Georgia", Font.BOLD, 200));
				g.drawImage(img, dx1, dy1, dx1 + X, dy1 + Y, sx1, sy1, sx2, sy2, obs);
				g.setTransform(org);
				g.setComposite(orc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void choosePicSample(int[][] index, String code, int language) {
		int i;
		for (i = 0; i < 6; i++) {
			char c = code.charAt(i);
			int num = ran.nextInt(15) + 1;
			// System.out.println("i= "+i+" Character is "+c+" Random ="+num);
			// if (Character.isLetter(c))
			// c = Character.toUpperCase(c);

			// index 0 is for language
			// index 1 is for character of random code entered
			// index 2 is for random image number
			index[i][0] = language;
			index[i][1] = c - 48;
			index[i][2] = num;
			// System.out.println("index["+i+"]"+index[i]);
		}
		//print2D(index);
	}

	private static void getCoord(int[] coord, int width, int hight, int curve) {
		int i, l, h;
		int x = X, y = Y;
		switch (curve) {
		case 1: {
			int sinarr[] = { 0, 180, 320 - x, 540 - y, 320, 0, 640 - x, 180 - y, 640, 180, 960 - x, 540 - y, 960, 540,
					1280 - x, 900 - y, 1280, 850, 1600 - x, 1040 - y, 1600, 540, 1920 - x, 900 - y };
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
			int cosarr[] = { 0, 0, 320 - x, 180 - y, 500, 360, 700 - x, 720 - y, 640, 850, 960 - x, 1040 - y, 960, 850,
					1280 - x, 1040 - y, 1200, 360, 1420 - x, 720 - y, 1600, 0, 1920 - x, 180 - y };
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
			/*int exparr[] = { 0, 900, 320 - x, 1080 - y, 320, 800, 640 - x, 1000 - y, 640, 640, 960 - x, 850 - y, 960,
					480, 1280 - x, 690 - y, 1280, 250, 1600 - x, 500 - y, 1700, 0, 1920 - x, 250 - y };*/
			int exparr[] = { 0, 920, 320 - x, 1080 - y, 380, 860, 700 - x, 1050 - y, 640, 780, 960 - x, 950 - y, 1100,
					630, 1440 - x, 850 - y, 1300, 350, 1550 - x, 550 - y, 1660, 0, 1860 - x, 300 - y };
			//System.out.print(exparr);
			for (i = 0; i < 6; i++) {
				l = exparr[4 * i];
				h = exparr[4 * i + 2];
				//System.out.println("h= "+h+" l= "+l);
				coord[2 * i] = ran.nextInt(h - l) + l;
				l = exparr[4 * i + 1];
				h = exparr[4 * i + 3];
				coord[2 * i + 1] = ran.nextInt(h - l) + l;
			}
		}
			break;
		case 4: {
			int logarr[] = { 50, 0, 200 - x, 350 - y, 200, 350, 325 - x, 550 - y, 320, 580, 640 - x, 780 - y, 640, 760,
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
			int stlinearr[] = { 0, 900, 320 - x, 1080 - y, 320, 720, 640 - x, 900 - y, 640, 540, 960 - x, 720 - y, 960,
					360, 1280 - x, 540 - y, 1280, 180, 1600 - x, 360 - y, 1600, 0, 1920 - x, 180 - y };
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

	private void choosePref(int[][] pref, int effect, int language) {
		int i;
		// rows 0=effectid1, 1=language1 2=scaleX, 3=scaleY, 4=rotation, 5=opacity, 6=shearX, 7=shearY
		/*if(effect==0)
			pref[0][0] = ran.nextInt(5)+1;
		else
			*/pref[0][0] = effect;
		pref[1][0] = language;
		for (i = 0; i < 6; i++) {
			pref[2][i] = ran.nextInt(40) + 100;
			pref[3][i] = ran.nextInt(40) + 100;
			pref[4][i] = ran.nextInt(30) - 15;
			pref[5][i] = ran.nextInt(10) + 80;

			pref[6][i] = ran.nextInt(69);
			if (pref[6][i] < 30)
				pref[7][i] = ran.nextInt(89);
			else if (pref[6][i] > 40 && pref[5][i] < 70)
				pref[7][i] = ran.nextInt(25) + 25;
			else
				pref[7][i] = ran.nextInt(15);
		}
	}

	private void rectify(Graphics2D g, int w, int h, String code, int effect, int language) {
		int pref[][] = new int[8][6];
		int coord[] = new int[14];
		int index[][] = new int[6][3];

		choosePref(pref, effect, language);
		getCoord(coord, w, h, pref[0][0]);
		choosePicSample(index, code, pref[1][0]);
		//displayCurve(g, pref[0][0]);
		//displayTimeStamp(coord,w,h);
		displayImage(g, coord, pref, index);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session = request.getSession(true);
			GetTime obj = new GetTime();
			String now = obj.now();

			String action=request.getParameter("action");
			
			System.out.println("\n"+action);
			
			if(action!= null && action.equals("Refresh")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
		    	
				String loginid=(String)session.getAttribute("loginid");
				String uname = request.getParameter("uname");
				int refresh=(int)session.getAttribute("refresh");
				
				/*Now here, there could be all sorts of customised shuffling based on any suspicion factor
				 * or based on which position to show which image and after how many complete refreshes.
				 * For now, I have kept it to be just cyclic.
				 * 
				 * */
				
				refresh=(++refresh)%4;
				AddLoggingInLogList.add(loginid, uname, "LoginImages.java", "Refresh Button Clicked. Remaining "+(3-refresh),request,response);
				
				response.getWriter().write(""+refresh);
				session.setAttribute("refresh", refresh);
				return;
			}
			else if(action!= null && action.equals("GetPic")) {
				/*Earlier this was used along with JSONObject
				 * 
				 * response.setContentType("application/json");
		        response.setCharacterEncoding("utf-8");

		        int index,type,language1,effectid1;
		        int language,effectid;
		        String picid,picid1;
		        ArrayList<String> list = new ArrayList<String>();
		        JSONObject JSONobj = new JSONObject();

				index=Integer.parseInt(request.getParameter("index"));
				type=Integer.parseInt(request.getParameter("type"));
				picid=(String)session.getAttribute("picid");
				effectid=(int)session.getAttribute("effectid");
				language=(int)session.getAttribute("language");
				list=(ArrayList<String>)session.getAttribute("list");
				
				System.out.println("index "+index+" type "+type+" picid "+picid+" effectid "+effectid+" language "+language);
				System.out.println(list);
				
				picid1=list.get( (3- refresh)*3 +index);
				if(picid1.equals("show"+picid)) {
					language1=language;
					effectid1=effectid;
				}else {
					language1=0; // For now since I have only one set of images language1=ran.nextInt(4);
					effectid1=ran.nextInt(5)+1;
				}
				
				JSONobj.put("picid1", picid1);
		        JSONobj.put("language1", language1);
		        JSONobj.put("effectid1", effectid1);
		        JSONobj.put("refresh", refresh);
		        response.getWriter().write(JSONobj.toString());
		        System.out.println(JSONobj);*/
				
				
				LoginLogDetails loginLog =(LoginLogDetails)session.getAttribute("loginLog");
				
				String code=loginLog.getCode();
				String picids = (String) request.getParameter("picids");
				String picid = picids.substring(0, picids.length()-2);
				int effectid = (int)(picids.charAt(picids.length()-2) - '0');
				int language=(int)(picids.charAt(picids.length()-1)  - '0');
				
				System.out.println("code "+code+" action "+action+" picid "+picid+" effectid "+effectid+" language "+language);
				
				Graphics2D g;
				BufferedImage bImageFromConvert;

				bImageFromConvert = loadImage("ChoosenImages//"+picid.substring(0, 4)+"//" + picid + ".jpg");
				//System.out.println("Image loaded");
				if (bImageFromConvert != null) {
					int h = bImageFromConvert.getHeight();
					int w = bImageFromConvert.getWidth();
					// System.out.println("height = "+height+"width ="+width);

					g = bImageFromConvert.createGraphics();
					rectify(g, w, h, code, effectid, language);
					/*File file = new File(
							"C:\\Users\\ujjwa\\Desktop\\" + picEffects + ".jpg");
					ImageIO.write(bImageFromConvert, "jpg", file);*/
					g.dispose();

					response.setContentType("image/jpeg");
					OutputStream o = response.getOutputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(bImageFromConvert, "jpg", baos);

					baos.flush();
					byte[] imageInByte = baos.toByteArray();

					o.write(imageInByte);
					o.flush();
					o.close();
				}
				else {
					System.out.println("\nImage can't be loaded\n");
				}
				return;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request, response);
	}
}