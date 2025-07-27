package io.github.kirstenali.simdmath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleSimdMathTest {

    @Test
    void add_sub_mul_div_min_max_sqrt_clamp_sum_dot_where() {
        double[] a = {1, 2, 3, 4};
        double[] b = {5, 6, 7, 8};

        // add / sub / mul / div
        assertArrayEquals(new double[]{6, 8, 10, 12}, SimdMath.add(a, b), 1e-12);
        assertArrayEquals(new double[]{-4, -4, -4, -4}, SimdMath.sub(a, b), 1e-12);
        assertArrayEquals(new double[]{5, 12, 21, 32}, SimdMath.mul(a, b), 1e-12);
        assertArrayEquals(new double[]{1d/5d, 2d/6d, 3d/7d, 4d/8d}, SimdMath.div(a, b), 1e-12);

        // min / max
        assertArrayEquals(new double[]{1, 2, 3, 4}, SimdMath.min(a, b), 1e-12);
        assertArrayEquals(new double[]{5, 6, 7, 8}, SimdMath.max(a, b), 1e-12);

        // sqrt
        double[] squares = {1, 4, 9, 16};
        assertArrayEquals(new double[]{1, 2, 3, 4}, SimdMath.sqrt(squares), 1e-12);

        // clamp
        assertArrayEquals(new double[]{1, 2, 3, 3.5}, SimdMath.clamp(a, 0.5, 3.5), 1e-12);

        // reductions
        assertEquals(70d, SimdMath.dot(a, b), 1e-12);
        assertEquals(26d, SimdMath.sum(b), 1e-12);

        // whereGreater — alternate F, T, F, T
        double[] aw_g = {1, 7, 3, 9};
        double[] bw_g = {5, 6, 7, 8};
        double[] t = {10,10,10,10};
        double[] f = {20,20,20,20};
        assertArrayEquals(new double[]{20, 10, 20, 10}, SimdMath.whereGreater(aw_g, bw_g, t, f), 1e-12);

        // whereEqual — equality at lanes 1 & 3
        double[] aw_eq = {0, 1, 2, 3};
        double[] bw_eq = {9, 1, 9, 3};
        assertArrayEquals(new double[]{20, 10, 20, 10}, SimdMath.whereEqual(aw_eq, bw_eq, t, f), 1e-12);

        // whereLess — true at lanes 0 & 2
        double[] aw_lt = {1, 9, 3, 9};
        double[] bw_lt = {5, 6, 7, 8};
        assertArrayEquals(new double[]{10, 20, 10, 20}, SimdMath.whereLess(aw_lt, bw_lt, t, f), 1e-12);
    }
}
