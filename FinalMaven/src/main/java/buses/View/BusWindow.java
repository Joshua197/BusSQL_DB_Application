package buses.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Computer form
 * @author Joshua Wambua
 *
 */
public class BusWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Bus View
	 */
	private BusPanel busView;
	/**
	 * previous button
	 */
	protected JButton btnPrev;
	/**
	 * insert button
	 */
	protected JButton btnInsert;
	/**
	 * update button
	 */
	protected JButton btnUpdate;
	/**
	 * delete button
	 */
	protected JButton btnDelete;
	/**
	 * next button
	 */
	protected JButton btnNext;
	/**
	 * close form button
	 */
	protected JButton btnClose;
	/**
	 * Controller
	 */
	protected BusController controller;
	
	/**
	 * Constructor
	 * 
	 * @param controller
	 * @throws MalformedURLException 
	 */
	public BusWindow(BusController controller) throws Exception {
		super();
		setLayout(new BorderLayout());
		this.controller = controller;
	     createGUI();
		 pack();
	}

	/**
	 * getter for bus view
	 * @return
	 */
	public BusPanel getBusPanel() {
		return this.busView;
	}

	/**
	 * Getter for previous button
	 * @return
	 */
	public JButton getPrevButton() {
		return this.btnPrev;
	}
	
	/**
	 * Getter for insert button
	 * @return
	 */
	public JButton getInsertButton() {
		return this.btnInsert;
	}
	
	/**
	 * Getter for update button
	 * @return
	 */
	public JButton getUpdateButton() {
		return this.btnUpdate;
	}
	
	/**
	 * Getter for delete button
	 * @return
	 */
	public JButton getDeleteButton() {
		return this.btnDelete;
	}
	
	/**
	 * Getter for next button
	 * @return
	 */
	public JButton getNextButton() {
		return this.btnNext;
	}

	/**
	 * Create graphical user interface
	 * @throws MalformedURLException 
	 */
	private void createGUI() throws MalformedURLException {
		this.busView = new BusPanel();
		add(this.busView, BorderLayout.CENTER);
		add(createButtons(), BorderLayout.SOUTH);
	}
	
	/**
	 * Create buttons
	 * 
	 * @return
	 */
	private JPanel createButtons() {
		JPanel result = new JPanel();
		result.setLayout(new FlowLayout());
		
		this.btnPrev = new JButton("Prev"); //$NON-NLS-1$
		this.btnPrev.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BusWindow.this.busView.readFormGUI();
				BusWindow.this.controller.prev();	
			}
		});
		result.add(this.btnPrev);
		
		this.btnInsert = new JButton("Insert"); //$NON-NLS-1$
		this.btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BusWindow.this.busView.readFormGUI();
				BusWindow.this.controller.insert();
				
			}
		});
		result.add(this.btnInsert);
		
		this.btnUpdate=new JButton("Update"); //$NON-NLS-1$
		this.btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BusWindow.this.busView.readFormGUI();
				BusWindow.this.controller.update();
			}
		});
		result.add(this.btnUpdate);
		
		this.btnDelete = new JButton("Delete"); //$NON-NLS-1$
		this.btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BusWindow.this.busView.readFormGUI();
				BusWindow.this.controller.delete();
			}
		});
		result.add(this.btnDelete);
		
		this.btnNext = new JButton("Next"); //$NON-NLS-1$
		this.btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BusWindow.this.controller.next();
			}
		});
		result.add(this.btnNext);
		
		this.btnClose = new JButton("Close"); //$NON-NLS-1$
		this.btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BusWindow.this.busView.readFormGUI();
				BusWindow.this.controller.close();
			}
		});
		result.add(this.btnClose);
		
		return result;
	}
}
