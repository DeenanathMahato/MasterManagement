package com.management.system.config;

public interface ManageDefaults {

	/**
	 * The Interface ClientApp.
	 */
	interface ClientApp {

		/** The name. */
		public static final String NAME = "eProcurementApp";
	}

	/**
	 * The Interface Logging.
	 */
	interface Logging {

		/** The use json format. */
		boolean useJsonFormat = false;

		/**
		 * The Interface Logstash.
		 */
		interface Logstash {

			/** The enabled. */
			boolean enabled = false;

			/** The host. */
			String host = "localhost";

			/** The port. */
			int port = 5000;

			/** The queue size. */
			int queueSize = 512;
		}
	}

	/**
	 * The Interface Mail.
	 */
	interface Mail {

		/** The enabled. */
		boolean enabled = false;

		/** The from. */
		String from = "";

		/** The base url. */
		String baseUrl = "";
		
		String supplierEmailVerificationUrl = "";
		
		String directSupplierEmailVerificationUrl = "";
		
		/**
		 * The Interface Jwt.
		 */
		interface Jwt {

			/** The secret. */
			String secret = null;

			/** The base 64 secret. */
			String base64Secret = null;

			/** The token validity in seconds. */
			long tokenValidityInSeconds = 1800; // 30 minutes

			/** The token validity in seconds for remember me. */
			long tokenValidityInSecondsForRememberMe = 2592000; // 30 days
		}
	}
	
	interface Cache {
		interface Ehcache {

            int timeToLiveSeconds = 3600; // 1 hour
            long maxEntries = 100;
        }
	}
}
