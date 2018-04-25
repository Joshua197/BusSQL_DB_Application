package buses.View;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class Main {

	public final static int MAIN_WIDTH=800;
	public final static int MAIN_HEIGHT=600;
	public final static String IMGURL=  "C:\\Users\\ADMN\\eclipse-workspace\\FinalMaven\\src\\main\\java\\images\\welcome.jpg";
	
	protected JFrame mainFrame;
	private BusController busController;
	
	public Main() {
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					new Main().init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected void init() throws Exception
	{
		this.busController = new BusController();
		createGUI();
	}
	
	protected void createGUI() throws MalformedURLException {
		this.mainFrame = new JFrame("Bus Management Administration"); //$NON-NLS-1$
		this.mainFrame.setSize(MAIN_WIDTH, MAIN_HEIGHT);
		this.mainFrame.setLocationRelativeTo(null);
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		{
		  this.mainFrame.setContentPane(new JLabel
		(new ImageIcon(getImage("images/welcome.jpg"))));
	        this.mainFrame.pack();
	        this.mainFrame.setVisible(true);
	        this.mainFrame.getContentPane().setPreferredSize(new Dimension(600, 600));
	        this.mainFrame.setJMenuBar(createMenubar());
		}
	}
	
	// New Method to impliment the use of a new image
	
	public static Image getImage(final String pathAndFileName) {
	    final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
	    return Toolkit.getDefaultToolkit().getImage(url);
	}
	protected void openBusesForm() {
		this.busController.getBusWindow().setVisible(true);
		
	}
	
	private JMenuBar createMenubar() {
		JMenuBar menubar = new JMenuBar();
		JMenu file=new JMenu("File"); //$NON-NLS-1$
		JMenuItem buses = new JMenuItem("Buses"); //$NON-NLS-1$
		JMenuItem exit=new JMenuItem("Exit"); //$NON-NLS-1$
		buses.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openBusesForm();
				
			}
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				busController.shutdown();
				WindowEvent wev = new WindowEvent(Main.this.mainFrame, WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
				Main.this.mainFrame.dispatchEvent(wev);
				Main.this.mainFrame.setVisible(false);
				Main.this.mainFrame.dispose();
				System.exit(0);
			}
		});
		file.add(buses);
		file.addSeparator();
		file.add(exit);
		menubar.add(file);
		return menubar;
	}
	
}

