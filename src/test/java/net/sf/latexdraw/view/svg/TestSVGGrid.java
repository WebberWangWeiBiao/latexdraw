package net.sf.latexdraw.view.svg;

import net.sf.latexdraw.model.CompareShapeMatcher;
import net.sf.latexdraw.model.api.shape.Grid;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestSVGGrid extends TestSVGBase<Grid> {
	@ParameterizedTest
	@MethodSource("net.sf.latexdraw.data.ShapeSupplier#createDiversifiedGrid")
	void testGridParams(final Grid sh) {
		final Grid s2 = produceOutputShapeFrom(sh);
		CompareShapeMatcher.INST.assertEqualsGrid(sh, s2);
	}
}
