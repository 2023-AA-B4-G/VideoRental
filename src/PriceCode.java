public enum PriceCode {
    REGULAR(1), NEW_RELEASE(2);
    private final int value;

    PriceCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PriceCode findPriceCode(int value) {
        for (PriceCode code : PriceCode.values()) {
            if (code.getValue() == value) {
                return code;
            }
        }
        throw new RuntimeException();
    }
}