package camp.nextstep.edu.calculator.expression;

import camp.nextstep.edu.calculator.Guard;
import camp.nextstep.edu.calculator.InvalidArgumentException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Delimiter {

    private static final Pattern CUSTOM_PATTERN = Pattern.compile("//(.)\n(.*)");

    static final String DEFAULT_REGEX = "[,:]";
    private static final int DELIMITER_INDEX = 1;
    private static final int EXPRESSION_INDEX = 2;

    private Delimiter() {
    }

    static String[] delimit(final String source) {
        validate(source);

        final Matcher customMatcher = CUSTOM_PATTERN.matcher(source);
        if (customMatcher.find()) {
            final String delimiter = customMatcher.group(DELIMITER_INDEX);
            final String expression = customMatcher.group(EXPRESSION_INDEX);

            return expression.split(delimiter);
        }

        return source.split(DEFAULT_REGEX);
    }

    private static void validate(final String source) {
        if (Guard.isNullOrBlank(source)) {
            throw new InvalidArgumentException(source);
        }
    }
}
