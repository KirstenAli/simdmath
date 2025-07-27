package io.github.kirstenali.simdmath.ops;

import jdk.incubator.vector.*;
import io.github.kirstenali.simdmath.core.ArrayUtil;
import io.github.kirstenali.simdmath.core.Species;

public final class DoubleSimdOps {
    private static final VectorSpecies<Double> S = Species.D;

    private DoubleSimdOps() {}

    public static double[] add(double[] a, double[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        double[] r = new double[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector va = DoubleVector.fromArray(S, a, i, m);
            DoubleVector vb = DoubleVector.fromArray(S, b, i, m);
            va.add(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static double[] sub(double[] a, double[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        double[] r = new double[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector va = DoubleVector.fromArray(S, a, i, m);
            DoubleVector vb = DoubleVector.fromArray(S, b, i, m);
            va.sub(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static double[] mul(double[] a, double[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        double[] r = new double[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector va = DoubleVector.fromArray(S, a, i, m);
            DoubleVector vb = DoubleVector.fromArray(S, b, i, m);
            va.mul(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static double[] div(double[] a, double[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        double[] r = new double[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector va = DoubleVector.fromArray(S, a, i, m);
            DoubleVector vb = DoubleVector.fromArray(S, b, i, m);
            va.div(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static double[] sqrt(double[] a) {
        double[] r = new double[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector.fromArray(S, a, i, m).lanewise(VectorOperators.SQRT).intoArray(r, i, m);
        }
        return r;
    }

    public static double[] min(double[] a, double[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        double[] r = new double[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector va = DoubleVector.fromArray(S, a, i, m);
            DoubleVector vb = DoubleVector.fromArray(S, b, i, m);
            va.min(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static double[] max(double[] a, double[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        double[] r = new double[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector va = DoubleVector.fromArray(S, a, i, m);
            DoubleVector vb = DoubleVector.fromArray(S, b, i, m);
            va.max(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static double[] clamp(double[] a, double min, double max) {
        double[] r = new double[a.length];
        DoubleVector vMin = DoubleVector.broadcast(S, min);
        DoubleVector vMax = DoubleVector.broadcast(S, max);
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector va = DoubleVector.fromArray(S, a, i, m);
            va.max(vMin).min(vMax).intoArray(r, i, m);
        }
        return r;
    }

    public static double[] whereGreater(double[] a, double[] b, double[] ifTrue, double[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        double[] r = new double[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector va = DoubleVector.fromArray(S, a, i, m);
            DoubleVector vb = DoubleVector.fromArray(S, b, i, m);
            VectorMask<Double> cm = va.compare(VectorOperators.GT, vb);
            DoubleVector vt = DoubleVector.fromArray(S, ifTrue, i, m);
            DoubleVector vf = DoubleVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m);
        }
        return r;
    }

    public static double[] whereEqual(double[] a, double[] b, double[] ifTrue, double[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        double[] r = new double[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector va = DoubleVector.fromArray(S, a, i, m);
            DoubleVector vb = DoubleVector.fromArray(S, b, i, m);
            VectorMask<Double> cm = va.compare(VectorOperators.EQ, vb);
            DoubleVector vt = DoubleVector.fromArray(S, ifTrue, i, m);
            DoubleVector vf = DoubleVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m);
        }
        return r;
    }

    public static double[] whereLess(double[] a, double[] b, double[] ifTrue, double[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        double[] r = new double[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector va = DoubleVector.fromArray(S, a, i, m);
            DoubleVector vb = DoubleVector.fromArray(S, b, i, m);
            VectorMask<Double> cm = va.compare(VectorOperators.LT, vb);
            DoubleVector vt = DoubleVector.fromArray(S, ifTrue, i, m);
            DoubleVector vf = DoubleVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m);
        }
        return r;
    }

    public static double sum(double[] a) {
        double t = 0d;
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            t += DoubleVector.fromArray(S, a, i, m).reduceLanes(VectorOperators.ADD, m);
        }
        return t;
    }

    public static double dot(double[] a, double[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        double t = 0d;
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Double> m = S.indexInRange(i, a.length);
            DoubleVector va = DoubleVector.fromArray(S, a, i, m);
            DoubleVector vb = DoubleVector.fromArray(S, b, i, m);
            t += va.mul(vb).reduceLanes(VectorOperators.ADD, m);
        }
        return t;
    }
}
