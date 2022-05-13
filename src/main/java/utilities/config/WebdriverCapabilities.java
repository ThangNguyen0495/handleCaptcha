package utilities.config;

public class WebdriverCapabilities {
    public static String CONFIG_NO_HEADLESS = "--disable-gpu --no-sandbox --disable-dev-shm-usage --allow-insecure-localhost";
    public static String CONFIG_HEADLESS = "--headless " + CONFIG_NO_HEADLESS;
}
