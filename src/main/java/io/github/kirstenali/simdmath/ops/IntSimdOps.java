package io.github.kirstenali.simdmath.ops;

import jdk.incubator.vector.*;
import io.github.kirstenali.simdmath.core.ArrayUtil;
import io.github.kirstenali.simdmath.core.Species;

public final class IntSimdOps {
    private static final VectorSpecies<Integer> S = Species.I;

    private IntSimdOps() {}

    public static int[] add(int[] a, int[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Integer> m = S.indexInRange(i, a.length);
            IntVector va = IntVector.fromArray(S, a, i, m);
            IntVector vb = IntVector.fromArray(S, b, i, m);
            va.add(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static int[] sub(int[] a, int[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Integer> m = S.indexInRange(i, a.length);
            IntVector va = IntVector.fromArray(S, a, i, m);
            IntVector vb = IntVector.fromArray(S, b, i, m);
            va.sub(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static int[] mul(int[] a, int[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Integer> m = S.indexInRange(i, a.length);
            IntVector va = IntVector.fromArray(S, a, i, m);
            IntVector vb = IntVector.fromArray(S, b, i, m);
            va.mul(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static int[] min(int[] a, int[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Integer> m = S.indexInRange(i, a.length);
            IntVector va = IntVector.fromArray(S, a, i, m);
            IntVector vb = IntVector.fromArray(S, b, i, m);
            va.min(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static int[] max(int[] a, int[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Integer> m = S.indexInRange(i, a.length);
            IntVector va = IntVector.fromArray(S, a, i, m);
            IntVector vb = IntVector.fromArray(S, b, i, m);
            va.max(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static int[] clamp(int[] a, int min, int max) {
        int[] r = new int[a.length];
        IntVector vMin = IntVector.broadcast(S, min);
        IntVector vMax = IntVector.broadcast(S, max);
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Integer> m = S.indexInRange(i, a.length);
            IntVector va = IntVector.fromArray(S, a, i, m);
            va.max(vMin).min(vMax).intoArray(r, i, m);
        }
        return r;
    }

    public static int[] whereGreater(int[] a, int[] b, int[] ifTrue, int[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Integer> m = S.indexInRange(i, a.length);
            IntVector va = IntVector.fromArray(S, a, i, m);
            IntVector vb = IntVector.fromArray(S, b, i, m);
            VectorMask<Integer> cm = va.compare(VectorOperators.GT, vb);
            IntVector vt = IntVector.fromArray(S, ifTrue, i, m);
            IntVector vf = IntVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m);
        }
        return r;
    }

    public static int[] whereEqual(int[] a, int[] b, int[] ifTrue, int[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Integer> m = S.indexInRange(i, a.length);
            IntVector va = IntVector.fromArray(S, a, i, m);
            IntVector vb = IntVector.fromArray(S, b, i, m);
            VectorMask<Integer> cm = va.compare(VectorOperators.EQ, vb);
            IntVector vt = IntVector.fromArray(S, ifTrue, i, m);
            IntVector vf = IntVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m);
        }
        return r;
    }

    public static int[] whereLess(int[] a, int[] b, int[] ifTrue, int[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Integer> m = S.indexInRange(i, a.length);
            IntVector va = IntVector.fromArray(S, a, i, m);
            IntVector vb = IntVector.fromArray(S, b, i, m);
            VectorMask<Integer> cm = va.compare(VectorOperators.LT, vb);
            IntVector vt = IntVector.fromArray(S, ifTrue, i, m);
            IntVector vf = IntVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m);
        }
        return r;
    }

    public static long sum(int[] a) {
        long t = 0L;
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Integer> m = S.indexInRange(i, a.length);
            t += (long) IntVector.fromArray(S, a, i, m).reduceLanes(VectorOperators.ADD, m);
        }
        return t;
    }

    public static long dot(int[] a, int[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        long t = 0L;
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Integer> m = S.indexInRange(i, a.length);
            IntVector va = IntVector.fromArray(S, a, i, m);
            IntVector vb = IntVector.fromArray(S, b, i, m);
            t += (long) va.mul(vb).reduceLanes(VectorOperators.ADD, m);
        }
        return t;
    }
}
