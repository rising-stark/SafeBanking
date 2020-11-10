package com.bank.additional;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainClass extends JPanel {

  public void paint(Graphics g2) {
	  Graphics2D g=(Graphics2D) g2;
    Dimension d = this.getPreferredSize();
    int fontSize = 20;

    g.setFont(new Font("Devnagari", Font.PLAIN, fontSize));
     
    g.setColor(Color.red);
    final float dash1[] = {10.0f};
    final BasicStroke dashed =
        new BasicStroke(1.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
	g.setStroke(dashed);
    g.drawString("\u0966\u0967\u0968\u0969\u096A\u3058   x", 10, 20);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.getContentPane().add(new MainClass());

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200,200);
    frame.setVisible(true);
  }
}