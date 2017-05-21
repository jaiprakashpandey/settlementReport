package report.error;


import report.common.ReportProcessorErrorMsg;

/**
 * Created by Jai on 17-05-2017.
 */
public class ReportProcessorException extends Exception {
    public ReportProcessorErrorMsg getError() {
        return error;
    }

    private final ReportProcessorErrorMsg error;

    public ReportProcessorException(ReportProcessorErrorMsg error, String otherMsg) {
        super(error.getMessage() + otherMsg);
        this.error = error;
    }
}
