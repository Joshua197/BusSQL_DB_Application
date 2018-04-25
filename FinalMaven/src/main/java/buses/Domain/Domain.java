package buses.Domain;

/**
 * Minimal interface for a database domain 
 * 
 * It has only one field named id, which is the primary key of the database table
 * 
 * @author Joshua Wambua
 *
 */
public interface Domain {
	
	Integer getId();
	
	void setId(Integer id);

}
