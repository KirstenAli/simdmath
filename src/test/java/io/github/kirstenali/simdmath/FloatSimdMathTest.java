package io.github.kirstenali.simdmath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FloatSimdMathTest {
    public static final float DELTA = 1e-6f;

    @Test
    void add_sub_mul_div_min_max_abs_neg_sqrt_clamp_sum_dot_where() {
        float[] a = {1f, 2f, 3f, 4f};
        float[] b = {5f, 6f, 7f, 8f};

        // add / sub / mul / div
        assertArrayEquals(new float[]{6f, 8f, 10f, 12f}, SimdMath.add(a, b), DELTA);
        assertArrayEquals(new float[]{-4f, -4f, -4f, -4f}, SimdMath.sub(a, b), DELTA);
        assertArrayEquals(new float[]{5f, 12f, 21f, 32f}, SimdMath.mul(a, b), DELTA);
        assertArrayEquals(new float[]{1f/5f, 2f/6f, 3f/7f, 4f/8f}, SimdMath.div(a, b), DELTA);

        // min / max
        assertArrayEquals(new float[]{1f, 2f, 3f, 4f}, SimdMath.min(a, b), DELTA);
        assertArrayEquals(new float[]{5f, 6f, 7f, 8f}, SimdMath.max(a, b), DELTA);

        // abs / neg
        float[] neg = SimdMath.neg(a);
        assertArrayEquals(new float[]{-1f, -2f, -3f, -4f}, neg, DELTA);
        assertArrayEquals(new float[]{1f, 2f, 3f, 4f}, SimdMath.abs(neg), DELTA);

        // sqrt
        float[] squares = {1f, 4f, 9f, 16f};
        assertArrayEquals(new float[]{1f, 2f, 3f, 4f}, SimdMath.sqrt(squares), DELTA);

        // clamp
        assertArrayEquals(new float[]{1f, 2f, 3f, 3.5f}, SimdMath.clamp(a, 0.5f, 3.5f), DELTA);

        // reductions
        assertEquals(70f, SimdMath.dot(a, b), DELTA);
        assertEquals(26f, SimdMath.sum(b), DELTA);

        // whereGreater — alternate F, T, F, T
        float[] aw_g = {1f, 7f, 3f, 9f};
        float[] bw_g = {5f, 6f, 7f, 8f};
        float[] t = {10f,10f,10f,10f};
        float[] f = {20f,20f,20f,20f};
        assertArrayEquals(new float[]{20f, 10f, 20f, 10f}, SimdMath.whereGreater(aw_g, bw_g, t, f), DELTA);

        // whereEqual — equality at lanes 1 & 3
        float[] aw_eq = {0f, 1f, 2f, 3f};
        float[] bw_eq = {9f, 1f, 9f, 3f};
        assertArrayEquals(new float[]{20f, 10f, 20f, 10f}, SimdMath.whereEqual(aw_eq, bw_eq, t, f), DELTA);

        // whereLess — true at lanes 0 & 2
        float[] aw_lt = {1f, 9f, 3f, 9f};
        float[] bw_lt = {5f, 6f, 7f, 8f};
        assertArrayEquals(new float[]{10f, 20f, 10f, 20f}, SimdMath.whereLess(aw_lt, bw_lt, t, f), DELTA);
    }
}