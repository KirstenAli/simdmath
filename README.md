# simdmath

Dead‑simple, type‑safe SIMD math for Java using the (incubator) **Vector API**.  
One façade class, `io.github.kirstenali.simdmath.SimdMath`, with method overloads for `float[]`, `double[]`, `int[]`, and `long[]`.

- ✅ Clean, readable API (`SimdMath.add(a, b)`, `SimdMath.whereGreater(a,b,t,f)`, `SimdMath.dot(a,b)`, …)
- ✅ Works for **any array length** (including very small arrays) via a **single masked loop** (no scalar tails)
- ✅ Consistent behavior across types
- ✅ Defensive checks (length mismatches → `IllegalArgumentException`)
- ✅ JUnit 5 tests included

> **JDK requirement:** Java **21+** (or 20/22/23 with the incubator module).  
> You must enable the module: `--add-modules jdk.incubator.vector` (the included `pom.xml` does this for compile & test).

---

## Why another SIMD library?

### Motivation
Writing high‑performance loops with the Vector API takes boilerplate (species, masks, tails). This library:
- **Hides the boilerplate** behind a compact API,
- **Picks the right species** (`SPECIES_PREFERRED`), and
- Uses the **masked‑loop pattern** so code works for *any* array length without scalar “tail” paths.

### Benefits
- **Performance**: The JVM maps operations to hardware SIMD (AVX/AVX‑512 on x86, SVE/NEON on Arm) where available.
- **Predictability**: You explicitly use the Vector API (no reliance on the JIT’s auto‑vectorization heuristics).
- **Portability**: Same Java code runs across CPUs; the species adapts to the widest sensible vector size.

> Real speedups depend on data size, memory bandwidth, CPU, and operation mix. This library gives the JVM what it needs to vectorize the hot loop deterministically.

---