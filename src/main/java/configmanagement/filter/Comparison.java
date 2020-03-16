package configmanagement.filter;

import java.util.Optional;
import java.util.stream.Stream;

public enum Comparison {
    EQUAL("eq"),
    NOT_EQUAL("ne"),
    LESS_THAN_OR_EQUAL_TO("le"),
    GREATER_THAN_OR_EQUAL_TO("ge"),
    LESS_THAN("lt"),
    GREATER_THAN("gt"),
    LIKE("like"),
    IS_NULL("null"),
    IS_NOT_NULL("notnull"),
    IN("in"),
    BETWEEN("between"),
    CONTAINS("contains");

    private final String code;

    Comparison(String code) {
        this.code = code;
    }

    public static Optional<Comparison> getByCode(String s) {
        return Stream.of(Comparison.values())
                .filter(v -> v.code.equalsIgnoreCase(s))
                .findFirst();
    }

    public String getCode() {
        return code;
    }
}
