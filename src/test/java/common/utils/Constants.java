package common.utils;

public class Constants {

    /**
     * Constants for Endpoint
     */
    public static String OWNER_ENDPOINT = "/owners";
    public static String PET_ENDPOINT = "/pets";
    public static String VET_ENDPOINT = "/vets";
    public static String VISIT_ENDPOINT = "/visits";

    /**
     * Miscellaneous Constants
     */
    public static String ON_START_MESSAGE = "message.test.listener.onstart";
    public static String ON_FINISH_MESSAGE = "message.test.listener.onfinish";
    public static String ON_TEST_START_MESSAGE = "message.test.listener.onteststart";
    public static String ON_TEST_SUCCESS_MESSAGE = "message.test.listener.ontestsuccess";
    public static String ON_TEST_FAILURE_MESSAGE = "message.test.listener.ontestfailure";
    public static String ON_TEST_SKIPPED_MESSAGE = "message.test.listener.ontestskipped";
    public static String ON_SUCCESS_RATIO_MESSAGE = "message.test.listener.successratio";

    /**
     * Constants for file paths
     */
    public static String OWNER_API_DATA = "owner-api.json";
    public static String OWNER_UI_DATA = "owner-ui.json";
    public static String PET_API_DATA = "pet-api.json";
    public static String PET_UI_DATA = "pet-ui.json";


    private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    public static String MESSAGES_PROPERTIES_PATH = WORKING_DIRECTORY + "/src/test/resources/messages.properties";
    public static String EXTENT_REPORT_PATH = WORKING_DIRECTORY + "/extentreport/ExtentReportResults.html";

}
