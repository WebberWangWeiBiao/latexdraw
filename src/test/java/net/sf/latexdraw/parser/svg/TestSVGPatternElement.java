package net.sf.latexdraw.parser.svg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSVGPatternElement extends TestBaseSVGElement {
	@Test
	void testContructorFail() {
		assertThrows(IllegalArgumentException.class, () -> new SVGPatternElement(null, null));
	}

	@Test
	void testContructorOK() throws MalformedSVGDocument {
		new SVGPatternElement(node, null);
	}

	@Test
	void testGetHeight0() throws MalformedSVGDocument {
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(0d, e.getHeight(), 0.0001);
	}

	@Test
	void testGetHeight() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_HEIGHT, "20");
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(20d, e.getHeight(), 0.0001);
	}

	@Test
	void testGetWidth0() throws MalformedSVGDocument {
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(0d, e.getWidth(), 0.0001);
	}

	@Test
	void testGetWidth() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_WIDTH, "30");
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(30d, e.getWidth(), 0.0001);
	}

	@Test
	void testGetPatternUnitsdefault() throws MalformedSVGDocument {
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(SVGAttributes.SVG_UNITS_VALUE_OBJ, e.getPatternUnits());
	}

	@Test
	void testGetPatternUnitsUSR() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_PATTERN_UNITS, SVGAttributes.SVG_UNITS_VALUE_USR);
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(SVGAttributes.SVG_UNITS_VALUE_USR, e.getPatternUnits());
	}

	@Test
	void testGetPatternUnitsOBJ() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_PATTERN_UNITS, SVGAttributes.SVG_UNITS_VALUE_OBJ);
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(SVGAttributes.SVG_UNITS_VALUE_OBJ, e.getPatternUnits());
	}

	@Test
	void testGetPatternContentUnitsdefault() throws MalformedSVGDocument {
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(SVGAttributes.SVG_UNITS_VALUE_USR, e.getPatternContentUnits());
	}

	@Test
	void testGetPatternContentUnitsUSR() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_PATTERN_CONTENTS_UNITS, SVGAttributes.SVG_UNITS_VALUE_USR);
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(SVGAttributes.SVG_UNITS_VALUE_USR, e.getPatternContentUnits());
	}

	@Test
	void testGetPatternContentUnitsOBJ() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_PATTERN_CONTENTS_UNITS, SVGAttributes.SVG_UNITS_VALUE_OBJ);
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(SVGAttributes.SVG_UNITS_VALUE_OBJ, e.getPatternContentUnits());
	}

	@Test
	void testEnableRenderingKO00() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_WIDTH, "0");
		node.setAttribute(SVGAttributes.SVG_HEIGHT, "0");
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertFalse(e.enableRendering());
	}

	@Test
	void testEnableRenderingKO100() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_WIDTH, "10");
		node.setAttribute(SVGAttributes.SVG_HEIGHT, "0");
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertFalse(e.enableRendering());
	}

	@Test
	void testEnableRenderingKO010() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_WIDTH, "0");
		node.setAttribute(SVGAttributes.SVG_HEIGHT, "10");
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertFalse(e.enableRendering());
	}

	@Test
	void testEnableRenderingOK() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_WIDTH, "10");
		node.setAttribute(SVGAttributes.SVG_HEIGHT, "10");
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertTrue(e.enableRendering());
	}

	@Test
	void testGetY0() throws MalformedSVGDocument {
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(0d, e.getY(), 0.0001);
	}

	@Test
	void testGetY() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_Y, "1");
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(1d, e.getY(), 0.0001);
	}

	@Test
	void testGetX0() throws MalformedSVGDocument {
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(0d, e.getX(), 0.0001);
	}

	@Test
	void testGetX() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_X, "2");
		final SVGPatternElement e = new SVGPatternElement(node, null);
		assertEquals(2d, e.getX(), 0.0001);
	}

	@Override
	public String getNameNode() {
		return SVGElements.SVG_PATTERN;
	}
}
