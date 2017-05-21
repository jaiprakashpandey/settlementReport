package report.io.Impl;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by Jai on 11-05-2017.
 */
public class TradeInfoReaderWriterServiceTest {
    @After
    public void tearDown() throws Exception {
        readerWriterService = null;
    }

    TradeInfoReaderWriterService readerWriterService;

    @Before
    public void setUp() throws Exception {
        readerWriterService = new TradeInfoReaderWriterService();
    }

    @Test
    public void readTradeInstructions() throws Exception {
        String fileName = "instructions.txt";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        readerWriterService.readTradeInstructions(file.getAbsolutePath());
    }

    @Test
    public void writeTradeReport() throws Exception {
        final String data = "Unit tests report By JPMC Copyright since 1883";
        final String writeFileName = "c://jpmc//UnitTestTradeReport.txt";
        readerWriterService.writeTradeReport(data, writeFileName);
    }
}