package io.github.kirstenali.simdmath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FloatSimdMathTest {

    @Test
    void add_sub_mul_div_min_max_abs_neg_sqrt_clamp_sum_dot_where() {
        float[] a = {1f, 2f, 3f, 4f};
        float[] b = {5f, 6f, 7f, 8f};

        // add / sub / mul / div
        assertArrayEquals(new float[]{6f, 8f, 10f, 12f}, SimdMath.add(a, b), 1e-6f);
        assertArrayEquals(new float[]{-4f, -4f, -4f, -4f}, SimdMath.sub(a, b), 1e-6f);
        assertArrayEquals(new float[]{5f, 12f, 21f, 32f}, SimdMath.mul(a, b), 1e-6f);
        assertArrayEquals(new float[]{1f/5f, 2f/6f, 3f/7f, 4f/8f}, SimdMath.div(a, b), 1e-6f);

        // min / max
        assertArrayEquals(new float[]{1f, 2f, 3f, 4f}, SimdMath.min(a, b), 1e-6f);
        assertArrayEquals(new float[]{5f, 6f, 7f, 8f}, SimdMath.max(a, b), 1e-6f);

        // abs / neg
        float[] neg = SimdMath.neg(a);
        assertArrayEquals(new float[]{-1f, -2f, -3f, -4f}, neg, 1e-6f);
        assertArrayEquals(new float[]{1f, 2f, 3f, 4f}, SimdMath.abs(neg), 1e-6f);

        // sqrt
        float[] squares = {1f, 4f, 9f, 16f};
        assertArrayEquals(new float[]{1f, 2f, 3f, 4f}, SimdMath.sqrt(squares), 1e-6f);

        // clamp
        assertArrayEquals(new float[]{1f, 2f, 3f, 3.5f}, SimdMath.clamp(a, 0.5f, 3.5f), 1e-6f);

        // reductions
        assertEquals(70f, SimdMath.dot(a, b), 1e-5f);
        assertEquals(26f, SimdMath.sum(b), 1e-5f);

        // whereGreater — alternate F, T, F, T
        float[] aw_g = {1f, 7f, 3f, 9f};
        float[] bw_g = {5f, 6f, 7f, 8f};
        float[] t = {10f,10f,10f,10f};
        float[] f = {20f,20f,20f,20f};
        assertArrayEquals(new float[]{20f, 10f, 20f, 10f}, SimdMath.whereGreater(aw_g, bw_g, t, f), 1e-6f);

        // whereEqual — equality at lanes 1 & 3
        float[] aw_eq = {0f, 1f, 2f, 3f};
        float[] bw_eq = {9f, 1f, 9f, 3f};
        assertArrayEquals(new float[]{20f, 10f, 20f, 10f}, SimdMath.whereEqual(aw_eq, bw_eq, t, f), 1e-6f);

        // whereLess — true at lanes 0 & 2
        float[] aw_lt = {1f, 9f, 3f, 9f};
        float[] bw_lt = {5f, 6f, 7f, 8f};
        assertArrayEquals(new float[]{10f, 20f, 10f, 20f}, SimdMath.whereLess(aw_lt, bw_lt, t, f), 1e-6f);
    }
}