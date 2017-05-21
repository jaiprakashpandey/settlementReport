package report.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import report.common.ReportEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static report.common.ReportUtil.roundOff;

/**
 * Created by K316940 on 21-05-2017.
 */
public class TradeTest {
    Trade trade1;
    String[] tradeValues;

    @Before
    public void setUp() throws Exception {
        tradeValues = new String[12];
        tradeValues[0] = "BRK";
        tradeValues[1] = "S";
        tradeValues[2] = "1";
        tradeValues[3] = "USD";
        tradeValues[4] = "21";
        tradeValues[5] = "May";
        tradeValues[6] = "2017";
        tradeValues[7] = "22";
        tradeValues[8] = "May";
        tradeValues[9] = "2017";
        tradeValues[10] = "100";
        tradeValues[11] = "244910.00";
        trade1 = new Trade(tradeValues);
    }

    @After
    public void tearDown() throws Exception {
        trade1 = null;
    }


    @Test
    public void getEntityId() throws Exception {
        String EXPECTED_ENTITY = "BRK";
        String actualEntity = trade1.getEntityId();
        Assert.assertEquals(EXPECTED_ENTITY, actualEntity);
    }

    @Test
    public void getTradeType() throws Exception {
        String EXPECTED_TRADE_TYPE = ReportEnum.TRADE_TYPE_SELL.value();
        String actualTadeType = trade1.getTradeType();
        Assert.assertEquals(EXPECTED_TRADE_TYPE, actualTadeType);
    }

    @Test
    public void getAgreedFx() throws Exception {
        BigDecimal EXPECTED_FX = new BigDecimal(1);
        BigDecimal actualFX = trade1.getAgreedFx();
        Assert.assertEquals(EXPECTED_FX, actualFX);
    }

    @Test
    public void getCurrency() throws Exception {
        String EXPECTED_CURRENCY = ReportEnum.USA_DOLLAR.value();
        String actualTadeType = trade1.getCurrency();
        Assert.assertEquals(EXPECTED_CURRENCY, actualTadeType);
    }

    @Test
    public void getSettlementDate() throws Exception {
        LocalDateTime EXPECTED_SETTLEMENT_DATE = LocalDateTime.of(2017, 05, 22, 0, 0, 0);
        LocalDateTime actualSettlementDate = trade1.getSettlementDate();
        Assert.assertEquals(EXPECTED_SETTLEMENT_DATE, actualSettlementDate);
    }

    @Test
    public void getUnits() throws Exception {
        Integer EXPECTED_UNITS = 100;
        Integer actualNumberfUnits = trade1.getUnits();
        Assert.assertEquals(EXPECTED_UNITS, actualNumberfUnits);
    }

    @Test
    public void getRate() throws Exception {
        BigDecimal EXPECTED_RATE = roundOff(new BigDecimal(244910.001));
        BigDecimal actualRate = trade1.getRate();
        Assert.assertEquals(EXPECTED_RATE, actualRate);
    }


    @Test
    public void testEquals() throws Exception {

        tradeValues[0] = "TRK";
        tradeValues[1] = "S";
        tradeValues[2] = "1";
        tradeValues[3] = "USD";
        tradeValues[4] = "21";
        tradeValues[5] = "May";
        tradeValues[6] = "2017";
        tradeValues[7] = "22";
        tradeValues[8] = "May";
        tradeValues[9] = "2017";
        tradeValues[10] = "100";
        tradeValues[11] = "244910.00";
        Trade trade2 = new Trade(tradeValues);

        boolean actual1 = trade1.equals(trade1);
        boolean actual2 = trade1.equals(trade2);

        Assert.assertTrue(actual1);
        Assert.assertFalse(actual2);
    }

    @Test
    public void getUsdAmount() throws Exception {
        BigDecimal EXPECTED_USD = roundOff(new BigDecimal(244910.001)).multiply(new BigDecimal(100.00)).multiply(new BigDecimal(1.0));
        BigDecimal actualUSD = trade1.getUsdAmount();
        Assert.assertEquals(EXPECTED_USD, actualUSD);
    }

    @Test
    public void getActualSettlementDateForCurrencyOtheThan_Middle_East_countries() throws Exception {
        //set settlement date for holiday as per US dollar country 21st may as sunda
        Trade tradeHavingSettlementDayOnSunday = createTrade(21, ReportEnum.USA_DOLLAR.value());
        // We expect Actual Settleent date as on monday 22nd May
        LocalDateTime EXPECTED_SETTLEMENT_DATE = LocalDateTime.of(2017, 05, 22, 0, 0, 0);

        LocalDateTime actualSettlementDate = tradeHavingSettlementDayOnSunday.getActualSettlementDate();
        Assert.assertEquals(EXPECTED_SETTLEMENT_DATE, actualSettlementDate);
    }

    @Test
    public void getActualSettlementDateForCurrency_Middle_East_Countries() throws Exception {
        //set settlement date for holiday as per Saudi Arabia Friday is holiday so
        Trade tradeHavingSettlementDayOnFridayForSaudi = createTrade(19, ReportEnum.SAUDI_RIAL.value());
        // We expect Actual settlement date as on Sunday 21st May 2017  as that is working day in SAUDI
        LocalDateTime EXPECTED_SETTLEMENT_DATE = LocalDateTime.of(2017, 05, 21, 0, 0, 0);

        LocalDateTime actualSettlementDate = tradeHavingSettlementDayOnFridayForSaudi.getActualSettlementDate();
        Assert.assertEquals(EXPECTED_SETTLEMENT_DATE, actualSettlementDate);

        //set settlement date for holiday as per UAE Saturday is holiday so
        Trade tradeHavingSettlementDayOnSaturdayForUAE = createTrade(13, ReportEnum.UNITED_ARAB_EMIRATES_DINAR.value());

        // We expect Actual settlement date as on Sunday 14th May 2017 as that is working day in SAUDI
        EXPECTED_SETTLEMENT_DATE = LocalDateTime.of(2017, 05, 14, 0, 0, 0);

        actualSettlementDate = tradeHavingSettlementDayOnSaturdayForUAE.getActualSettlementDate();
        Assert.assertEquals(EXPECTED_SETTLEMENT_DATE, actualSettlementDate);
    }

    private Trade createTrade(int day, String currency) {
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