package report.error;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import report.common.ReportProcessorErrorMsg;

/**
 * Created by K316940 on 21-05-2017.
 */
public class ReportProcessorExceptionTest {
    ReportProcessorException exception;

    @Before
    public void setUp() throws Exception {
        exception = new ReportProcessorException(ReportProcessorErrorMsg.ERROR_INPUT_FILE_NOT_FOUND, "filename");
    }

    @After
    public void tearDown() throws Exception {
        exception = null;
    }

    @Test
    public void getError() throws Exception {
        final String EXPECTD_ERROR_MESSAGE = "No instructions found at path !!";
        final Integer EXPECTED_ERROR_CODE = 1;
        String actualErrorMsg = exception.getError().getMessage();
        Integer actualErrorCode = exception.getError().getErrorCode();

        Assert.assertNotNull(actualErrorMsg);
        Assert.assertNotNull(actualErrorCode);

        Assert.assertEquals(EXPECTED_ERROR_CODE, actualErrorCode);
        Assert.assertEquals(EXPECTD_ERROR_MESSAGE, actualErrorMsg);
    }

}