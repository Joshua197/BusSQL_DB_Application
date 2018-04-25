package buses.View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import buses.Domain.Bus;

/**
 * The Domain Form
 * 
 * @author Joshua Wambua
 *
 */
public class BusPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JTextField drName;
	protected JTextField typeOfBus;
	protected JSpinner maxPass;
	protected JSpinner mxFuel;
	protected JComboBox<String> busColour;
	protected Bus bus;
	protected BusWindow busWindow;
	/**
	 * Creating the Panel
	 * @throws MalformedURLException 
	 */
	public BusPanel() throws MalformedURLException {
		super();
		createGUI();
	}
	
	public void readFormGUI() {
		if (this.bus==null) 
	    this.bus = new Bus();
		this.bus.setDriverName(this.drName.getText());
		this.bus.setMaximumPassengers((int) this.maxPass.getValue());
		this.bus.setMaxFuel((int) this.mxFuel.getValue());
		this.bus.setColour(this.busColour.getSelectedIndex());
	}
	

	/**
	 * Creating the graphical user interface
	 */
		 protected void createGUI() throws MalformedURLException {
		        
		       GridBagLayout gridBagLayout = new GridBagLayout();
		/**
		 *The layout is the GridbagLayout
		 */
		
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		
		//TODO If you need more rows, you can add a 0 to rowHeights array
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		// The Driver Name Label
		JLabel lbName = new JLabel("Driver Name"); //$NON-NLS-1$
		GridBagConstraints gbc_lbName = new GridBagConstraints();
		gbc_lbName.insets=new Insets(10, 5, 5, 5);
		gbc_lbName.anchor = GridBagConstraints.EAST;
		gbc_lbName.insets = new Insets(0, 0, 5, 5);
		gbc_lbName.gridx = 0;							// column index
		gbc_lbName.gridy = 0;							// row index
		add(lbName, gbc_lbName);
		
		// The Driver Name TextField
		this.drName = new JTextField();
		GridBagConstraints gbc_tfName = new GridBagConstraints();
		gbc_tfName.insets = new Insets(10, 0, 5, 5);
		gbc_tfName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfName.gridx = 2;							// column index
		gbc_tfName.gridy = 0;							// row index
		add(this.drName, gbc_tfName);
		this.drName.setColumns(40);
		this.drName.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (BusPanel.this.bus!=null) 
					BusPanel.this.bus.setDriverName(BusPanel.this.drName.getText());
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				//NC
			}
		});
		// The Label for the Maximum Passengers
		
		JLabel lbMaxPass = new JLabel("Max Passengers"); //$NON-NLS-1$
		GridBagConstraints gbc_lbMaxPass = new GridBagConstraints();
		gbc_lbMaxPass.anchor = GridBagConstraints.EAST;
		gbc_lbMaxPass.insets = new Insets(0, 0, 5, 5);
		gbc_lbMaxPass.gridx = 0;						// column index
		gbc_lbMaxPass.gridy = 2;						// row index
		add(lbMaxPass, gbc_lbMaxPass);
		
		//TODO memory spinner 
		this.maxPass = new JSpinner();
		// model for spinner first 0 is the current value, second 0 is the minimum value, 120 is the maximum value, 1 is the step
		this.maxPass.setModel(new SpinnerNumberModel(0, 0, 120, 1));		
		GridBagConstraints gbc_spMaxPass = new GridBagConstraints();
		gbc_spMaxPass.anchor = GridBagConstraints.WEST;
		gbc_spMaxPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_spMaxPass.insets = new Insets(0, 0, 5, 5);
		gbc_spMaxPass.gridx = 2;						// column index
		gbc_spMaxPass.gridy = 2;						// row index
		this.maxPass.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (BusPanel.this.bus!=null)
					BusPanel.this.bus.setMaximumPassengers(((Number)BusPanel.this.maxPass.getValue()).intValue());
			}
		});
		add(this.maxPass, gbc_spMaxPass);
		
		// The Maximum Amount of Fuel Label
		JLabel lblMaxFuel = new JLabel("Max Fuel(Litres)"); //$NON-NLS-1$
		GridBagConstraints gbc_lblMaxFuel  = new GridBagConstraints();
		gbc_lblMaxFuel.anchor = GridBagConstraints.EAST;
		gbc_lblMaxFuel.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxFuel.gridx = 0;		//column index
		gbc_lblMaxFuel.gridy = 3;		//row index
		add(lblMaxFuel, gbc_lblMaxFuel);
		
		//The Maximum Amount of Fuel spinner
		this.mxFuel = new JSpinner();
		// model for spinner first 0 is the current value, second 0 is the minimum value, 150 is the maximum value, 1 is the step
		this.mxFuel.setModel(new SpinnerNumberModel(0, 0, 150, 1)); 
		GridBagConstraints gbc_spFuel = new GridBagConstraints();
		gbc_spFuel.fill = GridBagConstraints.HORIZONTAL;
		gbc_spFuel.anchor = GridBagConstraints.WEST;
		gbc_spFuel.insets = new Insets(0, 0, 5, 5);
		gbc_spFuel.gridx = 2;						//column index
		gbc_spFuel.gridy = 3;						//row index
		this.mxFuel.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (BusPanel.this.bus!=null)
					BusPanel.this.bus.setMaxFuel(((Number)BusPanel.this.mxFuel.getValue()).intValue());
			}
		});
		add(this.mxFuel, gbc_spFuel);
		
		//The Bus Color label
		JLabel lblColor = new JLabel("Bus Color"); //$NON-NLS-1$
		GridBagConstraints gbc_lblColor = new GridBagConstraints();
		gbc_lblColor.anchor = GridBagConstraints.EAST;
		gbc_lblColor.insets = new Insets(0, 0, 0, 5);
		gbc_lblColor.gridx = 0;					//column index
		gbc_lblColor.gridy = 4;					//row index
		add(lblColor, gbc_lblColor);
		
		//TODO display type combo box
		this.busColour = new JComboBox<>(Bus.COLOUR_VALUES);
		GridBagConstraints gbc_cbColor = new GridBagConstraints();
		gbc_cbColor.insets = new Insets(0, 0, 0, 5);
		gbc_cbColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbColor.gridx = 2;					//column index
		gbc_cbColor.gridy = 4;					//row index
		this.busColour.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (BusPanel.this.bus!=null)
					BusPanel.this.bus.setColour(BusPanel.this.busColour.getSelectedIndex());
			}
		});
		add(this.busColour, gbc_cbColor);
	}
	/**
	 * Set the current bus data
	 * 
	 * @param computer the current data
	 */
	public void setData(Bus bus) {
		this.bus = bus;
		if (this.bus!=null) {
			this.drName.setText(bus.getDriverName());
			this.maxPass.setValue(Integer.valueOf(bus.getMaximumPassengers()));
			this.mxFuel.setValue(Integer.valueOf(bus.getMaxFuel()));
			this.busColour.setSelectedIndex(bus.getColour());	
		}
	}
	
	/**
	 * Returns the current data object
	 * 
	 * @return the current data
	 */
	
	public Bus getData() {
		return this.bus;
	}
}
