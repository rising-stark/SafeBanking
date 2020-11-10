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
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.bankDAO;
import com.bank.model.BankDetails;
import com.bank.model.EmailVerifyAttemptDetails;
import com.bank.model.ImageLogDetails;
import com.bank.model.PhotoCountDetails;
import com.bank.model.PicPreferenceDetails;
import com.bank.model.RegisteringLogDetails;
import com.bank.model.RegistrationAddLogDetails;
 
@WebServlet(asyncSupported = true,urlPatterns = { "/Registration2" })
public class Registration2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static Random ran = new Random();
	//static String pathDir = "C:\\Users\\ujjwa\\Desktop\\Practice\\Winter\\WinterImages\\";
	static String pathDir = "/images/";
	//static String pathDir = "/opt/tomcat/webapps/ROOT/WEB-INF/classes/images/";
	private static final int X = 180;
	private static final int Y = 155;
	private static final int emailMaxAttempt=5;
	private static final int effects=5;
	private static final int hide = 106;
    
    public Registration2() {
        super();
    }
    
    public static void print2D(int mat[][]) {
		for (int[] row : mat)
			System.out.println(Arrays.toString(row));
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
				img = (Image) loadImage(index[i][0]+"/" + index[i][1] + "/" + index[i][2] + ".png");

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

	private void choosePicsample(int[][] index, String code, int language) {
		int i;
		for (i = 0; i < 6; i++) {
			char c = code.charAt(i);
			int num = 1;//ran.nextInt(5) + 1;
			// System.out.println("i= "+i+" Character is "+c+" Random ="+num);
			// if (Character.isLetter(c))
			// c = Character.toUpperCase(c);

			// index 0 is for language
			// index 1 is for character of code
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
			int exparr[] = { 0, 880, 320 - x, 1060 - y, 380, 860, 700 - x, 1050 - y, 640, 780, 960 - x, 950 - y, 1100,
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

	private void choosePref(int[][] pref, int effect) {
		int i;
		// rows 0=effectid, 1=language 2=scaleX, 3=scaleY, 4=rotation, 5=opacity, 6=shearX, 7=shearY
		pref[0][0] = effect;
		//bankDAO u = new bankDAO();
		pref[1][0] = 0;//0 is English for now else pp.getLanguage();
		for (i = 0; i < 6; i++) {
			pref[2][i] = ran.nextInt(40) + 100;
			pref[3][i] = ran.nextInt(40) + 100;
			pref[4][i] = ran.nextInt(30) - 15;
			pref[5][i] = ran.nextInt(10) + 90;

			pref[6][i] = ran.nextInt(69);
			if (pref[6][i] < 30)
				pref[7][i] = ran.nextInt(89);
			else if (pref[6][i] > 40 && pref[5][i] < 70)
				pref[7][i] = ran.nextInt(25) + 25;
			else
				pref[7][i] = ran.nextInt(15);
		}
	}

	private void rectify(Graphics2D g, String code, int effect, int w, int h) {
		int pref[][] = new int[8][6];
		int coord[] = new int[12];
		int index[][] = new int[6][3];

		choosePref(pref, effect);
		getCoord(coord, w, h, pref[0][0]);
		choosePicsample(index, code, pref[1][0]);
		//displayCurve(g, pref[0][0]);
		displayImage(g, coord, pref, index);
	}
    
    @SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession(true);
		try {
			String picids = request.getParameter("picid");
			String effectids = request.getParameter("effectid");
			String action=request.getParameter("action");
			String regid=(String)session.getAttribute("regid");
			
			if(picids!=null && effectids==null) {//System.out.println("inside 1 if");
				int picid = Integer.parseInt(picids);
				//System.out.println("\n\nFunction for selecting base image will be called ");
				Graphics2D g;
				BufferedImage bImageFromConvert;
				ArrayList<String> list = (ArrayList<String>)session.getAttribute("list");
				bImageFromConvert = loadImage("ChoosenImages/show/" + list.get(picid) + ".jpg");
				/*String uname=((BankDetails)(session.getAttribute("bank"))).getUname();
				String acc=((BankDetails)(session.getAttribute("bank"))).getAcc();
				new File("C:\\Users\\ujjwa\\Desktop\\Practice\\Winter\\WinterImages\\PostProcessing_Analysis\\FinalLatest\\"+uname+acc).mkdirs();
				file= new File(
						"C:\\Users\\ujjwa\\Desktop\\Practice\\Winter\\WinterImages\\PostProcessing_Analysis\\FinalLatest\\"+uname+acc+"\\" + list.get(picid) + ".jpg");
				ImageIO.write(bImageFromConvert, "jpg", file);*/
				if (bImageFromConvert != null) {
					g = bImageFromConvert.createGraphics();
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
					return;
				}
				else {
					System.out.println("\nImage can't be loaded\n");
				}
			}
			else if(picids!=null && effectids!=null) {//System.out.println("inside 2 if");
				int picid = Integer.parseInt(picids);
				int effectid = Integer.parseInt(effectids);
				String effectorder=(String)session.getAttribute("effectorder");
				if(effectid<=5 && effectid>=1) {
					//System.out.println("effect id "+effectid);
					Graphics2D g;
					BufferedImage bImageFromConvert;
	
					ArrayList<String> list = (ArrayList<String>)session.getAttribute("list");
					bImageFromConvert = loadImage("ChoosenImages/show/" + list.get(picid) + ".jpg");
	
					//System.out.println("Image loaded");
					if (bImageFromConvert != null) {
						int h = bImageFromConvert.getHeight();
						int w = bImageFromConvert.getWidth();
						String str="123456";
						// System.out.println("height = "+height+"width ="+width);
	
						g = bImageFromConvert.createGraphics();
				        rectify(g,str, (effectorder.charAt(effectid-1)-'0'), w, h);
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
						return;
					}
					else {
						System.out.println("\nImage after effect can't be loaded\n");
					}
				}
				return;
			}
			else if(action!= null && action.equals("setPosition")) {
				System.out.println("\nSession attribute for position is set\n");
				session.setAttribute("position", request.getParameter("position"));
				return;
			}
			else if(action!=null && action.equals("setRegpage2time")) {
				String time = request.getParameter("regpage2time").trim();
				String uname=((BankDetails)(session.getAttribute("bank"))).getUname();
				
				AddRegisteringLogList.addTime(regid, uname, time, "Registration2.jsp", "Successfully Loaded",request,response);
				return;
			}
			
			System.out.println("\nRegistration2.java for Register starts\n");
			
			ShowAll show = new ShowAll();
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			bankDAO u = new bankDAO();
			RegistrationAddLogDetails regLog=(RegistrationAddLogDetails)session.getAttribute("regLog");
			BankDetails bank=(BankDetails)session.getAttribute("bank");
			PicPreferenceDetails pre=new PicPreferenceDetails();
			ImageLogDetails imgLog=new ImageLogDetails();
			PhotoCountDetails pc=new PhotoCountDetails();
			EmailVerifyAttemptDetails emailAttempt=new EmailVerifyAttemptDetails();
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> list1 = new ArrayList<String>();
			
			// Here picids is the position of the image in the slot of 8 i.e. 0-7
			picids = request.getParameter("picids");
			list=(ArrayList<String>)session.getAttribute("list");
			String effectorder=(String)session.getAttribute("effectorder");
			
			// picid is the actual image id like show46
			String picid = list.get(Integer.parseInt(picids));
			int position=Integer.parseInt((String)session.getAttribute("position"));
			int effectid=effectorder.charAt(position-1)-'0';
			int language=0;//Add the language bar and update it
			//System.out.println("\n\nRegistration2.java starts for picids "+picids+" and session effectid= "+effectid+" param piceffect "+request.getParameter("piceffect"));
			
			String effecttime = request.getParameter("effecttime").trim();
			String pictime = request.getParameter("pictime").trim();
			String subtime2 = request.getParameter("subtime2").trim();
			String regpage2time = request.getParameter("regpage2time").trim();
			String backtime = request.getParameter("backtime").trim();
			
			String uname=bank.getUname();
			String acc=bank.getAcc();
			String pass=u.generatePassword();
			int success=2;
			String message="Registration Details Complete without Email Verfication";
			
			AddRegisteringLogList.addTime(regid, uname, subtime2, "Registration2.java", "Processing Information",request,response);
			
			/* int balance=u.retrieveBalance(uname);
			 * 
			 * Here the balance in bank table is hardcoded to be 10000 but
			 * it should have been set to savings column of account table.
			 * Now it can't be set as foreign key as savings column of account
			 * table is not unique and here first time when account is created
			 * the balance should be 10000. The retrieve balance function works
			 * on uname basis but here there is no such user exists till now 
			 * at the time of registration so, 10000 set is good.
			 * 
			 * */
			bank.setBalance(1000);
			bank.setPicid(picid);
			bank.setPassword(pass);
			bank.setSuccess(success);
			bank.setMessage(message);
			
			pre.setUname(uname);
			pre.setCurve(effectid);
			pre.setLanguage(language);
			//pre.setOpacity();
			//pre.setScale();
			//pre.setRotation();
			//pre.setSkewX();
			//pre.setSkewY();
			
			regLog.setRegpage2time(regpage2time);
			regLog.setPictime(pictime);
			regLog.setEffecttime(effecttime);
			regLog.setPicid(picid);
			regLog.setEffectid(effectid);
			regLog.setLanguage(language);
			regLog.setSubtime2(subtime2);
			regLog.setAttempt(emailMaxAttempt);
			regLog.setSuccess(success);
			regLog.setMessage(message);
			
			imgLog.setId(regid);
			imgLog.setAcc(acc);
			imgLog.setDateaccessed(subtime2);
			imgLog.setActionpage("Registration2.jsp after submitting. Chosen Login Image");
			imgLog.setImagestring(picid);
			
			list1.add(picid);
			pc.setId(regid);
			pc.setType("choosereg");
			pc.setList(list1);
			pc.setDateaccessed(subtime2);
			
			emailAttempt.setUname(uname);
			emailAttempt.setRegid(regid);
			emailAttempt.setDate(subtime2);
			emailAttempt.setAttempt(emailMaxAttempt);
			
			// picid is the actual image id like show46
			String loginimagestring=u.encodeLogin(acc, Integer.parseInt(picid.substring(4)),list, effectid, effects);
			AddRegisteringLogList.add(regid, uname, "Registration2.java", message,request,response);
			
			u.insertBankDetails(bank,1);
			u.insertRegistrationAddLogDetails(regLog,1);
			u.insertImageLog(imgLog);
			u.insertPhotoCountDetails(pc);
			u.updateAccountLoginImageString(acc, loginimagestring, subtime2);
			u.insertPicPreferenceDetails(pre);
			//u.insertEmailVerifyAttempt(emailAttempt);
			u.insertRegisteringLogDetails((ArrayList<RegisteringLogDetails>)session.getAttribute("registeringLogList"));
			AddRegisteringLogList.clear(request,response);
			
			session.removeAttribute("bank");
			session.removeAttribute("regLog");
			session.removeAttribute("list");
			session.removeAttribute("effectorder");
			session.removeAttribute("position");
			session.removeAttribute("REGISTER");
			
			session.setAttribute("verify", bank);
			
			System.out.println("\nRegistration2.java will end after showing all\n");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			response.sendRedirect("EmailVerify.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}
}