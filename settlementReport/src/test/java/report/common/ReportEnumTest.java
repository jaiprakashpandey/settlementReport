package report.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by K316940 on 21-05-2017.
 */
public class ReportEnumTest {
    @Test
    public void value() throws Exception {
        String EXPECTED_UAE_CURRENCY = "AED";
        String EXPECTED_SAUD_CURRENCY = "SAR";
        String EXPECTED_USD_CURRENCY = "USD";
        String EXPECTED_TRADE_TYPE_BUY = "B";
        String EXPECTED_TRADE_TYPE_SELL = "S";

        Assert.assertEquals(EXPECTED_UAE_CURRENCY, ReportEnum.UNITED_ARAB_EMIRATES_DINAR.value());
        Assert.assertEquals(EXPECTED_SAUD_CURRENCY, ReportEnum.SAUDI_RIAL.value());
        Assert.assertEquals(EXPECTED_USD_CURRENCY, ReportEnum.USA_DOLLAR.value());
        Assert.assertEquals(EXPECTED_TRADE_TYPE_BUY, ReportEnum.TRADE_TYPE_BUY.value());
        Assert.assertEquals(EXPECTED_TRADE_TYPE_SELL, ReportEnum.TRADE_TYPE_SELL.value());
    }
}