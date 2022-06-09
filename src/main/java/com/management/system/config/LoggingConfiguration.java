package com.management.system.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.LoggerContext;

/*
 * Configures the console and Logstash log appenders from the app properties
 */
@Configuration
public class LoggingConfiguration {

    public LoggingConfiguration(
        @Value("${spring.application.name}") String appName,
        @Value("${server.port}") String serverPort,
        ManagementProperties managementProperties,
        ObjectProvider<BuildProperties> buildProperties,
        ObjectMapper mapper
    ) throws JsonProcessingException {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        Map<String, String> map = new HashMap<>();
        map.put("app_name", appName);
        map.put("app_port", serverPort);
        buildProperties.ifAvailable(it -> map.put("version", it.getVersion()));
        String customFields = mapper.writeValueAsString(map);

        ManagementProperties.Logging loggingProperties = managementProperties.getLogging();
        ManagementProperties.Logging.Logstash logstashProperties = loggingProperties.getLogstash();

        if (loggingProperties.isUseJsonFormat()) {
        	LoggingUtils.addJsonConsoleAppender(context, customFields);
        }
        if (logstashProperties.isEnabled()) {
        	LoggingUtils.addLogstashTcpSocketAppender(context, customFields, logstashProperties);
        }
        if (loggingProperties.isUseJsonFormat() || logstashProperties.isEnabled()) {
        	LoggingUtils.addContextListener(context, customFields, loggingProperties);
        }
    }
}
