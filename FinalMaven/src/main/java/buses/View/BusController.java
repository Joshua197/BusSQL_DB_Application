package buses.View;

import java.util.List;

import buses.Dao.BusesDao;
import buses.Domain.Bus;

/**
 * Controller for MVC design pattern
 * 
 * @author Joshua Wambua
 *
 */
public class BusController {

	/**
	 * DAO
	 */
	private BusesDao busesDao;
	/**
	 * Form
	 */
	private BusWindow busWindow;

	/**
	 * Current Bus
	 */
	private Bus currentBus;

	/**
	 * All Bus list
	 */
	private List<Bus> all;

	/**
	 * Constructor
	 * 
	 * @throws Exception
	 */
	public BusController() throws Exception {
		this.busesDao = BusesDao.createBusDao();
		this.busWindow = new BusWindow(this);
		first();
	}

	/**
	 * Getter for Bus Window
	 * 
	 * @return Bus Window
	 */
	public BusWindow getBusWindow() {
		return this.busWindow;
	}

	/**
	 * Load all buses list and select first element as current bus if the list is
	 * not empty
	 */
	public void first() {

		this.all = this.busesDao.listOrderById();
		if (this.all.size() > 0)
			setCurrent(this.all.get(0));
		else
			this.setCurrent(new Bus());
		updateGUI();
	}

	/**
	 * Select previous bus in the list, if the list is not empty and the current bus
	 * is not the first element in the list.
	 */
	public void prev() {
		if (this.currentBus != null && this.all.indexOf(this.currentBus) > 0) {
			setCurrent(this.all.get(this.all.indexOf(this.currentBus) - 1));

			updateGUI();
		}
	}

	/**
	 * Select next bus in the list if the list is not empty and the current bus is
	 * not the last element in the list.
	 */

	public void next() {
		if (this.currentBus != null && this.all.indexOf(this.currentBus) < this.all.size() - 1) {
			setCurrent(this.all.get(this.all.indexOf(this.currentBus) + 1));
		}
		updateGUI();
	}

	/**
	 * Create a new bus and set it to current
	 */
	public void insert() {

		setCurrent(this.busWindow.getBusPanel().getData());
		Bus bus = new Bus();

		bus.setDriverName(this.currentBus.getDriverName());
		bus.setMaximumPassengers(this.currentBus.getMaximumPassengers());
		bus.setMaxFuel(this.currentBus.getMaxFuel());
		bus.setColour(this.currentBus.getColour());

		@SuppressWarnings("unused")
		Bus bus2 = this.busesDao.insert(bus);
		updateGUI();
	}

	/**
	 * update current bus data to database
	 */
	public void update() {
		if (this.currentBus == null)
			setCurrent(this.busWindow.getBusPanel().getData());
		if (this.currentBus.getId() == null)
			setCurrent(this.busesDao.insert(this.currentBus));
		else
			this.busesDao.update(this.currentBus);
		Integer id = this.currentBus.getId();
		first();
		for (Bus c : this.all)
			if (c.getId().equals(id)) {
				setCurrent(c);
				updateGUI();
				break;
			}
	}

	/**
	 * Delete the current bus from database
	 */
	public void delete() {
		try {
			this.busesDao.delete(this.currentBus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		first();
	}

	/**
	 * Close the bus window
	 */
	public void close() {
		this.busWindow.setVisible(false);
	}

	public void shutdown() {
		this.busesDao.shutdown();
	}

	/**
	 * Update Graphical User Interface
	 * 
	 * Setting the state of buttons
	 */
	private void updateGUI() {
		this.busWindow.btnInsert.setEnabled(this.currentBus != null);
		this.busWindow.btnInsert.setEnabled(true);
		this.busWindow.btnDelete.setEnabled(this.currentBus != null);
		this.busWindow.btnUpdate.setEnabled(this.currentBus != null);

		int idx = this.all.indexOf(this.currentBus);
		this.busWindow.btnPrev.setEnabled(this.currentBus != null && idx != 0);
		this.busWindow.btnNext.setEnabled(this.currentBus != null && idx < this.all.size() - 1);
	}

	/**
	 * Set the current bus
	 * 
	 * @param c
	 *            the current computer
	 */
	private void setCurrent(Bus c) {
		this.currentBus = c;
		this.busWindow.getBusPanel().setData(c);
	}

}
