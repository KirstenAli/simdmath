package io.github.kirstenali.simdmath.core;

import jdk.incubator.vector.*;

public final class Species {
    private Species() {}
    public static final VectorSpecies<Float>   F = FloatVector.SPECIES_PREFERRED;
    public static final VectorSpecies<Double>  D = DoubleVector.SPECIES_PREFERRED;
    public static final VectorSpecies<Integer> I = IntVector.SPECIES_PREFERRED;
    public static final VectorSpecies<Long>    L = LongVector.SPECIES_PREFERRED;
}
