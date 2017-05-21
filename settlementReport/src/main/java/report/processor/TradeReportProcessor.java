package report.processor;


import report.common.ReportEnum;
import report.domain.Report;
import report.domain.Trade;
import report.error.ReportProcessorException;
import report.io.TradeInfoReaderWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static report.common.ReportUtil.*;


/**
 * Created by Jai on 12-05-2017.
 */
public class TradeReportProcessor {

    TradeInfoReaderWriter readerWriter;
    List<Trade> trades;
    List<Report> reports;
    List<Report> outgoingRanked;
    List<Report> incomingRanked;

    public TradeReportProcessor(TradeInfoReaderWriter readerWriter) {
        this.readerWriter = readerWriter;
        this.reports = new ArrayList<>(100);
    }

    public TradeReportProcessor readInstructions() throws ReportProcessorException, IOException {
        trades = readerWriter.readTradeInstructions(INPUT_INSTRUCTIONS_PATH);
        return this;
    }

    public TradeReportProcessor processSettlements() {
        Consumer<Trade> tradeConsumer = getTradeConsumer();
        trades.stream().forEach(trade -> tradeConsumer.accept(trade));

        return this;
    }

    public TradeReportProcessor rankEntities() {
        List<Report> outgoingReports = reports.stream().filter(outgoingPredicate()).collect(Collectors.toList());
        outgoingRanked = stampRanks(outgoingReports);
        List<Report> incomingReports = reports.stream().filter(incomingPredicate()).collect(Collectors.toList());
        incomingRanked = stampRanks(incomingReports);
        reports.clear();
        reports.addAll(incomingRanked);
        reports.addAll(outgoingRanked);

        return this;
    }

    private List<Report> stampRanks(List<Report> toBeRanked) {
        Collections.sort(toBeRanked);
        int rank = 0;
        BigDecimal lastAmts = null;
        String lastEntity = null;
        for (Report r : toBeRanked) {
            if (!r.getSettledAmount().equals(lastAmts)) {
                rank += 1;
            }
            lastAmts = r.getSettledAmount();
            if (!r.getEntityId().equals(lastEntity)) {
                r.setRank(rank);
            } else {
                rank -= 1;
                r.setRank(rank);
            }

            lastEntity = r.getEntityId();
        }
        return toBeRanked;
    }

    private Predicate<Report> incomingPredicate() {
        return trade -> trade.getTradeType().equalsIgnoreCase(ReportEnum.TRADE_TYPE_SELL.value());
    }

    private Predicate<Report> outgoingPredicate() {
        return trade -> trade.getTradeType().equalsIgnoreCase(ReportEnum.TRADE_TYPE_BUY.value());
    }

    private Consumer<Trade> getTradeConsumer() {
        return trade -> {
            reports.add(new Report(trade.getActualSettlementDate(), trade.getEntityId(), trade.getUsdAmount(), trade.getTradeType(), null));
        };
    }

    public TradeReportProcessor generateDailySettlementReports() throws ReportProcessorException, IOException {
        StringBuilder reportBuilder = prepareReportHeader();
        reports.sort(Comparator.comparing(Report::getSettlementDate));
        LocalDateTime lastSettlementDateI = reports.stream().filter(incomingPredicate()).findFirst().get().getSettlementDate();
        BigDecimal totalForTheDayI = new BigDecimal(0.0);
        BigDecimal totalForTheDayO = new BigDecimal(0.0);
        LocalDateTime lastSettlementDateO = reports.stream().filter(outgoingPredicate()).findFirst().get().getSettlementDate();

        for (Report report : reports) {
            reportBuilder.append(toExpectedFormat(report.getSettlementDate()));
            reportBuilder.append("\t\t");
            reportBuilder.append(report.getEntityId());
            reportBuilder.append("\t\t\t");
            reportBuilder.append(report.getRank());
            if (report.getTradeType().equalsIgnoreCase(ReportEnum.TRADE_TYPE_SELL.value())) {
                reportBuilder.append("\t\t\t");
                reportBuilder.append(report.getSettledAmount());

                if (report.getSettlementDate().isEqual(lastSettlementDateI)) {
                    totalForTheDayI = totalForTheDayI.add(report.getSettledAmount());
                    reportBuilder.append("\t\t\t\t\t\t\t\t\t" + roundOff(totalForTheDayI));
                } else {
                    totalForTheDayI = report.getSettledAmount();
                    reportBuilder.append("\t\t\t\t\t\t\t\t\t" + totalForTheDayI);

                }

                lastSettlementDateI = report.getSettlementDate();
            } else {
                reportBuilder.append("\t\t\t\t\t\t\t\t");
                reportBuilder.append(report.getSettledAmount());
                if (report.getSettlementDate().isEqual(lastSettlementDateO)) {
                    totalForTheDayO = totalForTheDayO.add(report.getSettledAmount());
                    reportBuilder.append("\t\t\t\t" + roundOff(totalForTheDayO));
                } else {
                    totalForTheDayO = report.getSettledAmount();
                    reportBuilder.append("\t\t\t\t" + totalForTheDayO);
                }

                lastSettlementDateO = report.getSettlementDate();
            }
            reportBuilder.append("\n");
        }

        readerWriter.writeTradeReport(reportBuilder.toString(), OUTPUT_SETTLEMENT_REPORT_PATH);

        return this;
    }

    public void displayRankings() throws ReportProcessorException, IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("\nRANK \tENTITY\tRANKED_FOR\t TRADED_AMT_USD\n");
        incomingRanked.addAll(outgoingRanked);
        incomingRanked.stream().forEach(rankedEntity -> {
            builder.append(rankedEntity.getRank());
            builder.append("\t\t");
            builder.append(rankedEntity.getEntityId());
            builder.append("\t\t");
            builder.append(rankedEntity.getTradeType().equals(ReportEnum.TRADE_TYPE_BUY.value()) ? "OUTGOING" : "INOMING");
            builder.append("\t\t");
            builder.append(rankedEntity.getSettledAmount());
            builder.append("\n");
        });

        readerWriter.writeTradeReport(builder.toString(), OUTPUT_ENTITY_RANKS_PATHS);
    }
}
