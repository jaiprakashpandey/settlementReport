package report.common;


import report.domain.Trade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Created by Jai on 11-05-2017.
 */
public class ReportUtil {

    public static LocalDateTime toLocalDateTime(String strDate) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("ddMMMyyyy")
                .toFormatter();
        return LocalDate.parse(strDate, formatter).atStartOfDay();
    }

    public static Integer toInteger(String stringData) {
        return Integer.valueOf(stringData);
    }

    public static LocalDateTime getNextWorkingDay(LocalDateTime dt, Trade trade) {
        switch (dt.getDayOfWeek()) {
            case FRIDAY:
                return isMiddleEastCurrency(trade) ? dt.plusDays(2) : dt;
            case SATURDAY:
                return isMiddleEastCurrency(trade) ? dt.plusDays(1) : dt.plusDays(2);
            case SUNDAY:
                return isMiddleEastCurrency(trade) ? dt : dt.plusDays(1);
            default:
                return dt;
        }
    }

    private static boolean isMiddleEastCurrency(Trade trade) {
        return trade.getCurrency().equalsIgnoreCase(ReportEnum.SAUDI_RIAL.value())
                || (trade.getCurrency().equalsIgnoreCase(ReportEnum.UNITED_ARAB_EMIRATES_DINAR.value()));
    }

    public static StringBuilder prepareReportHeader() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n\nACTUAL SETTLEMENT DATE\t\t");
        builder.append("ENTITY\t\t");
        builder.append("RANK \t\t");
        builder.append("USD INCOMING\t\t");
        builder.append("USD OUTGOING\t\t");
        builder.append("TOTAL ACCUMULATION PER SETTLEMENT DAY \n");

        return builder;
    }

    public static String toExpectedFormat(LocalDateTime ldt) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return ldt.format(format);
    }

    public static BigDecimal roundOff(BigDecimal amt) {
        return amt.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    final public static String INPUT_INSTRUCTIONS_PATH = "C://jpmc//Instructions.txt";
    final public static String OUTPUT_SETTLEMENT_REPORT_PATH = "C://jpmc//TradeReports.txt";
    final public static String OUTPUT_ENTITY_RANKS_PATHS = "C://jpmc//Ranks.txt";
}
