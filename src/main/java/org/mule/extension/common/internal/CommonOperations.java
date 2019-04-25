package org.mule.extension.common.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.util.Date;
import java.beans.XMLEncoder;
import java.util.*;
import java.io.ByteArrayOutputStream;

import org.mule.runtime.extension.api.annotation.Expression;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.NullSafe;

import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.Optional;

import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Text;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;


enum logType
{
	JSON, 
	PIPED
	
}

enum logLevel
{
	INFO,
	DEBUG,
	ERROR,		
	TRACE,
	WARN
}

enum TracePoints
{		
	FLOW_STARTED,
	BEFORE_API_OR_BACKEND,
	AFTER_API_OR_BACKEND,
	BEFORE_TRANSFORM,
	AFTER_TRANSFORM,
	FLOW_END,
	EXCEPTION

}



/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class CommonOperations {

	  

	private static final Logger lLogger = LoggerFactory.getLogger("CommonOperations.Class");	
  @MediaType(value = ANY, strict = false)
  @DisplayName("BLog it")  
  public void logit(
		  @Placement(tab = "General") @DisplayName("Trace Points") @Example("FLOW_START") @Optional(defaultValue = "FLOW_STARTED") TracePoints tracePoints,
		  @Placement(tab = "General") @DisplayName("Timestamp") @Optional(defaultValue = "#[now()]") String timestamp,
		  @Placement(tab = "General") @DisplayName("Request Id") @Optional(defaultValue = "")  String requestid,
		  @Placement(tab= "General") @DisplayName("App Name")  @Optional(defaultValue = "#[app.name]") String appName,
		  @Placement(tab = "General") @DisplayName("App Version") @Optional(defaultValue = "1.0.0") String appVersion,
		  @Placement(tab = "General") @DisplayName("Env.") @Optional(defaultValue = "${env}") String env,
		  @Placement(tab = "General" ) @DisplayName("Payload") @Text @Optional(defaultValue = "") String payload,
		  
		  @Placement(tab = "Log", order = 2) @DisplayName("Level") @Optional(defaultValue = "INFO") logLevel level,
		  @Placement(tab = "Log") @DisplayName("Category") @Optional(defaultValue = "") String logcategory,				  
		  @Placement(tab = "Log") @Optional @DisplayName("Extended Values") @NullSafe HashMap<String, String> extended,
		  
		  @Placement(tab = "Exception", order = 3) @DisplayName("Exception") @Optional(defaultValue = "") String statusCode,
		  @Placement(tab = "Exception") @DisplayName("Type") @Optional(defaultValue = "") String exceptionType,
		  @Placement(tab = "Exception") @DisplayName("Detail") @Optional(defaultValue = "") String expDetail
		  
		  )
  {	  	 	   
	   	   
	  StringBuffer tempLog = new StringBuffer();
	   tempLog.append(timestamp +  "\t"); 
	   tempLog.append(tracePoints.toString() +  "\t"); 
	   tempLog.append(requestid +  "\t"); 
	   tempLog.append(appName +  "\t");  
	   tempLog.append(appVersion +  "\t"); 
	   tempLog.append(env  +  "\t");  		   		   
	   tempLog.append(payload +  "\t");
	   tempLog.append(extended.toString()  +  "\t");
	   tempLog.append(statusCode +  "\t");
	   tempLog.append(exceptionType +  "\t");
	   tempLog.append(expDetail);	   
	   
		switch (level)
		{
			case INFO:			
				lLogger.info(tempLog.toString());
				break;
			case DEBUG:			
				lLogger.debug(tempLog.toString());
				break;
			case WARN:			
				lLogger.warn(tempLog.toString());
				break;
			case ERROR:			
				lLogger.error(tempLog.toString());
				break;
			case TRACE:			
				lLogger.trace(tempLog.toString());
				break;			
		}    
  }

}

