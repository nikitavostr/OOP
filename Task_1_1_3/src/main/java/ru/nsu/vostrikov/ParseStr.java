package ru.nsu.vostrikov;

/**
 * Parser.
 */
class ParseStr {
    /**
     * delete spaces and call parser function.
     */
    public Expression parseStr(String expression) {
        expression = expression.replace(" ", "");
        return parser(expression);
    }

    /**
     * recursive parser.
     */
    private Expression parser(String expression) {
        int bracketCount = 0;
        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
            if (currentChar == ')') {
                bracketCount--;
            }
            if (currentChar == '(') {
                bracketCount++;
            }
            if (bracketCount == 0) {
                {
                    switch (currentChar) {
                        case '+':
                            return new Add(parseStr(expression.substring(0, i)),
                                    parser(expression.substring(i + 1)));
                        case '-':
                            return new Sub(parseStr(expression.substring(0, i)),
                                    parser(expression.substring(i + 1)));

                        case '*':
                            return new Mul(parser(expression.substring(0, i)),
                                    parser(expression.substring(i + 1)));

                        case '/':
                            return new Div(parseStr(expression.substring(0, i)),
                                    parser(expression.substring(i + 1)));

                        default:
                            break;
                    }
                }
            }
        }
        if (expression.matches("\\d+")) {
            return new Number(Integer.parseInt(expression));
        }

        if (expression.startsWith("(") && expression.endsWith(")")) {
            return parser(expression.substring(1, expression.length() - 1));
        }

        return new Variable(expression);
    }
}
