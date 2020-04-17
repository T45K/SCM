class Sample {
    public static int max(final int a, final int b) {
        return a > b ? a : b;
    }

    public static long max(final long x, final long y) {
        return
                x > y
                        ? x
                        :
                        y
                ;

    }

    public static int min(final int a, final int b) {
        return a < b ? a : b;
    }

    public static void defineLiterals() {
        int a = 0;
        short b = 0;
        long c = 0l;
        float d = 0f;
        double e = 0d;
        char f = '0';
        String g = "0";
    }
}
