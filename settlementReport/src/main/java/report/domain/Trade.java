package report.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static report.common.ReportUtil.*;


/**
 * Created by Jai on 10-05-2017.
 */
public class Trade {
    String entityId;
    String tradeType;
    BigDecimal agreedFx;
    String currency;
    LocalDateTime instructionDate;
    LocalDateTime settlementDate;
    Integer units;
    BigDecimal rate;

    public String getEntityId() {
        return entityId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getSettlementDate() {
        return settlementDate;
    }

    public Integer getUnits() {
        return units;
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trade that = (Trade) o;

        if (!entityId.equals(that.entityId)) return false;
        return instructionDate.equals(that.instructionDate);

    }

    @Override
    public int hashCode() {
        int result = entityId.hashCode();
        result = 31 * result + instructionDate.hashCode();
        return result;
    }

    public Trade(String... values) {
        this.entityId = values[0];
        this.tradeType = values[1];
        this.agreedFx = new BigDecimal(values[2]);
        this.currency = values[3];
        this.instructionDate = toLocalDateTime(new StringBuilder(values[4]).append(values[5]).append(values[6]).toString());
        this.settlementDate = toLocalDateTime(new StringBuilder(values[7]).append(values[8]).append(values[9]).toString());
        this.units = toInteger(values[10]);
        this.rate = new BigDecimal(values[11]);
    }

    public BigDecimal getUsdAmount() {
        BigDecimal usdAmt = this.getRate().multiply(BigDecimal.valueOf(this.getUnits())).multiply(this.getAgreedFx());
        return roundOff(usdAmt);
    }

    public LocalDateTime getActualSettlementDate() {
        return getNextWorkingDay(this.getSettlementDate(), this);
    }

}
