package buses.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import buses.Domain.Bus;

public class BusesDao extends AbstractDao<Bus> {

	private static final String SQL_FIND_BY_ID = "SELECT * FROM bus c WHERE id = ?"; //$NON-NLS-1$

	private static final String SQL_FIND_BY_NAME = "SELECT * FROM bus c WHERE name = ?"; //$NON-NLS-1$

	private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM bus c ORDER BY id"; //$NON-NLS-1$

	private static final String SQL_INSERT = "INSERT INTO bus (" //$NON-NLS-1$	
			+ Bus.COL_DRIVERNAME + "," //$NON-NLS-1$
			+ Bus.COL_COLOUR + "," //$NON-NLS-1$
			+ Bus.COL_MAXPASSENGERS + "," //$NON-NLS-1$
			+ Bus.COL_MAXFUEL + ") VALUES (?, ?, ?, ?)"; //$NON-NLS-1$

	private static final String SQL_UPDATE = "UPDATE bus SET " //$NON-NLS-1$
			+Bus.COL_DRIVERNAME + "= ?, " //$NON-NLS-1$
			+ Bus.COL_COLOUR + "=?," //$NON-NLS-1$
			+ Bus.COL_MAXPASSENGERS + " = ?, " //$NON-NLS-1$
			+  Bus.COL_MAXFUEL + " = ? WHERE id = ?"; //$NON-NLS-1$

	private static final String SQL_CREATE_TABLE = "CREATE TABLE if not exists Bus ( id integer identity primary key, "+  //$NON-NLS-1$
			Bus.COL_DRIVERNAME    + " varchar(40)," +  //$NON-NLS-1$
			 Bus.COL_COLOUR    + " varchar(30), "+  //$NON-NLS-1$
			 Bus.COL_MAXPASSENGERS    + " int,"+ //$NON-NLS-1$
			 Bus.COL_MAXFUEL + " int);"; //$NON-NLS-1$
	
	private static final String SQL_DELETE = "DELETE FROM Bus WHERE id = ?"; //$NON-NLS-1$

	private static final String SQL_DELETE_ALL = "DELETE FROM Computer"; //$NON-NLS-1$

	public BusesDao(String url, String user, String password) throws SQLException {
		super(url, user, password);
		checkBusTable();		
	}
	@Override
	protected Bus createDomain(ResultSet rs) {
		return new Bus(rs);
	}

	public Bus findById(Integer id) {
		return find(SQL_FIND_BY_ID, id);
	}

	public Bus findByName(String name) {
		return find(SQL_FIND_BY_NAME, name);
	}

	public List<Bus> listOrderById() {
		return list(SQL_LIST_ORDER_BY_ID);
	}

	@Override
	protected Object[] getDomainValues(Bus domain) {
		return new Object[] { 
				domain.getDriverName(),
			//	domain.getType(),
				 domain.getColour(),
	             Integer.valueOf(domain.getMaximumPassengers()),
				Integer.valueOf(domain.getMaxFuel())};
	}

	@Override
	protected Object[] getDomainValuesForUpdate(Bus domain) {
		return new Object[] {
				domain.getDriverName(),
				 domain.getColour(),
	             Integer.valueOf(domain.getMaximumPassengers()),
				Integer.valueOf(domain.getMaxFuel()),
				domain.getId()
				};
		
		};
	@Override
	protected String getInsertSql() {
		return SQL_INSERT;
	}

	@Override
	protected String getDeleteSql() {
		return SQL_DELETE;
	}

	@Override
	protected String getDeleteAllSql() {
		return SQL_DELETE_ALL;
	}

	@Override
	protected String getUpdateSql() {
		return SQL_UPDATE;
	}
	
	public static BusesDao createBusDao() throws SQLException {
		BusesDao result = new BusesDao(DB_URI, DB_USER, DB_PASSWORD);
		return result;
	}

	private void checkBusTable() {
		try (Statement stmt = this.connection.createStatement()) {
			stmt.execute(SQL_CREATE_TABLE);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}


