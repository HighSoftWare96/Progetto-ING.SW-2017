package it.RGB.is.GUI;

public class GUIMain implements Runnable {

	private static MainFrame frame;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		frame = new MainFrame("Music Store 1.0");
	}

	public static MainFrame getFrame() {
		return frame;
	}
}
