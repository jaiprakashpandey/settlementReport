package report.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import report.domain.Trade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by K316940 on 21-05-2017.
 */
public class ReportUtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void toLocalDateTime() throws Exception {
        LocalDateTime EXPECTED_lOCAL_DATE = LocalDateTime.of(2017, 05, 22, 0, 0, 0);
        LocalDateTime actualDateFormat = ReportUtil.toLocalDateTime("22May2017");

        Assert.assertNotNull(actualDateFormat);
        Assert.assertEquals(EXPECTED_lOCAL_DATE, actualDateFormat);
    }

    @Test
    public void toInteger() throws Exception {
        Integer EXPECTED_DATA = 1500;
        Integer actualData = ReportUtil.toInteger("1500");

        Assert.assertNotNull(actualData);
        Assert.assertEquals(EXPECTED_DATA, actualData);
    }

    @Test
    public void getNextWorkingDayForUSDCountry() throws Exception {
        LocalDateTime aSettlementDate = LocalDateTime.of(2017, 05, 20, 0, 0, 0);
        LocalDateTime actualDateFormat = ReportUtil.getNextWorkingDay(aSettlementDate, createTrade(20, ReportEnum.USA_DOLLAR.value()));

        //20th may is saturday so next working day will be on monday 22nd may 2017
        final LocalDateTime EXPECTED_lOCAL_DATE = LocalDateTime.of(2017, 05, 22, 0, 0, 0);

        Assert.assertNotNull(actualDateFormat);
        Assert.assertEquals(EXPECTED_lOCAL_DATE, actualDateFormat);
    }

    @Test
    public void getNextWorkingDayForSaudiArabia() throws Exception {
        LocalDateTime aSettlementDate = LocalDateTime.of(2017, 05, 12, 0, 0, 0);
        LocalDateTime actualDateFormat = ReportUtil.getNextWorkingDay(aSettlementDate, createTrade(12, ReportEnum.SAUDI_RIAL.value()));

        //12th may is friday so next working day will be on Sunday 14th May 2017 as its Saudi Arabia
        final LocalDateTime EXPECTED_lOCAL_DATE = LocalDateTime.of(2017, 05, 14, 0, 0, 0);

        Assert.assertNotNull(actualDateFormat);
        Assert.assertEquals(EXPECTED_lOCAL_DATE, actualDateFormat);
    }

    @Test
    public void prepareReportHeader() throws Exception {
        final String EXPECTED_HEADER_STRING = "\n\nACTUAL SETTLEMENT DATE\t\tENTITY\t\tRANK \t\tUSD INCOMING\t\tUSD OUTGOING\t\tTOTAL ACCUMULATION PER SETTLEMENT DAY \n";
        StringBuilder actualHeader = ReportUtil.prepareReportHeader();

        Assert.assertNotNull(actualHeader);
        Assert.assertEquals(EXPECTED_HEADER_STRING, actualHeader.toString());
    }

    @Test
    public void toExpectedFormat() throws Exception {
        final String EXPECTED_FORMAT = "14 May 2017 12:00 AM";
        LocalDateTime aDate = LocalDateTime.of(2017, 05, 14, 0, 0, 0);
        String acutalFormat = ReportUtil.toExpectedFormat(aDate);

        Assert.assertNotNull(acutalFormat);
        Assert.assertEquals(EXPECTED_FORMAT, acutalFormat);
    }

    @Test
    public void roundOff() throws Exception {
        final String EXPECTED_ROUNDED_OFF_VALUE = String.valueOf(100.68);
        BigDecimal aAmountToRoundOff = new BigDecimal(100.67888);
        BigDecimal acutalRoundOff = ReportUtil.roundOff(aAmountToRoundOff);

        Assert.assertNotNull(acutalRoundOff);
        Assert.assertEquals(EXPECTED_ROUNDED_OFF_VALUE, String.valueOf(acutalRoundOff));

    }

    private Trade createTrade(int day, String currency) {
        String[] tradeValues = new String[12];
        tradeValues[0] = "TRK";
        tradeValues[1] = "S";
        tradeValues[2] = "1";
        tradeValues[3] = currency;
        tradeValues[4] = "21";
        tradeValues[5] = "May";
        tradeValues[6] = "2017";
        tradeValues[7] = String.valueOf(day);
        tradeValues[8] = "May";
        tradeValues[9] = "2017";
        tradeValues[10] = "100";
        tradeValues[11] = "244910.00";
        Trade trade = new Trade(tradeValues);
        return trade;
    }

}