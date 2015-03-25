package util;

import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: Sam Wright Date: 20/09/2013 Time: 20:14
 */
public class DateCalcUtil {

    /**
     * Gets the calendar years of all days in the specified range.
     *
     * NB. This overestimates the number of holidays in the year (it assumes at least 150 days a
     * year are spent working).
     *
     * @param startDate the start data of the range.
     * @param durationInDays the duration of the range, in days.
     * @return the calendar years of all days in the specified range.
     */
    public static List<Integer> getApproximateYears(LocalDate startDate, int durationInDays) {
        List<Integer> years = new ArrayList<>();
        int year = startDate.year().get();
        years.add(year);

        while (durationInDays > 0) {
            years.add(++year);
            durationInDays -= 150;
        }

        return years;
    }


    /**
     * Gets the next working date after the given duration (in working days) after the given
     * starting date.
     *
     * @param startDate the starting date of the range.
     * @param durationInDays the number of working days to count on.
     * @return the next working date after the given duration after the given starting date.
     */
    public static LocalDate getEndDate(LocalDate startDate, int durationInDays, Set<LocalDate> holidayDates) {
        List<Integer> approximateYears = getApproximateYears(startDate, durationInDays);

        String yearStart = approximateYears.get(0) + "-01-01";
        String yearEnd = approximateYears.get(approximateYears.size()-1) + "-12-31";

        DefaultHolidayCalendar<LocalDate> calendar = new DefaultHolidayCalendar<>(
                holidayDates,
                new LocalDate(yearStart),
                new LocalDate(yearEnd)
        );

        LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("UK", calendar);

        // Create the date calculator based on the created calendar
        DateCalculator<LocalDate> calc
                = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("UK", HolidayHandlerType.FORWARD);

        // Set the starting date
        calc.setStartDate(startDate);

        // Get next working date after given durationInDays
        return calc.moveByBusinessDays(durationInDays).getCurrentBusinessDate();
    }

    /**
     * 计算交易日
     * @param startDateStr 格式yyyy-MM-dd HH:mm:ss
     * @param durationInDays 间隔天数
     * @param holidayStr 假期时间字符串，格式“2015-01-01;2015-01-02;2015-01-03”
     * @return
     */
    public static LocalDate getEndTradeDate(String startDateStr, int durationInDays, String holidayStr){
        String[] array = holidayStr.split(";");

        Set<LocalDate> holidayDates = new HashSet<>();
        LocalDate holidayDate = null;
        for (String holiday : array) {
            holidayDate = new LocalDate(holiday);
            holidayDates.add(holidayDate);
        }

        Timestamp dateTime = Timestamp.valueOf(startDateStr);
        LocalDateTime startDateTime = new LocalDateTime(dateTime);
        LocalDate startDate = startDateTime.toLocalDate();

        if(startDateTime.getHourOfDay() >= 15){
            startDate = startDate.plusDays(1);
        }

        LocalDate endDate = DateCalcUtil.getEndDate(startDate, durationInDays, holidayDates);

        return endDate;
    }

}
