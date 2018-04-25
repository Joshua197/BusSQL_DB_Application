package buses.Domain;

import java.sql.ResultSet;

/**
 * Bus domain 
 * 
 * Inherits AbstractDomain (id field)
 * @author Joshua Wambua
 *
 */
public class Bus extends AbstractDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The Names of each columns in database
	 *  
	 */
	public final static String COL_DRIVERNAME="driverName"; //$NON-NLS-1$
	
	public final static String COL_MAXPASSENGERS="maxPassengers"; //$NON-NLS-1$
	
	public final static String COL_MAXFUEL="maxFuel"; //$NON-NLS-1$
	
	public final static String COL_COLOUR="colour"; //$NON-NLS-1$

	/**
	 * Bus Colors
	 */
	public final static int COLOUR_YELLOW = 0;
	public final static int COLOUR_MAROON = 1;
	public final static int COLOUR_GREY = 2;
	public final static int COLOUR_GOLD = 3;
	public final static int COLOUR_SILVER= 4;
	public final static int COLOUR_UNSPECIFIED=5;
     /**
      * Bus colors as a string array
        */
	public final static String COLOUR_VALUES[]= {"Yellow", "Maroon", "Grey", "Gold", "Silver","UnSpecified" };
	
	/**
	 * Name of DriverName - Assuming each bus has a specific driver
	 */
	private String driverName;
	
	
	/**
	 * Color of the Bus
	 */
	private int colour;
	
	/**
	 * Maximum number of Passengers in a single Bus
	 */
	private int maxPassengers;
	
	/**
	 * Maximum amount of Fuel in a Bus
	 */
	private int maxFuel;
		
	/**
	 * Default constructor (empty bus)
	 */
	public Bus() {
	  this.colour=COLOUR_UNSPECIFIED;
	}
	/**
	 * Constructor from database results set.
	 * 
	 * @param rs
	 */
	public Bus(ResultSet rs) {
		super(rs);
		try {
			this.driverName=rs.getString(rs.findColumn(COL_DRIVERNAME));
			this.maxPassengers=rs.getInt(rs.findColumn(COL_MAXPASSENGERS));
			this.maxFuel = rs.getInt(rs.findColumn(COL_MAXFUEL));
			this.colour = rs.getInt(rs.findColumn(COL_COLOUR));
			if(this.colour < 0 || this.colour>=COLOUR_UNSPECIFIED)
				this.colour= COLOUR_UNSPECIFIED;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A getter for the Driver Name
	 * 
	 * @return driverName
	 */
	public String getDriverName() {
		return this.driverName;
	}
	/**
	 * Getter for type
	 * 
	 * @return typeOfBus
	 */
	
	/**
	 * @param driverName naming the new driver name
	 */
	public void setDriverName(String DriverName) {
		this.driverName = DriverName;
	}

	/**
	 * Getter for the bus color
	 * 
	 * @return color
	 */
	public int getColour() {
		return this.colour;
	}

	/**
	 * Getter for color as string
	 * 
	 * @return color as string
	 */
	public String getColourAsString() {
		switch (this.colour) {
		case COLOUR_YELLOW : return COLOUR_VALUES[0];
		case COLOUR_MAROON : return COLOUR_VALUES[1];
		case COLOUR_GREY : return COLOUR_VALUES[2];
		case COLOUR_GOLD : return COLOUR_VALUES[3];
		case COLOUR_SILVER: return COLOUR_VALUES[4];
		default:return COLOUR_VALUES[5];
		}
	}
	
	/**
	 * Setter for Color
	 * 
	 * If the new color is out of color interval, it sets to unknown
	 * 
	 * @param colour  new color
	 */

	public int getMaximumPassengers() {
		return this.maxPassengers;
	}
	public void setMaximumPassengers(int MaxPassengers) {
		this.maxPassengers = MaxPassengers;
	}

	public int getMaxFuel() {
		return this.maxFuel;
	}
	public void setMaxFuel(int MaxFuel) {
		this.maxFuel = MaxFuel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + colour;
		result = prime * result + ((driverName == null) ? 0 : driverName.hashCode());
		result = prime * result + maxFuel;
		result = prime * result + maxPassengers;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bus other = (Bus) obj;
		if (colour != other.colour)
			return false;
		if (driverName == null) {
			if (other.driverName != null)
				return false;
		} else if (!driverName.equals(other.driverName))
			return false;
		if (maxFuel != other.maxFuel)
			return false;
		if (maxPassengers != other.maxPassengers)
			return false;
		return false;
	}
	@Override
	public String toString() {
		return new StringBuffer("Bus")
				.append("DriverName:").append(getDriverName())
				.append("BusColor:").append(getColourAsString())
				.append("MaxPassengers:").append(getMaximumPassengers())
				.append("MaxFuel:").append(getMaxFuel())
				.toString();
	}
		
public void setColour(int colour) {
			this.colour = colour<0 || colour>COLOUR_UNSPECIFIED ? COLOUR_UNSPECIFIED : colour;
		}
		
	}
	


