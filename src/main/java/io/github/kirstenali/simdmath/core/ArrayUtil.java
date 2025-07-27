package io.github.kirstenali.simdmath.core;

public final class ArrayUtil {
    private ArrayUtil() {}

    public static void checkSameLength(int aLen, int bLen) {
        if (aLen != bLen) {
            throw new IllegalArgumentException("Arrays must have the same length: " + aLen + " != " + bLen);
        }
    }
}
