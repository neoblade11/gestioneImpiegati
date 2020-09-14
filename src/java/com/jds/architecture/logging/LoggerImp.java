/*
 * Created on Jan 28, 2005
 */
package com.jds.architecture.logging;


/**
 * @author Roan W G Nicolas <roan.w.g.nicolas@accenture.com>
 *
 */
public interface LoggerImp{ 

	public void init(String name);
    
    public void info(String msg);
    public void info(String msg, Throwable t);
    
	public void warn(String msg);
    public void warn(String msg, Throwable t);
	
	public void debug(String msg);
    public void debug(String msg, Throwable t);	
	
	public void error(String msg);
    public void error(String msg, Throwable t);	
	
	public void fatal(String msg);
    public void fatal(String msg, Throwable t);	
	
}
