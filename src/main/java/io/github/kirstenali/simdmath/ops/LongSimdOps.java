package io.github.kirstenali.simdmath.ops;

import jdk.incubator.vector.*;
import io.github.kirstenali.simdmath.core.ArrayUtil;
import io.github.kirstenali.simdmath.core.Species;

public final class LongSimdOps {
    private static final VectorSpecies<Long> S = Species.L;

    private LongSimdOps() {}

    public static long[] add(long[] a, long[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        long[] r = new long[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Long> m = S.indexInRange(i, a.length);
            LongVector va = LongVector.fromArray(S, a, i, m);
            LongVector vb = LongVector.fromArray(S, b, i, m);
            va.add(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static long[] sub(long[] a, long[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        long[] r = new long[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Long> m = S.indexInRange(i, a.length);
            LongVector va = LongVector.fromArray(S, a, i, m);
            LongVector vb = LongVector.fromArray(S, b, i, m);
            va.sub(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static long[] mul(long[] a, long[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        long[] r = new long[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Long> m = S.indexInRange(i, a.length);
            LongVector va = LongVector.fromArray(S, a, i, m);
            LongVector vb = LongVector.fromArray(S, b, i, m);
            va.mul(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static long[] min(long[] a, long[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        long[] r = new long[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Long> m = S.indexInRange(i, a.length);
            LongVector va = LongVector.fromArray(S, a, i, m);
            LongVector vb = LongVector.fromArray(S, b, i, m);
            va.min(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static long[] max(long[] a, long[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        long[] r = new long[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Long> m = S.indexInRange(i, a.length);
            LongVector va = LongVector.fromArray(S, a, i, m);
            LongVector vb = LongVector.fromArray(S, b, i, m);
            va.max(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static long[] clamp(long[] a, long min, long max) {
        long[] r = new long[a.length];
        LongVector vMin = LongVector.broadcast(S, min);
        LongVector vMax = LongVector.broadcast(S, max);
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Long> m = S.indexInRange(i, a.length);
            LongVector va = LongVector.fromArray(S, a, i, m);
            va.max(vMin).min(vMax).intoArray(r, i, m);
        }
        return r;
    }

    public static long[] whereGreater(long[] a, long[] b, long[] ifTrue, long[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        long[] r = new long[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Long> m = S.indexInRange(i, a.length);
            LongVector va = LongVector.fromArray(S, a, i, m);
            LongVector vb = LongVector.fromArray(S, b, i, m);
            VectorMask<Long> cm = va.compare(VectorOperators.GT, vb);
            LongVector vt = LongVector.fromArray(S, ifTrue, i, m);
            LongVector vf = LongVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m);
        }
        return r;
    }

    public static long[] whereEqual(long[] a, long[] b, long[] ifTrue, long[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        long[] r = new long[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Long> m = S.indexInRange(i, a.length);
            LongVector va = LongVector.fromArray(S, a, i, m);
            LongVector vb = LongVector.fromArray(S, b, i, m);
            VectorMask<Long> cm = va.compare(VectorOperators.EQ, vb);
            LongVector vt = LongVector.fromArray(S, ifTrue, i, m);
            LongVector vf = LongVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m);
        }
        return r;
    }

    public static long[] whereLess(long[] a, long[] b, long[] ifTrue, long[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        long[] r = new long[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Long> m = S.indexInRange(i, a.length);
            LongVector va = LongVector.fromArray(S, a, i, m);
            LongVector vb = LongVector.fromArray(S, b, i, m);
            VectorMask<Long> cm = va.compare(VectorOperators.LT, vb);
            LongVector vt = LongVector.fromArray(S, ifTrue, i, m);
            LongVector vf = LongVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m);
        }
        return r;
    }

    public static long sum(long[] a) {
        long t = 0L;
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Long> m = S.indexInRange(i, a.length);
            t += LongVector.fromArray(S, a, i, m).reduceLanes(VectorOperators.ADD, m);
        }
        return t;
    }

    public static long dot(long[] a, long[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        long t = 0L;
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Long> m = S.indexInRange(i, a.length);
            LongVector va = LongVector.fromArray(S, a, i, m);
            LongVector vb = LongVector.fromArray(S, b, i, m);
            t += va.mul(vb).reduceLanes(VectorOperators.ADD, m);
        }
        return t;
    }
}
