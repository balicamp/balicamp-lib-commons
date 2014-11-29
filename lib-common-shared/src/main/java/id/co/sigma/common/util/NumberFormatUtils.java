package id.co.sigma.common.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * A one-liner percentage formatter for a number. This utility class is intended to be use in JasperReport.
 *
 * @author <a href="mailto:wayan.saryada@sigma.co.id">Wayan Saryada</a>
 */
public class NumberFormatUtils {

    /**
     * Indonesian Locale.
     */
    public static final Locale INDONESIA = new Locale("in", "ID");

    private NumberFormatUtils() {
    }

    /**
     * Returns percent format for the specified number.
     *
     * @param number                a number to be formatted.
     * @param minimumFractionDigits minimum fraction digits.
     * @return percentage formatted number.
     */
    public static String formatPercent(double number, int minimumFractionDigits) {
        return NumberFormatUtils.getPercentInstance(minimumFractionDigits).format(number);
    }

    /**
     * Returns percent format for the specified number and locale.
     *
     * @param number                a number to be formatted.
     * @param minimumFractionDigits minimum fraction digits.
     * @param locale                a locale.
     * @return percentage formatted number.
     */
    public static String formatPercent(double number, int minimumFractionDigits, Locale locale) {
        return NumberFormatUtils.getPercentInstance(minimumFractionDigits, locale).format(number);
    }

    /**
     * Returns percent format for the specified number.
     *
     * @param number                a number to be formatted.
     * @param minimumFractionDigits minimum fraction digits.
     * @return percentage formatted number.
     */
    public static String formatPercent(long number, int minimumFractionDigits) {
        return NumberFormatUtils.getPercentInstance(minimumFractionDigits).format(number);
    }

    /**
     * Returns percent format for the specified number and locale.
     *
     * @param number                a number to be formatted.
     * @param minimumFractionDigits minimum fraction digits.
     * @param locale                a locale.
     * @return percentage formatted number.
     */
    public static String formatPercent(long number, int minimumFractionDigits, Locale locale) {
        return NumberFormatUtils.getPercentInstance(minimumFractionDigits, locale).format(number);
    }

    /**
     * Returns {@code NumberFormat} instance with a minimum fraction digits value.
     *
     * @param minimumFractionDigits minimum fraction digits.
     * @return {@code NumberFormat} instance.
     */
    private static NumberFormat getPercentInstance(int minimumFractionDigits) {
        return NumberFormatUtils.getPercentInstance(minimumFractionDigits, Locale.getDefault());
    }

    /**
     * Returns {@code NumberFormat} instance with a minimum fraction digits value and a locale information.
     *
     * @param minimumFractionDigits minimum fraction digits.
     * @param locale                a locale.
     * @return {@code NumberFormat} instance.
     */
    private static NumberFormat getPercentInstance(int minimumFractionDigits, Locale locale) {
        NumberFormat numberFormat = NumberFormat.getPercentInstance(locale);
        numberFormat.setMinimumFractionDigits(minimumFractionDigits);
        return numberFormat;
    }
}