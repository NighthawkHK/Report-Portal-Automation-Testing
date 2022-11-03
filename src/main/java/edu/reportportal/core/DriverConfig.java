package edu.reportportal.core;

import edu.reportportal.utils.PropertyReader;

public final class DriverConfig {

    private DriverConfig() {}

    public static final String HOST = PropertyReader.getProperty("host");
    public static final Browser BROWSER = Browser.valueOf(PropertyReader.getProperty("browserType"));
    public static final long WAIT_IMPLICITLY = Long.parseLong(PropertyReader.getProperty("implicitlyWait"));
}
