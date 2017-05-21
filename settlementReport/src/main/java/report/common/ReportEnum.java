package report.common;

/**
 * Created by Jai on 10-05-2017.
 */
public enum ReportEnum {
    UNITED_ARAB_EMIRATES_DINAR("AED"),
    SAUDI_RIAL("SAR"),
    USA_DOLLAR("USD"),
    TRADE_TYPE_BUY("B"),
    TRADE_TYPE_SELL("S");

    private String value;

    ReportEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
