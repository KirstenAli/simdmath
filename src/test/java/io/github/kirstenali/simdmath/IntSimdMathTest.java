package io.github.kirstenali.simdmath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntSimdMathTest {

    @Test
    void add_sub_mul_min_max_clamp_sum_dot_where() {
        int[] a = {1, 2, 3, 4};
        int[] b = {5, 6, 7, 8};

        // add / sub / mul
        assertArrayEquals(new int[]{6, 8, 10, 12}, SimdMath.add(a, b));
        assertArrayEquals(new int[]{-4, -4, -4, -4}, SimdMath.sub(a, b));
        assertArrayEquals(new int[]{5, 12, 21, 32}, SimdMath.mul(a, b));

        // min / max
        assertArrayEquals(new int[]{1, 2, 3, 4}, SimdMath.min(a, b));
        assertArrayEquals(new int[]{5, 6, 7, 8}, SimdMath.max(a, b));

        // clamp
        assertArrayEquals(new int[]{1, 2, 3, 4}, SimdMath.clamp(a, 1, 4));

        // reductions
        assertEquals(70L, SimdMath.dot(a, b));
        assertEquals(26L, SimdMath.sum(b));

        // whereGreater — alternate F, T, F, T
        int[] aw_g = {1, 7, 3, 9};
        int[] bw_g = {5, 6, 7, 8};
        int[] t = {10,10,10,10};
        int[] f = {20,20,20,20};
        assertArrayEquals(new int[]{20, 10, 20, 10}, SimdMath.whereGreater(aw_g, bw_g, t, f));

        // whereEqual — equality at lanes 1 & 3
        int[] aw_eq = {0, 1, 2, 3};
        int[] bw_eq = {9, 1, 9, 3};
        assertArrayEquals(new int[]{20, 10, 20, 10}, SimdMath.whereEqual(aw_eq, bw_eq, t, f));

        // whereLess — true at lanes 0 & 2
        int[] aw_lt = {1, 9, 3, 9};
        int[] bw_lt = {5, 6, 7, 8};
        assertArrayEquals(new int[]{10, 20, 10, 20}, SimdMath.whereLess(aw_lt, bw_lt, t, f));
    }
}
