package net.sf.latexdraw.model;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import net.sf.latexdraw.data.DoubleSupplier;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class TestMathUtils {
	@Test
	void testIsValidPointNull() {
		assertFalse(MathUtils.INST.isValidPt(null));
	}

	@ParameterizedTest
	@MethodSource(value = "net.sf.latexdraw.data.DoubleSupplier#badDoubles")
	void testIsValidPointKOX(final double value) {
		assertFalse(MathUtils.INST.isValidPt(ShapeFactory.INST.createPoint(value, 0d)));
	}

	@ParameterizedTest
	@MethodSource(value = "net.sf.latexdraw.data.DoubleSupplier#badDoubles")
	void testIsValidPointKOY(final double value) {
		assertFalse(MathUtils.INST.isValidPt(ShapeFactory.INST.createPoint(0d, value)));
	}

	@ParameterizedTest
	@MethodSource(value = "net.sf.latexdraw.data.DoubleSupplier#twoOkDoubles")
	void testIsValidPoint(final double x, final double y) {
		assertTrue(MathUtils.INST.isValidPt(ShapeFactory.INST.createPoint(x, y)));
	}

	@ParameterizedTest
	@MethodSource(value = "net.sf.latexdraw.data.DoubleSupplier#badDoubles")
	void testIsValidCoordinateKO(final double value) {
		assertFalse(MathUtils.INST.isValidCoord(value));
	}

	@ParameterizedTest
	@MethodSource(value = "net.sf.latexdraw.data.DoubleSupplier#okDoubles")
	void testIsValidCoordinate(final double value) {
		assertTrue(MathUtils.INST.isValidCoord(value));
	}

	@ParameterizedTest
	@MethodSource(value = "net.sf.latexdraw.data.DoubleSupplier#okDoubles")
	void testEquals(final double value) {
		assertThat(MathUtils.INST.equalsDouble(value, 0d, Math.abs(value)), is(true));
		assertThat(MathUtils.INST.equalsDouble(value, value, 0.000000001), is(true));
		assertThat(MathUtils.INST.equalsDouble(value, value + 0.000001, 0.0000001), is(false));
		assertThat(MathUtils.INST.equalsDouble(value, value + 0.000001, 0.00001), is(true));
	}

	@ParameterizedTest
	@MethodSource(value = "net.sf.latexdraw.data.DoubleSupplier#twoOkDoubles")
	void testGetCutNumberNotCut(final double value, final double threshold) {
		assumingThat(Math.abs(threshold) > Math.abs(value), () ->
			assertThat(MathUtils.INST.getCutNumber(value, threshold), closeTo(0d, 0.00001))
		);
	}

	@ParameterizedTest
	@MethodSource(value = "net.sf.latexdraw.data.DoubleSupplier#twoOkDoubles")
	void testGetCutNumberCut(final double value, final double threshold) {
		assumingThat(Math.abs(value) > Math.abs(threshold), () ->
			assertThat(MathUtils.INST.getCutNumber(value, threshold), closeTo(value, 0.00001))
		);
	}

	@TestFactory
	Stream<DynamicTest> testGetCutNumberNotCutFloat() {
		return Stream.of(-0.00001f, -1.34f, -83.12f, 0f, 0.00001f, 1.34f, 83.12f).map(value ->
			DoubleSupplier.okDoubles().filter(threshold -> Math.abs(threshold) > Math.abs((double) value)).
			mapToObj(t -> dynamicTest("testcutNumberNotCutFloat", () -> assertThat(MathUtils.INST.getCutNumber(value, t), is(0f)))
		)).flatMap(s -> s);
	}

	@TestFactory
	Stream<DynamicTest> testGetCutNumberCutFloat() {
		return Stream.of(-0.00001f, -1.34f, -83.12f, 0f, 0.00001f, 1.34f, 83.12f).map(value ->
			DoubleSupplier.okDoubles().filter(threshold -> Math.abs((double) value) > Math.abs(threshold)).
			mapToObj(t -> dynamicTest("testCutNumberCutFloat", () -> assertThat(MathUtils.INST.getCutNumber(value, t), is(value)))
		)).flatMap(s -> s);
	}

	@TestFactory
	Stream<DynamicTest> testMod2pi() {
		return DoubleStream.of(0d, 1.1, 2.3, 3.1).mapToObj(value ->
			DoubleStream.of(Math.PI * 2d, Math.PI * 4d).
				mapToObj(piVal -> dynamicTest("testMod2pi", () -> assertEquals(value, MathUtils.INST.mod2pi(value + piVal), 0.0001))
			)).flatMap(s -> s);
	}

	@TestFactory
	Stream<DynamicTest> testMod2piNegVal() {
		return DoubleStream.of(-0d, -1.1, -2.3, -3.1).mapToObj(value ->
			DoubleStream.of(Math.PI * -2d, Math.PI * -4d).
				mapToObj(piVal -> dynamicTest("testMod2piNegVal", () -> assertEquals(value, MathUtils.INST.mod2pi(value + piVal), 0.0001))
			)).flatMap(s -> s);
	}

	@Test
	void testParserDoubleKO() {
		assertFalse(MathUtils.INST.parserDouble("foo").isPresent());
	}

	@Test
	void testParserDoubleKOInt() {
		assertEquals(1d, MathUtils.INST.parserDouble("1").orElseThrow(), 0.000001);
	}

	@Test
	void testParserDoubleKODouble() {
		assertEquals(-1.2, MathUtils.INST.parserDouble("-1.2").orElseThrow(), 0.000001);
	}
}
