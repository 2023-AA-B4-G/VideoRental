public enum PriceCode {
    REGULAR(1), NEW_RELEASE(2);
    private final int value;

    PriceCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}