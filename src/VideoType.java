public enum VideoType {
    VHS(1), CD(2), DVD(3);
    private final int value;

    VideoType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static VideoType findVideoType(int value) {
        for (VideoType type : VideoType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new RuntimeException();
    }
}