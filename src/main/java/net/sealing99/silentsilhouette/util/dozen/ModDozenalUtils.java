package net.sealing99.silentsilhouette.util.dozen;

public class ModDozenalUtils {
    private static final char[] DIGITS = {
            '0','1','2','3','4','5',
            '6','7','8','9','X','E'
    };

    public static String toDozenal(int number) {
        if (number == 0) return "0";

        boolean negative = number < 0;
        number = Math.abs(number);

        StringBuilder sb = new StringBuilder();

        while (number > 0) {
            int remainder = number % 12;
            sb.append(DIGITS[remainder]);
            number /= 12;
        }

        if (negative) {
            sb.append("-");
        }

        return sb.reverse().toString();
    }

    public static String toDozenal(double value, int precision) {
        final char[] DIGITS = {
                '0','1','2','3','4','5',
                '6','7','8','9','X','E'
        };

        boolean negative = value < 0;
        value = Math.abs(value);

        // Integer part
        long integerPart = (long) value;
        double fractionalPart = value - integerPart;

        StringBuilder result = new StringBuilder();

        // Convert integer part
        if (integerPart == 0) {
            result.append("0");
        } else {
            StringBuilder intBuilder = new StringBuilder();

            while (integerPart > 0) {
                int remainder = (int)(integerPart % 12);
                intBuilder.append(DIGITS[remainder]);
                integerPart /= 12;
            }

            result.append(intBuilder.reverse());
        }

        if (precision > 0) {
            result.append(".");

            for (int i = 0; i < precision; i++) {
                fractionalPart *= 12;

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
}
