package io.github.kirstenali.simdmath;

import io.github.kirstenali.simdmath.ops.*;

public final class SimdMath {
    private SimdMath() {}

    // add
    public static float[]  add(float[] a, float[] b)   { return FloatSimdOps.add(a, b); }
    public static double[] add(double[] a, double[] b) { return DoubleSimdOps.add(a, b); }
    public static int[]    add(int[] a, int[] b)       { return IntSimdOps.add(a, b); }
    public static long[]   add(long[] a, long[] b)     { return LongSimdOps.add(a, b); }

    // sub
    public static float[]  sub(float[] a, float[] b)   { return FloatSimdOps.sub(a, b); }
    public static double[] sub(double[] a, double[] b) { return DoubleSimdOps.sub(a, b); }
    public static int[]    sub(int[] a, int[] b)       { return IntSimdOps.sub(a, b); }
    public static long[]   sub(long[] a, long[] b)     { return LongSimdOps.sub(a, b); }

    // mul
    public static float[]  mul(float[] a, float[] b)   { return FloatSimdOps.mul(a, b); }
    public static double[] mul(double[] a, double[] b) { return DoubleSimdOps.mul(a, b); }
    public static int[]    mul(int[] a, int[] b)       { return IntSimdOps.mul(a, b); }
    public static long[]   mul(long[] a, long[] b)     { return LongSimdOps.mul(a, b); }

    // div (float & double)
    public static float[]  div(float[] a, float[] b)   { return FloatSimdOps.div(a, b); }
    public static double[] div(double[] a, double[] b) { return DoubleSimdOps.div(a, b); }

    // sqrt (float & double)
    public static float[]  sqrt(float[] a)  { return FloatSimdOps.sqrt(a); }
    public static double[] sqrt(double[] a) { return DoubleSimdOps.sqrt(a); }

    // abs / neg (float only)
    public static float[]  abs(float[] a)   { return FloatSimdOps.abs(a); }
    public static float[]  neg(float[] a)   { return FloatSimdOps.neg(a); }

    // min
    public static float[]  min(float[] a, float[] b)   { return FloatSimdOps.min(a, b); }
    public static double[] min(double[] a, double[] b) { return DoubleSimdOps.min(a, b); }
    public static int[]    min(int[] a, int[] b)       { return IntSimdOps.min(a, b); }
    public static long[]   min(long[] a, long[] b)     { return LongSimdOps.min(a, b); }

    // max
    public static float[]  max(float[] a, float[] b)   { return FloatSimdOps.max(a, b); }
    public static double[] max(double[] a, double[] b) { return DoubleSimdOps.max(a, b); }
    public static int[]    max(int[] a, int[] b)       { return IntSimdOps.max(a, b); }
    public static long[]   max(long[] a, long[] b)     { return LongSimdOps.max(a, b); }

    // clamp
    public static float[]  clamp(float[] a, float min, float max)      { return FloatSimdOps.clamp(a, min, max); }
    public static double[] clamp(double[] a, double min, double max)   { return DoubleSimdOps.clamp(a, min, max); }
    public static int[]    clamp(int[] a, int min, int max)            { return IntSimdOps.clamp(a, min, max); }
    public static long[]   clamp(long[] a, long min, long max)         { return LongSimdOps.clamp(a, min, max); }

    // whereGreater
    public static float[]  whereGreater(float[] a, float[] b, float[] t, float[] f)     { return FloatSimdOps.whereGreater(a, b, t, f); }
    public static double[] whereGreater(double[] a, double[] b, double[] t, double[] f) { return DoubleSimdOps.whereGreater(a, b, t, f); }
    public static int[]    whereGreater(int[] a, int[] b, int[] t, int[] f)             { return IntSimdOps.whereGreater(a, b, t, f); }
    public static long[]   whereGreater(long[] a, long[] b, long[] t, long[] f)         { return LongSimdOps.whereGreater(a, b, t, f); }

    // whereEqual
    public static float[]  whereEqual(float[] a, float[] b, float[] t, float[] f)     { return FloatSimdOps.whereEqual(a, b, t, f); }
    public static double[] whereEqual(double[] a, double[] b, double[] t, double[] f) { return DoubleSimdOps.whereEqual(a, b, t, f); }
    public static int[]    whereEqual(int[] a, int[] b, int[] t, int[] f)             { return IntSimdOps.whereEqual(a, b, t, f); }
    public static long[]   whereEqual(long[] a, long[] b, long[] t, long[] f)         { return LongSimdOps.whereEqual(a, b, t, f); }

    // whereLess
    public static float[]  whereLess(float[] a, float[] b, float[] t, float[] f)      { return FloatSimdOps.whereLess(a, b, t, f); }
    public static double[] whereLess(double[] a, double[] b, double[] t, double[] f)  { return DoubleSimdOps.whereLess(a, b, t, f); }
    public static int[]    whereLess(int[] a, int[] b, int[] t, int[] f)              { return IntSimdOps.whereLess(a, b, t, f); }
    public static long[]   whereLess(long[] a, long[] b, long[] t, long[] f)          { return LongSimdOps.whereLess(a, b, t, f); }

    // reductions
    public static float  sum(float[] a)  { return FloatSimdOps.sum(a); }
    public static double sum(double[] a) { return DoubleSimdOps.sum(a); }
    public static long   sum(int[] a)    { return IntSimdOps.sum(a); }
    public static long   sum(long[] a)   { return LongSimdOps.sum(a); }

    public static float  dot(float[] a, float[] b)   { return FloatSimdOps.dot(a, b); }
    public static double dot(double[] a, double[] b) { return DoubleSimdOps.dot(a, b); }
    public static long   dot(int[] a, int[] b)       { return IntSimdOps.dot(a, b); }
    public static long   dot(long[] a, long[] b)     { return LongSimdOps.dot(a, b); }
}
