package dumpBackground2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpBackground2.gui.MainFrame;

/**
 * Hello world!
 *
 */
public class App {
	public static String Log4JPath = null;
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		initApp();
		logger.info("APP_STARTED");
		logger.error("APP_STARTED");
		logger.warn("APP_STARTED");
		logger.debug("APP_STARTED");

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new MainFrame().setVisible(true);
			}
		});

	}

	public static String getLog4JPath() {
		return Log4JPath;
	}

	public static void setLog4JPath(String log4jPath) {
		Log4JPath = log4jPath;
	}

	public static Properties getApplicationProperties() {
		App application = new App();

		InputStream inputStream = null;
		Properties applicationProp = null;
		try {
			inputStream = new FileInputStream(new File("./resources/application.properties"));
			applicationProp = new Properties();
			applicationProp.load(inputStream);
			inputStream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return applicationProp;
	}

	public static void initApp() {
		Properties applicationProperties = getApplicationProperties();
		setLog4JPath(applicationProperties.getProperty("LOG4J_PATH"));
		loadLog4j();
	}

	private static void loadLog4j() {
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		context.setConfigLocation(new File(getLog4JPath()).toURI());
	}

	/***************************************************************************/
	/* Private Methods */
	/***************************************************************************/

}
