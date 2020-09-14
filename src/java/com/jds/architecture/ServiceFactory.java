/*
 * Created on Feb 4, 2005
 */
package com.jds.architecture;

import java.util.Map;
import java.util.HashMap;

import com.jds.architecture.logging.*;
import com.jds.architecture.properties.*;

/**
 * @author Roan W G Nicolas <roan.w.g.nicolas@accenture.com>
 *
 */
public class ServiceFactory {
    
	private static ServiceFactory onlyInstance = new ServiceFactory();
	private static Map<String, Service> serviceMap;
	
	static {
	    initializeServices();
	}
	
	private static void initializeServices(){
		//call registerService	    
	    registerService(new LoggerService(), new Log4JLoggerImp());
	    registerService(new PropertiesMonitorService(), new JDSPropertiesMonitorImp());
	}
	
	private ServiceFactory(){
		serviceMap = new HashMap<String, Service>();
	}
	
	public static ServiceFactory getInstance() {
	    return onlyInstance;
	}		
	
	private static void registerService(Service service, Implementor plugin){
		
	    service.setImplementor(plugin);
	    serviceMap.put(service.getServiceName(), service);
		
	}
	
	public Service getService(Class<?> serviceClassName){
		
		return (Service)serviceMap.get(serviceClassName.getName());
	}
}
