package report;


import report.error.ReportProcessorException;
import report.io.Impl.TradeInfoReaderWriterService;
import report.processor.TradeReportProcessor;

import java.io.IOException;

public class ReportApplication {
    public static void main(String[] args) throws ReportProcessorException, IOException {
        new TradeReportProcessor(new TradeInfoReaderWriterService())
                .readInstructions()
                .processSettlements()
                .rankEntities()
                .generateDailySettlementReports()
                .displayRankings();
    }
}
