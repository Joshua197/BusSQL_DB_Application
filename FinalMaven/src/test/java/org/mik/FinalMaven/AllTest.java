package org.mik.FinalMaven;

import java.sql.SQLException;
import java.util.List;

import buses.Dao.BusesDao;
import buses.Domain.Bus;
import junit.framework.TestCase;

public class AllTest extends TestCase {
	
	private final static String NAME = "My bus"; //$NON-NLS-1$
	private final static String FIND_NAME = "find bus"; //$NON-NLS-1$
	private final static String MODIFIED_NAME = "My new bus"; //$NON-NLS-1$

	private BusesDao dao;

	public void init() {
		try {
			this.dao = BusesDao.createBusDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void shutdown() {
		this.dao.shutdown();
	}


	public void test() {
		insertUpdateDeleteTest();
		
	}

	public void insertUpdateDeleteTest() {
	  init();

		Bus bus = new Bus();
		bus.setDriverName(NAME);
		assertEquals(NAME.compareTo(bus.getDriverName()), 0);
		bus.setColour(0);
		assertEquals(bus.getColour(),(0));
		bus.setMaxFuel(140);
		assertEquals(bus.getMaxFuel(),(140));
		bus.setMaximumPassengers(40);
		assertEquals(bus.getMaximumPassengers(),(40));	
		Bus newBus = this.dao.insert(bus);
		Integer newId = newBus.getId();
		assertNotNull(newId);
		newBus.setDriverName(MODIFIED_NAME);
		this.dao.update(newBus);
		assertEquals(MODIFIED_NAME.compareTo(newBus.getDriverName()),0);
		try {
			this.dao.delete(newBus);
		}
		catch(Exception e) {
			e.printStackTrace();
			fail();
		}		
	}
		 
	public void selectTest() {
		assertNotNull(this.dao);
		Bus bus = new Bus();
		bus.setDriverName(NAME);
		assertEquals(NAME.compareTo(bus.getDriverName()), 0);
		bus.setColour(0);
		assertEquals(bus.getColour(),(0));
		bus.setMaxFuel(140);
		assertEquals(bus.getMaxFuel(),(140));
		bus.setMaximumPassengers(40);
		assertEquals(bus.getMaximumPassengers(),(40));
		this.dao.insert(bus);
		
	    bus = new Bus();
		bus.setDriverName(NAME);
		assertEquals(NAME.compareTo(bus.getDriverName()), 0);
		bus.setColour(4);
		assertEquals(bus.getColour(),(4));
		bus.setMaxFuel(150);
		assertEquals(bus.getMaxFuel(),(150));
		bus.setMaximumPassengers(140);
		assertEquals(bus.getMaximumPassengers(),(140));
		this.dao.insert(bus);
		
		
		int cnt=0;
		List<Bus> all = this.dao.listOrderById();
		for(Bus c:all) {
			assertNotNull(c.getId());
			++cnt;
		}
		assertEquals(cnt, 2);
	}
	
	public void findTest() {
		assertNotNull(this.dao);
		Bus bus = new Bus();
		bus.setDriverName(FIND_NAME);
		bus.setColour(0);
		bus.setMaxFuel(40);
		bus.setMaximumPassengers(140);
		
		Bus nc=this.dao.insert(bus);
		Bus fc=this.dao.findById(nc.getId());
		assertEquals(nc,  fc);
		fc=this.dao.findByName(FIND_NAME);
		assertEquals(fc, nc);
		try {
			this.dao.delete(fc);
		}
		catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}

