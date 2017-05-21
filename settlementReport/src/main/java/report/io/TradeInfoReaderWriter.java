package report.io;


import report.domain.Trade;
import report.error.ReportProcessorException;

import java.util.List;

/**
 * Created by Jai on 07-10-2016.
 */
public interface TradeInfoReaderWriter {

    List<Trade> readTradeInstructions(String path) throws ReportProcessorException;

    void writeTradeReport(String reportData, String writeFileName) throws ReportProcessorException;
}
