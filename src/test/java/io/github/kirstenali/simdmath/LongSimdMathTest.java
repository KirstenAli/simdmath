package io.github.kirstenali.simdmath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LongSimdMathTest {

    @Test
    void add_sub_mul_min_max_clamp_sum_dot_where() {
        long[] a = {1L, 2L, 3L, 4L};
        long[] b = {5L, 6L, 7L, 8L};

        // add / sub / mul
        assertArrayEquals(new long[]{6L, 8L, 10L, 12L}, SimdMath.add(a, b));
        assertArrayEquals(new long[]{-4L, -4L, -4L, -4L}, SimdMath.sub(a, b));
        assertArrayEquals(new long[]{5L, 12L, 21L, 32L}, SimdMath.mul(a, b));

        // min / max
        assertArrayEquals(new long[]{1L, 2L, 3L, 4L}, SimdMath.min(a, b));
        assertArrayEquals(new long[]{5L, 6L, 7L, 8L}, SimdMath.max(a, b));

        // clamp
        assertArrayEquals(new long[]{1L, 2L, 3L, 4L}, SimdMath.clamp(a, 1L, 4L));

        // reductions
        assertEquals(70L, SimdMath.dot(a, b));
        assertEquals(26L, SimdMath.sum(b));

        // whereGreater — alternate F, T, F, T
        long[] aw_g = {1L, 7L, 3L, 9L};
        long[] bw_g = {5L, 6L, 7L, 8L};
        long[] t = {10L,10L,10L,10L};
        long[] f = {20L,20L,20L,20L};
        assertArrayEquals(new long[]{20L, 10L, 20L, 10L}, SimdMath.whereGreater(aw_g, bw_g, t, f));

        // whereEqual — equality at lanes 1 & 3
        long[] aw_eq = {0L, 1L, 2L, 3L};
        long[] bw_eq = {9L, 1L, 9L, 3L};
        assertArrayEquals(new long[]{20L, 10L, 20L, 10L}, SimdMath.whereEqual(aw_eq, bw_eq, t, f));

        // whereLess — true at lanes 0 & 2
        long[] aw_lt = {1L, 9L, 3L, 9L};
        long[] bw_lt = {5L, 6L, 7L, 8L};
        assertArrayEquals(new long[]{10L, 20L, 10L, 20L}, SimdMath.whereLess(aw_lt, bw_lt, t, f));
    }
}
