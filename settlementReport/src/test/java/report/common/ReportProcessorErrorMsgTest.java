package report.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by K316940 on 21-05-2017.
 */
public class ReportProcessorErrorMsgTest {
    @Test
    public void getErrorCode() throws Exception {
        int EXPECTED_ERROR_CODE1 = 1;
        int EXPECTED_ERROR_CODE2 = 2;

        Assert.assertEquals(EXPECTED_ERROR_CODE1, ReportProcessorErrorMsg.ERROR_INPUT_FILE_NOT_FOUND.getErrorCode());
        Assert.assertEquals(EXPECTED_ERROR_CODE2, ReportProcessorErrorMsg.ERROR_OUTPUT_UNABLE_TO_GENERATE_REPORT.getErrorCode());
    }

    @Test
    public void getMessage() throws Exception {
        String EXPECTED_ERROR_MSG1 = "No instructions found at path !!";
        String EXPECTED_ERROR_MSG2 = "Unable to generate Report please contact administrator!!";

        Assert.assertEquals(EXPECTED_ERROR_MSG1, ReportProcessorErrorMsg.ERROR_INPUT_FILE_NOT_FOUND.getMessage());
        Assert.assertEquals(EXPECTED_ERROR_MSG2, ReportProcessorErrorMsg.ERROR_OUTPUT_UNABLE_TO_GENERATE_REPORT.getMessage());
    }
}