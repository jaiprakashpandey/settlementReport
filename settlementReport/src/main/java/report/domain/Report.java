package report.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Jai on 10-05-2017.
 */
public class Report implements Comparable<Report> {
    String entityId;
    String tradeType;
    LocalDateTime settlementDate;
    BigDecimal settledAmount;
    Integer rank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (entityId != null ? !entityId.equals(report.entityId) : report.entityId != null) return false;
        return settlementDate != null ? settlementDate.equals(report.settlementDate) : report.settlementDate == null;

    }

    @Override
    public int hashCode() {
        int result = entityId != null ? entityId.hashCode() : 0;
        result = 31 * result + (settlementDate != null ? settlementDate.hashCode() : 0);
        return result;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public LocalDateTime getSettlementDate() {
        return settlementDate;
    }

    public BigDecimal getSettledAmount() {
        return settledAmount;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Report(LocalDateTime settlementDate, String entityId, BigDecimal settledAmount, String tradeType, Integer rank) {
        this.entityId = entityId;
        this.tradeType = tradeType;
        this.settlementDate = settlementDate;
        this.settledAmount = settledAmount;
        this.rank = rank;
    }

    @Override
    public int compareTo(Report o) {
        return o.getSettledAmount().toBigInteger().intValue() - this.getSettledAmount().toBigInteger().intValue();
    }
}
