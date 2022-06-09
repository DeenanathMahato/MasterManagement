package com.management.system.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.boolex.OnMarkerEvaluator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.filter.EvaluatorFilter;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.FilterReply;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.composite.ContextJsonProvider;
import net.logstash.logback.composite.GlobalCustomFieldsJsonProvider;
import net.logstash.logback.composite.loggingevent.*;
import net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder;
import net.logstash.logback.encoder.LogstashEncoder;
import net.logstash.logback.stacktrace.ShortenedThrowableConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Utility methods to add appenders to a {@link ch.qos.logback.classic.LoggerContext}.
 */
public final class LoggingUtils {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(LoggingUtils.class);

    /** The Constant CONSOLE_APPENDER_NAME. */
    private static final String CONSOLE_APPENDER_NAME = "CONSOLE";
    
    /** The Constant LOGSTASH_APPENDER_NAME. */
    private static final String LOGSTASH_APPENDER_NAME = "LOGSTASH";
    
    /** The Constant ASYNC_LOGSTASH_APPENDER_NAME. */
    private static final String ASYNC_LOGSTASH_APPENDER_NAME = "ASYNC_LOGSTASH";

    /**
     * Instantiates a new logging utils.
     */
    private LoggingUtils () {
    }

    /**
     * <p>addJsonConsoleAppender.</p>
     *
     * @param context a {@link ch.qos.logback.classic.LoggerContext} object.
     * @param customFields a {@link java.lang.String} object.
     */
    public static void addJsonConsoleAppender(LoggerContext context, String customFields) {
        log.info("Initializing Console loggingProperties");

        // More documentation is available at: https://github.com/logstash/logstash-logback-encoder
        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setContext(context);
        consoleAppender.setEncoder(compositeJsonEncoder(context, customFields));
        consoleAppender.setName(CONSOLE_APPENDER_NAME);
        consoleAppender.start();

        context.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME).detachAppender(CONSOLE_APPENDER_NAME);
        context.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME).addAppender(consoleAppender);
    }

   
    /**
     * Adds the logstash tcp socket appender.
     *
     * @param context the context
     * @param customFields the custom fields
     * @param logstashProperties the logstash properties
     */
    public static void addLogstashTcpSocketAppender(LoggerContext context, String customFields,
                                                    ManagementProperties.Logging.Logstash logstashProperties) {
        log.info("Initializing Logstash loggingProperties");

        // More documentation is available at: https://github.com/logstash/logstash-logback-encoder
        LogstashTcpSocketAppender logstashAppender = new LogstashTcpSocketAppender();
        logstashAppender.addDestinations(new InetSocketAddress(logstashProperties.getHost(), logstashProperties.getPort()));
        logstashAppender.setContext(context);
        logstashAppender.setEncoder(logstashEncoder(customFields));
        logstashAppender.setName(ASYNC_LOGSTASH_APPENDER_NAME);
        logstashAppender.setQueueSize(logstashProperties.getQueueSize());
        logstashAppender.start();

        context.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME).addAppender(logstashAppender);
    }

    
    /**
     * Adds the context listener.
     *
     * @param context the context
     * @param customFields the custom fields
     * @param properties the properties
     */
    public static void addContextListener(LoggerContext context, String customFields, ManagementProperties.Logging properties) {
        LogbackLoggerContextListener loggerContextListener = new LogbackLoggerContextListener(properties, customFields);
        loggerContextListener.setContext(context);
        context.addListener(loggerContextListener);
    }

    /**
     * Configure a log filter to remove "metrics" logs from all appenders except the "LOGSTASH" appender.
     *
     * @param context the logger context
     * @param useJsonFormat whether to use JSON format
     */
    public static void setMetricsMarkerLogbackFilter(LoggerContext context, boolean useJsonFormat) {
        log.info("Filtering metrics logs from all appenders except the {} appender", LOGSTASH_APPENDER_NAME);
        OnMarkerEvaluator onMarkerMetricsEvaluator = new OnMarkerEvaluator();
        onMarkerMetricsEvaluator.setContext(context);
        onMarkerMetricsEvaluator.addMarker("metrics");
        onMarkerMetricsEvaluator.start();
        EvaluatorFilter<ILoggingEvent> metricsFilter = new EvaluatorFilter<>();
        metricsFilter.setContext(context);
        metricsFilter.setEvaluator(onMarkerMetricsEvaluator);
        metricsFilter.setOnMatch(FilterReply.DENY);
        metricsFilter.start();

        context.getLoggerList().forEach(logger ->
            logger.iteratorForAppenders().forEachRemaining(appender -> {
                if (!appender.getName().equals(ASYNC_LOGSTASH_APPENDER_NAME)
                        && !(appender.getName().equals(CONSOLE_APPENDER_NAME) && useJsonFormat)) {
                    log.debug("Filter metrics logs from the {} appender", appender.getName());
                    appender.setContext(context);
                    appender.addFilter(metricsFilter);
                    appender.start();
                }
            })
        );
    }

    /**
     * Composite json encoder.
     *
     * @param context the context
     * @param customFields the custom fields
     * @return the logging event composite json encoder
     */
    private static LoggingEventCompositeJsonEncoder compositeJsonEncoder(LoggerContext context, String customFields) {
        final LoggingEventCompositeJsonEncoder compositeJsonEncoder = new LoggingEventCompositeJsonEncoder();
        compositeJsonEncoder.setContext(context);
        compositeJsonEncoder.setProviders(jsonProviders(context, customFields));
        compositeJsonEncoder.start();
        return compositeJsonEncoder;
    }

    /**
     * Logstash encoder.
     *
     * @param customFields the custom fields
     * @return the logstash encoder
     */
    private static LogstashEncoder logstashEncoder(String customFields) {
        final LogstashEncoder logstashEncoder = new LogstashEncoder();
        logstashEncoder.setThrowableConverter(throwableConverter());
        logstashEncoder.setCustomFields(customFields);
        return logstashEncoder;
    }

    /**
     * Json providers.
     *
     * @param context the context
     * @param customFields the custom fields
     * @return the logging event json providers
     */
    private static LoggingEventJsonProviders jsonProviders(LoggerContext context, String customFields) {
        final LoggingEventJsonProviders jsonProviders = new LoggingEventJsonProviders();
        jsonProviders.addArguments(new ArgumentsJsonProvider());
        jsonProviders.addContext(new ContextJsonProvider<>());
        jsonProviders.addGlobalCustomFields(customFieldsJsonProvider(customFields));
        jsonProviders.addLogLevel(new LogLevelJsonProvider());
        jsonProviders.addLoggerName(loggerNameJsonProvider());
        jsonProviders.addMdc(new MdcJsonProvider());
        jsonProviders.addMessage(new MessageJsonProvider());
        jsonProviders.addPattern(new LoggingEventPatternJsonProvider());
        jsonProviders.addStackTrace(stackTraceJsonProvider());
        jsonProviders.addThreadName(new ThreadNameJsonProvider());
        jsonProviders.addTimestamp(timestampJsonProvider());
        jsonProviders.setContext(context);
        return jsonProviders;
    }

    /**
     * Custom fields json provider.
     *
     * @param customFields the custom fields
     * @return the global custom fields json provider
     */
    private static GlobalCustomFieldsJsonProvider<ILoggingEvent> customFieldsJsonProvider(String customFields) {
        final GlobalCustomFieldsJsonProvider<ILoggingEvent> customFieldsJsonProvider = new GlobalCustomFieldsJsonProvider<>();
        customFieldsJsonProvider.setCustomFields(customFields);
        return customFieldsJsonProvider;
    }

    /**
     * Logger name json provider.
     *
     * @return the logger name json provider
     */
    private static LoggerNameJsonProvider loggerNameJsonProvider() {
        final LoggerNameJsonProvider loggerNameJsonProvider = new LoggerNameJsonProvider();
        loggerNameJsonProvider.setShortenedLoggerNameLength(20);
        return loggerNameJsonProvider;
    }

    /**
     * Stack trace json provider.
     *
     * @return the stack trace json provider
     */
    private static StackTraceJsonProvider stackTraceJsonProvider() {
        StackTraceJsonProvider stackTraceJsonProvider = new StackTraceJsonProvider();
        stackTraceJsonProvider.setThrowableConverter(throwableConverter());
        return stackTraceJsonProvider;
    }

    /**
     * Throwable converter.
     *
     * @return the shortened throwable converter
     */
    private static ShortenedThrowableConverter throwableConverter() {
        final ShortenedThrowableConverter throwableConverter = new ShortenedThrowableConverter();
        throwableConverter.setRootCauseFirst(true);
        return throwableConverter;
    }

    /**
     * Timestamp json provider.
     *
     * @return the logging event formatted timestamp json provider
     */
    private static LoggingEventFormattedTimestampJsonProvider timestampJsonProvider() {
        final LoggingEventFormattedTimestampJsonProvider timestampJsonProvider = new LoggingEventFormattedTimestampJsonProvider();
        timestampJsonProvider.setTimeZone("UTC");
        timestampJsonProvider.setFieldName("timestamp");
        return timestampJsonProvider;
    }

    /**
     * Logback configuration is achieved by configuration file and API.
     * When configuration file change is detected, the configuration is reset.
     * This listener ensures that the programmatic configuration is also re-applied after reset.
     *
     * @see LogbackLoggerContextEvent
     */
    private static class LogbackLoggerContextListener extends ContextAwareBase implements LoggerContextListener {
        
        /** The logging properties. */
        private final ManagementProperties.Logging loggingProperties;
        
        /** The custom fields. */
        private final String customFields;

        /**
         * Instantiates a new logback logger context listener.
         *
         * @param loggingProperties the logging properties
         * @param customFields the custom fields
         */
        private LogbackLoggerContextListener(ManagementProperties.Logging loggingProperties, String customFields) {
            this.loggingProperties = loggingProperties;
            this.customFields = customFields;
        }

        /**
         * Checks if is reset resistant.
         *
         * @return true, if is reset resistant
         */
        @Override
        public boolean isResetResistant() {
            return true;
        }

        /**
         * On start.
         *
         * @param context the context
         */
        @Override
        public void onStart(LoggerContext context) {
            if (this.loggingProperties.isUseJsonFormat()) {
                addJsonConsoleAppender(context, customFields);
            }
            if (this.loggingProperties.getLogstash().isEnabled()) {
                addLogstashTcpSocketAppender(context, customFields, loggingProperties.getLogstash());
            }
        }

        /**
         * On reset.
         *
         * @param context the context
         */
        @Override
        public void onReset(LoggerContext context) {
            if (this.loggingProperties.isUseJsonFormat()) {
                addJsonConsoleAppender(context, customFields);
            }
            if (this.loggingProperties.getLogstash().isEnabled()) {
                addLogstashTcpSocketAppender(context, customFields, loggingProperties.getLogstash());
            }
        }

        /**
         * On stop.
         *
         * @param context the context
         */
        @Override
        public void onStop(LoggerContext context) {
            // Nothing to do.
        }

        /**
         * On level change.
         *
         * @param logger the logger
         * @param level the level
         */
        @Override
        public void onLevelChange(ch.qos.logback.classic.Logger logger, Level level) {
            // Nothing to do.
        }
    }
}
