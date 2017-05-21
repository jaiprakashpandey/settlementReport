package report.common;

/**
 * Created by Jai on 17-05-2017.
 */
public enum ReportProcessorErrorMsg {
    ERROR_INPUT_FILE_NOT_FOUND(1, "No instructions found at path !!"),
    ERROR_OUTPUT_UNABLE_TO_GENERATE_REPORT(2, "Unable to generate Report please contact administrator!!");

    private int errorCode;
    private String message;

    ReportProcessorErrorMsg(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
