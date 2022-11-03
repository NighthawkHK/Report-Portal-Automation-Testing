package edu.reportportal.core;

import org.openqa.selenium.WebDriver;

interface DriverFactory {
    WebDriver createDriver(Browser browser);
}
