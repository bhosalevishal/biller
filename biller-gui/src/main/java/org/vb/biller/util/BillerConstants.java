package org.vb.biller.util;




/**
 * Customer application launcher class was changed to RunApplication Class.
 * <p>
 * This application is a stand-alone Java client. Class CustomerServiceDelegate 
 * demonstrates how data could be read and sent back to a server. 
 * Because of this "dummy" implementation, the data saved via client is not persisted
 * (GUI doesn't remember your changes).
 * <p>
 * This demo has one frame including several views, which all have different
 * controllers and models. Controllers have hierachical
 * structure, but communication is implemented with event "broadcasting"
 * functionality of the framework (YController sendApplicationEvent method). 
 * 
 * @author Tomi Tuomainen
 */
public class BillerConstants {

	// application event names
	public static final String CUSTOMER_CHANGED = "CustomerChanged";
	public static final String CUSTOMER_ADDED = "CustomerAdded";
	public static final String CUSTOMER_DELETED = "CustomerDeleted";
	public static final String JOBS_CHANGED = "JobsChanged";

}
