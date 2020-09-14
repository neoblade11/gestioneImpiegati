/*
 * Created on Feb 1, 2005
 */
package com.jds.architecture.properties;

import java.util.Properties;
import java.util.Hashtable;

/**
 * @author Roan W G Nicolas <roan.w.g.nicolas@accenture.com>
 *
 */
public interface PropertiesMonitorImp {

	public void init();

	public void loadAllStaticProperties(Hashtable<String,Properties> staticProperties, String globalHRSPropertiesFolder);

	public void initializeDynamicProperties(Properties dynamicProperties, String globalPropertiesFolder,
			String dynamicPropertiesFileName);

	public Properties getPropertiesFile(Hashtable<?, ?> staticProperties, String componentName);

	public String getProperty(Hashtable<?, ?> staticProperties, String componentName, String propertyName);

	public String setDynamicProperty(Properties dynamicProperties, String globalPropertiesFolder,
			String dynamicPropertiesFileName, String propertyName, String propertyValue);

	public String getDynamicProperty(Properties dynamicProperties, String propertyName);

}
