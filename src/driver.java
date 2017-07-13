import javax.swing.*;

class driver
{
	public static void main(String[] args)
	{
		Interface window = new Interface();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000, 700);
	}
}
