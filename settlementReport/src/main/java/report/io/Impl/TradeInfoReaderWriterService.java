package report.io.Impl;


import report.common.ReportProcessorErrorMsg;
import report.domain.Trade;
import report.error.ReportProcessorException;
import report.io.TradeInfoReaderWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jai on 04-10-2016.
 */

public class TradeInfoReaderWriterService implements TradeInfoReaderWriter {

    public List<Trade> readTradeInstructions(String readFileName) throws ReportProcessorException {
        List<Trade> tradeEntities;
        try {
            tradeEntities = Files
                    .lines(Paths.get(readFileName))
                    .skip(1)
                    .map(ins -> ins.split(" "))
                    .map(values -> new Trade(values))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new ReportProcessorException(ReportProcessorErrorMsg.ERROR_INPUT_FILE_NOT_FOUND, readFileName);
        }
        return tradeEntities;
    }

    public void writeTradeReport(String reportData, String writeFileName) throws ReportProcessorException {
        try {
            List<String> reportDataLines = new ArrayList<>();
            reportDataLines.add(reportData);
            Files.write(Paths.get(writeFileName), reportDataLines);
            //print console as well for demo test
            System.out.println("Please open output reports in NOTEPAD++ for better visualise formats from output paths, defaults C://jpmc/");
            System.out.print(reportDataLines.get(0));
        } catch (IOException e) {
            throw new ReportProcessorException(ReportProcessorErrorMsg.ERROR_OUTPUT_UNABLE_TO_GENERATE_REPORT, writeFileName);
        }
    }
}
