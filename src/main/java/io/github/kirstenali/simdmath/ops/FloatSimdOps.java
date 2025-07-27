package io.github.kirstenali.simdmath.ops;

import jdk.incubator.vector.*;
import io.github.kirstenali.simdmath.core.ArrayUtil;
import io.github.kirstenali.simdmath.core.Species;

public final class FloatSimdOps {
    private static final VectorSpecies<Float> S = Species.F;

    private FloatSimdOps() {}

    public static float[] add(float[] a, float[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(S, a, i, m);
            FloatVector vb = FloatVector.fromArray(S, b, i, m);
            va.add(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static float[] sub(float[] a, float[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(S, a, i, m);
            FloatVector vb = FloatVector.fromArray(S, b, i, m);
            va.sub(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static float[] mul(float[] a, float[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(S, a, i, m);
            FloatVector vb = FloatVector.fromArray(S, b, i, m);
            va.mul(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static float[] div(float[] a, float[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(S, a, i, m);
            FloatVector vb = FloatVector.fromArray(S, b, i, m);
            va.div(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static float[] neg(float[] a) {
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector.fromArray(S, a, i, m).neg().intoArray(r, i, m);
        }
        return r;
    }

    public static float[] abs(float[] a) {
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector.fromArray(S, a, i, m).abs().intoArray(r, i, m);
        }
        return r;
    }

    public static float[] sqrt(float[] a) {
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector.fromArray(S, a, i, m).lanewise(VectorOperators.SQRT).intoArray(r, i, m);
        }
        return r;
    }

    public static float[] min(float[] a, float[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(S, a, i, m);
            FloatVector vb = FloatVector.fromArray(S, b, i, m);
            va.min(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static float[] max(float[] a, float[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(S, a, i, m);
            FloatVector vb = FloatVector.fromArray(S, b, i, m);
            va.max(vb).intoArray(r, i, m);
        }
        return r;
    }

    public static float[] clamp(float[] a, float min, float max) {
        float[] r = new float[a.length];
        FloatVector vMin = FloatVector.broadcast(S, min);
        FloatVector vMax = FloatVector.broadcast(S, max);
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(S, a, i, m);
            va.max(vMin).min(vMax).intoArray(r, i, m);
        }
        return r;
    }

    // where*: compare -> mask; select via blend; masked IO for edges
    public static float[] whereGreater(float[] a, float[] b, float[] ifTrue, float[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(S, a, i, m);
            FloatVector vb = FloatVector.fromArray(S, b, i, m);
            VectorMask<Float> cm = va.compare(VectorOperators.GT, vb);
            FloatVector vt = FloatVector.fromArray(S, ifTrue, i, m);
            FloatVector vf = FloatVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m); // pick vt where cm true, else vf
        }
        return r;
    }

    public static float[] whereEqual(float[] a, float[] b, float[] ifTrue, float[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(S, a, i, m);
            FloatVector vb = FloatVector.fromArray(S, b, i, m);
            VectorMask<Float> cm = va.compare(VectorOperators.EQ, vb);
            FloatVector vt = FloatVector.fromArray(S, ifTrue, i, m);
            FloatVector vf = FloatVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m);
        }
        return r;
    }

    public static float[] whereLess(float[] a, float[] b, float[] ifTrue, float[] ifFalse) {
        ArrayUtil.checkSameLength(a.length, b.length);
        ArrayUtil.checkSameLength(a.length, ifTrue.length);
        ArrayUtil.checkSameLength(ifTrue.length, ifFalse.length);
        float[] r = new float[a.length];
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(S, a, i, m);
            FloatVector vb = FloatVector.fromArray(S, b, i, m);
            VectorMask<Float> cm = va.compare(VectorOperators.LT, vb);
            FloatVector vt = FloatVector.fromArray(S, ifTrue, i, m);
            FloatVector vf = FloatVector.fromArray(S, ifFalse, i, m);
            vf.blend(vt, cm).intoArray(r, i, m);
        }
        return r;
    }

    public static float sum(float[] a) {
        float t = 0f;
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            t += FloatVector.fromArray(S, a, i, m).reduceLanes(VectorOperators.ADD, m);
        }
        return t;
    }

    public static float dot(float[] a, float[] b) {
        ArrayUtil.checkSameLength(a.length, b.length);
        float t = 0f;
        for (int i = 0; i < a.length; i += S.length()) {
            VectorMask<Float> m = S.indexInRange(i, a.length);
            FloatVector va = FloatVector.fromArray(S, a, i, m);
            FloatVector vb = FloatVector.fromArray(S, b, i, m);
            t += va.mul(vb).reduceLanes(VectorOperators.ADD, m);
        }
        return t;
    }
}
