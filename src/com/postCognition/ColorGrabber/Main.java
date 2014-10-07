package com.postCognition.ColorGrabber;


import javax.swing.JOptionPane;
public class Main implements Runnable {
	static int a = 255, r = 0, g = 0, b = 0;
	static String r1, g1, b1;

	public static void main(String[] args) {
		Main m = new Main();
        System.out.println("testing");
		m.start();
	}

	public void start() {
		new Thread(this).start();
	}

	public void run() {
		r1 = JOptionPane.showInputDialog(null, "R = ?");
		g1 = JOptionPane.showInputDialog(null, "G = ?");
		b1 = JOptionPane.showInputDialog(null, "B = ?");

		r = Integer.parseInt(r1.replaceAll("[a-zA-Z]", ""));
		g = Integer.parseInt(g1.replaceAll("[a-zA-Z]", ""));
		b = Integer.parseInt(b1.replaceAll("[a-zA-Z]", ""));

		int value = ((a & 0x0ff) << 24 | (r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
		JOptionPane.showMessageDialog(null, "Java pixel vale = " + value);
	}
}