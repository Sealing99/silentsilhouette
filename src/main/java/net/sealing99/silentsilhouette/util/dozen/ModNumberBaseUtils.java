package net.sealing99.silentsilhouette.util.dozen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModNumberBaseUtils {
    private static final int base = 16;

    private static final char[] DIGITS = {
            '0','1','2','3','4','5','6','7','8','9',
            'A','B','C','D','E','F'
    };

    private static final Pattern NUMBER_PATTERN =
            Pattern.compile("\\d+(\\.\\d+)?");

    public static String toNumeral(int number) {
        if (number == 0) return "0";

        boolean negative = number < 0;
        number = Math.abs(number);

        StringBuilder sb = new StringBuilder();

        while (number > 0) {
            int remainder = number % base;
            sb.append(DIGITS[remainder]);
            number /= base;
        }

        if (negative) {
            sb.append("-");
        }

        return sb.reverse().toString();
    }

    public static String toNumeral(double value, int precision) {
        boolean negative = value < 0;
        value = Math.abs(value);

        long integerPart = (long) value;
        double fractionalPart = value - integerPart;

        StringBuilder result = new StringBuilder();

        if (integerPart == 0) {
            result.append("0");
        } else {
            StringBuilder intBuilder = new StringBuilder();

            while (integerPart > 0) {
                int remainder = (int)(integerPart % base);
                intBuilder.append(DIGITS[remainder]);
                integerPart /= base;
            }

            result.append(intBuilder.reverse());
        }

        if (precision > 0) {
            result.append(".");

            for (int i = 0; i < precision; i++) {
                fractionalPart *= base;

                int digit = (int) fractionalPart;

                result.append(DIGITS[digit]);

                fractionalPart -= digit;

                if (fractionalPart == 0) {
                    break;
                }
            }
        }

        if (negative) {
            result.insert(0, "-");
        }

        return result.toString();
    }

    public static String convertText(String input) {

        Matcher matcher = NUMBER_PATTERN.matcher(input);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {

            String match = matcher.group();

            String replacement;

            if (match.contains(".")) {

                double d = Double.parseDouble(match);
                replacement = ModNumberBaseUtils.toNumeral(d, 4);

            } else {

                int i = Integer.parseInt(match);
                replacement = ModNumberBaseUtils.toNumeral(i);
            }

            matcher.appendReplacement(result, replacement);
        }

        matcher.appendTail(result);

        return result.toString();
    }
}
