package report.domain;

import org.junit.Assert;
import org.junit.Test;
import report.common.ReportEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by K316940 on 21-05-2017.
 */
public class ReportTest {
    @Test
    public void equalsIfDifferentSettlementDateAndEntity() throws Exception {
        Report report1 = new Report(LocalDateTime.now(), "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        Report report2 = new Report(LocalDateTime.now().minusDays(1), "TRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));

        boolean actual = report1.equals(report2);

        Assert.assertFalse(actual);
    }

    @Test
    public void equalsIfSameSettlementDateAndSameEntity() throws Exception {
        final LocalDateTime sameSettlementDate = LocalDateTime.now();
        Report report1 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        Report report2 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));

        boolean actual = report1.equals(report2);

        Assert.assertTrue(actual);
    }

    @Test
    public void equalsIfSameSettlementDateAndDifferentEntity() throws Exception {
        final LocalDateTime sameSettlementDate = LocalDateTime.now();
        Report report1 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        Report report2 = new Report(sameSettlementDate, "TRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));

        boolean actual = report1.equals(report2);

        Assert.assertFalse(actual);
    }

    @Test
    public void testHashCode() throws Exception {
        Report report1 = new Report(LocalDateTime.now(), "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        Report report2 = new Report(LocalDateTime.now().minusDays(1), "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));

        int report1HashCode = report1.hashCode();
        int report2HashCode = report2.hashCode();

        Assert.assertNotEquals(report1HashCode, report2HashCode);
    }

    @Test
    public void testHashForSameObjectCode() throws Exception {
        final LocalDateTime sameSettlementDate = LocalDateTime.now();
        Report report1 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        Report report2 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));

        int report1HashCode = report1.hashCode();
        int report2HashCode = report2.hashCode();

        Assert.assertEquals(report1HashCode, report2HashCode);
    }

    @Test
    public void getEntityId() throws Exception {
        final LocalDateTime sameSettlementDate = LocalDateTime.now();
        Report report1 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        String EXPECTED_ENTITY = "BRK";
        String actualEntity = report1.getEntityId();

        Assert.assertEquals(EXPECTED_ENTITY, actualEntity);
    }

    @Test
    public void getTradeType() throws Exception {
        final LocalDateTime sameSettlementDate = LocalDateTime.now();
        Report report1 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_SELL.value(), new Integer(0));
        Report report2 = new Report(sameSettlementDate, "MRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        final String EXPECTED_TRADE1 = ReportEnum.TRADE_TYPE_SELL.value();
        final String EXPECTED_TRADE2 = ReportEnum.TRADE_TYPE_BUY.value();

        String actualTradeType1 = report1.getTradeType();
        String actualTradeType2 = report2.getTradeType();

        Assert.assertEquals(EXPECTED_TRADE1, actualTradeType1);
        Assert.assertEquals(EXPECTED_TRADE2, actualTradeType2);
    }

    @Test
    public void getSettlementDate() throws Exception {
        final LocalDateTime sameSettlementDate = LocalDateTime.now();
        Report report1 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        final LocalDateTime EXPECTED_SETTLEMENT_DATE = sameSettlementDate;
        LocalDateTime actualsettlementDate = report1.getSettlementDate();

        Assert.assertEquals(EXPECTED_SETTLEMENT_DATE, actualsettlementDate);
    }

    @Test
    public void getSettledAmount() throws Exception {
        final LocalDateTime sameSettlementDate = LocalDateTime.now();
        Report report1 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        final BigDecimal EXPECTED_SETTLEMENT_AMT = new BigDecimal(100.11);
        BigDecimal actualsettlementAmt = report1.getSettledAmount();

        Assert.assertEquals(EXPECTED_SETTLEMENT_AMT, actualsettlementAmt);
    }

    @Test
    public void getRank() throws Exception {
        final LocalDateTime sameSettlementDate = LocalDateTime.now();
        Report report1 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(1));
        final Integer EXPECTED_RANK = 1;
        Integer actualRank = report1.getRank();

        Assert.assertEquals(EXPECTED_RANK, actualRank);
    }

    @Test
    public void setRank() throws Exception {
        final LocalDateTime sameSettlementDate = LocalDateTime.now();
        Report report1 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(1));
        final Integer EXPECTED_RANK = 2;

        report1.setRank(new Integer(2));
        Integer actualRank = report1.getRank();

        Assert.assertEquals(EXPECTED_RANK, actualRank);

    }

    @Test
    public void compareToIfSameSettlementAmount() throws Exception {
        final LocalDateTime sameSettlementDate = LocalDateTime.now();
        Report report1 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        Report report2 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        final Integer EXPECTED_COMPARE_VAL = 0;
        Integer actual = report1.compareTo(report2);

        Assert.assertEquals(EXPECTED_COMPARE_VAL, actual);
    }

    @Test
    public void compareToIfDifferentSettlementAmount() throws Exception {
        final LocalDateTime sameSettlementDate = LocalDateTime.now();
        Report report1 = new Report(sameSettlementDate, "BRK", new BigDecimal(100.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        Report report2 = new Report(sameSettlementDate, "BRK", new BigDecimal(200.11), ReportEnum.TRADE_TYPE_BUY.value(), new Integer(0));
        final Integer EXPECTED_COMPARE_VAL = report2.getSettledAmount().toBigInteger().intValue() - report1.getSettledAmount().toBigInteger().intValue();
        Integer actual = report1.compareTo(report2);

        Assert.assertEquals(EXPECTED_COMPARE_VAL, actual);
    }

}