package com.management.system.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.cors.CorsConfiguration;

/**
 * The Class EProcProperties.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "management", ignoreUnknownFields = false)
@PropertySources({ @PropertySource(value = "classpath:git.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "classpath:META-INF/build-info.properties", ignoreResourceNotFound = true) })
public class ManagementProperties implements InitializingBean {

	/** The cors. */
	private final CorsConfiguration cors = new CorsConfiguration();

	/** The client app. */
	private final ClientApp clientApp = new ClientApp();

	/** The logging. */
	private final Logging logging = new Logging();

	/** The mail. */
	private final Mail mail = new Mail();

	private final Cache cache = new Cache();

	/**
	 * Gets the cors.
	 *
	 * @return the cors
	 */
	public CorsConfiguration getCors() {
		return cors;
	}

	/**
	 * Gets the client app.
	 *
	 * @return the client app
	 */
	public ClientApp getClientApp() {
		return clientApp;
	}

	/**
	 * Gets the logging.
	 *
	 * @return the logging
	 */
	public Logging getLogging() {
		return logging;
	}

	public Mail getMail() {
		return mail;
	}

	public Cache getCache() {
		return cache;
	}

	/**
	 * After properties set.
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
	}

	/**
	 * The Class ClientApp.
	 */
	public static class ClientApp {

		/** The name. */
		private String name = ManageDefaults.ClientApp.NAME;

		public String getName() {
			return name;
		}

		/**
		 * Sets the name.
		 *
		 * @param name the new name
		 */
		public void setName(String name) {
			this.name = name;
		}

	}

	/**
	 * The Class Logging.
	 */
	public static class Logging {

		/** The use json format. */
		private boolean useJsonFormat = ManageDefaults.Logging.useJsonFormat;

		/** The logstash. */
		private final Logstash logstash = new Logstash();

		/**
		 * Checks if is use json format.
		 *
		 * @return true, if is use json format
		 */
		public boolean isUseJsonFormat() {
			return useJsonFormat;
		}

		/**
		 * Sets the use json format.
		 *
		 * @param useJsonFormat the new use json format
		 */
		public void setUseJsonFormat(boolean useJsonFormat) {
			this.useJsonFormat = useJsonFormat;
		}

		/**
		 * Gets the logstash.
		 *
		 * @return the logstash
		 */
		public Logstash getLogstash() {
			return logstash;
		}

		/**
		 * The Class Logstash.
		 */
		public static class Logstash {

			/** The enabled. */
			private boolean enabled = ManageDefaults.Logging.Logstash.enabled;

			/** The host. */
			private String host = ManageDefaults.Logging.Logstash.host;

			/** The port. */
			private int port = ManageDefaults.Logging.Logstash.port;

			/** The queue size. */
			private int queueSize = ManageDefaults.Logging.Logstash.queueSize;

			/**
			 * Checks if is enabled.
			 *
			 * @return true, if is enabled
			 */
			public boolean isEnabled() {
				return enabled;
			}

			/**
			 * Sets the enabled.
			 *
			 * @param enabled the new enabled
			 */
			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			/**
			 * Gets the host.
			 *
			 * @return the host
			 */
			public String getHost() {
				return host;
			}

			/**
			 * Sets the host.
			 *
			 * @param host the new host
			 */
			public void setHost(String host) {
				this.host = host;
			}

			/**
			 * Gets the port.
			 *
			 * @return the port
			 */
			public int getPort() {
				return port;
			}

			/**
			 * Sets the port.
			 *
			 * @param port the new port
			 */
			public void setPort(int port) {
				this.port = port;
			}

			/**
			 * Gets the queue size.
			 *
			 * @return the queue size
			 */
			public int getQueueSize() {
				return queueSize;
			}

			/**
			 * Sets the queue size.
			 *
			 * @param queueSize the new queue size
			 */
			public void setQueueSize(int queueSize) {
				this.queueSize = queueSize;
			}
		}
	}

	/**
	 * The Class Mail.
	 */
	public static class Mail {

		/** The enabled. */
		private boolean enabled = ManageDefaults.Mail.enabled;

		/** The from. */
		private String from = ManageDefaults.Mail.from;

		/** The base url. */
		private String baseUrl = ManageDefaults.Mail.baseUrl;

		private String supplierEmailVerificationUrl = ManageDefaults.Mail.supplierEmailVerificationUrl;

		private String directSupplierEmailVerificationUrl = ManageDefaults.Mail.directSupplierEmailVerificationUrl;

		private final Jwt jwt = new Jwt();

		/**
		 * Checks if is enabled.
		 *
		 * @return true, if is enabled
		 */
		public boolean isEnabled() {
			return enabled;
		}

		/**
		 * Sets the enabled.
		 *
		 * @param enabled the new enabled
		 */
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		/**
		 * Gets the from.
		 *
		 * @return the from
		 */
		public String getFrom() {
			return from;
		}

		/**
		 * Sets the from.
		 *
		 * @param from the new from
		 */
		public void setFrom(String from) {
			this.from = from;
		}

		/**
		 * Gets the base url.
		 *
		 * @return the base url
		 */
		public String getBaseUrl() {
			return baseUrl;
		}

		/**
		 * Sets the base url.
		 *
		 * @param baseUrl the new base url
		 */
		public void setBaseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
		}

		public String getSupplierEmailVerificationUrl() {
			return supplierEmailVerificationUrl;
		}

		public void setSupplierEmailVerificationUrl(String supplierEmailVerificationUrl) {
			this.supplierEmailVerificationUrl = supplierEmailVerificationUrl;
		}

		public String getDirectSupplierEmailVerificationUrl() {
			return directSupplierEmailVerificationUrl;
		}

		public void setDirectSupplierEmailVerificationUrl(String directSupplierEmailVerificationUrl) {
			this.directSupplierEmailVerificationUrl = directSupplierEmailVerificationUrl;
		}

		public Jwt getJwt() {
			return jwt;
		}

		public static class Jwt {

			private String secret = ManageDefaults.Mail.Jwt.secret;

			private String base64Secret = ManageDefaults.Mail.Jwt.base64Secret;

			private long tokenValidityInSeconds = ManageDefaults.Mail.Jwt.tokenValidityInSeconds;

			private long tokenValidityInSecondsForRememberMe = ManageDefaults.Mail.Jwt.tokenValidityInSecondsForRememberMe;

			public String getSecret() {
				return secret;
			}

			public void setSecret(String secret) {
				this.secret = secret;
			}

			public String getBase64Secret() {
				return base64Secret;
			}

			public void setBase64Secret(String base64Secret) {
				this.base64Secret = base64Secret;
			}

			public long getTokenValidityInSeconds() {
				return tokenValidityInSeconds;
			}

			public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
				this.tokenValidityInSeconds = tokenValidityInSeconds;
			}

			public long getTokenValidityInSecondsForRememberMe() {
				return tokenValidityInSecondsForRememberMe;
			}

			public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
				this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
			}
		}
	}

	public static class Cache {

		private final Ehcache ehcache = new Ehcache();

		public Ehcache getEhcache() {
			return ehcache;
		}

		public static class Ehcache {

			private int timeToLiveSeconds = ManageDefaults.Cache.Ehcache.timeToLiveSeconds;

			private long maxEntries = ManageDefaults.Cache.Ehcache.maxEntries;

			public int getTimeToLiveSeconds() {
				return timeToLiveSeconds;
			}

			public void setTimeToLiveSeconds(int timeToLiveSeconds) {
				this.timeToLiveSeconds = timeToLiveSeconds;
			}

			public long getMaxEntries() {
				return maxEntries;
			}

			public void setMaxEntries(long maxEntries) {
				this.maxEntries = maxEntries;
			}
		}
	}

}
