package net.sf.latexdraw.parser.svg;

import net.sf.latexdraw.parser.svg.parsers.SVGLength;
import net.sf.latexdraw.parser.svg.parsers.UnitProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSVGEllipseElement extends TestBaseSVGElement {
	@Test
	void testContructorNULLNULL() {
		assertThrows(IllegalArgumentException.class, () -> new SVGEllipseElement(null, null));
	}

	@Test
	void testContructorNodeNULL() {
		assertThrows(MalformedSVGDocument.class, () -> new SVGEllipseElement(node, null));
	}

	@Test
	void testContructorNodeInvInvNULL() {
		node.setAttribute(SVGAttributes.SVG_RX, "dsd");
		node.setAttribute(SVGAttributes.SVG_RY, "dsd");
		assertThrows(MalformedSVGDocument.class, () -> new SVGEllipseElement(node, null));
	}

	@Test
	void testContructorNodeInvNULL() {
		node.setAttribute(SVGAttributes.SVG_RX, "1");
		assertThrows(MalformedSVGDocument.class, () -> new SVGEllipseElement(node, null));
	}

	@Test
	void testContructorNode2NULL() {
		node.setAttribute(SVGAttributes.SVG_RX, "-1");
		node.setAttribute(SVGAttributes.SVG_RY, "10");
		assertThrows(MalformedSVGDocument.class, () -> new SVGEllipseElement(node, null));
	}

	@Test
	void testContructorNode3NULL() {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "-1");
		assertThrows(MalformedSVGDocument.class, () -> new SVGEllipseElement(node, null));
	}

	@Test
	void testContructorNodeOK() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		new SVGEllipseElement(node, null);
	}

	@Test
	void testGetCyDefault() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(0d, ell.getCy(), 0.0001);
	}

	@Test
	void testGetCyVal() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		node.setAttribute(SVGAttributes.SVG_CY, "40");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(40d, ell.getCy(), 0.0001);
	}

	@Test
	void testGetCyValPX() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		node.setAttribute(SVGAttributes.SVG_CY, "40px");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(40d, ell.getCy(), 0.0001);
	}

	@Test
	void testGetCyValCM() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		node.setAttribute(SVGAttributes.SVG_CY, "40 cm");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(UnitProcessor.INSTANCE.toUserUnit(40, SVGLength.LengthType.CM), ell.getCy(), 0.0001);
	}

	@Test
	void testGetCxDefault() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(0d, ell.getCx(), 0.0001);
	}

	@Test
	void testGetCxVal() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		node.setAttribute(SVGAttributes.SVG_CX, "30");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(30d, ell.getCx(), 0.0001);
	}

	@Test
	void testGetCxValPX() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		node.setAttribute(SVGAttributes.SVG_CX, "40px");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(40d, ell.getCx(), 0.0001);
	}

	@Test
	void testGetCxValCM() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		node.setAttribute(SVGAttributes.SVG_CX, "40 cm");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(UnitProcessor.INSTANCE.toUserUnit(40, SVGLength.LengthType.CM), ell.getCx(), 0.0001);
	}

	@ParameterizedTest
	@CsvSource(value = {"0,10", "10,0", "0,0"})
	void testEnableRenderingKO(final String v1, final String v2) throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, v1);
		node.setAttribute(SVGAttributes.SVG_RY, v2);
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertFalse(ell.enableRendering());
	}

	@Test
	void testEnableRendering() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "10");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertTrue(ell.enableRendering());
	}

	@Test
	void testGetRy() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(20d, ell.getRy(), 0.0001);
	}

	@Test
	void testGetRyValDim() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		node.setAttribute(SVGAttributes.SVG_RY, "20 pt");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(UnitProcessor.INSTANCE.toUserUnit(20, SVGLength.LengthType.PT), ell.getRy(), 0.0001);
	}

	@Test
	void testGetRx() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(10d, ell.getRx(), 0.0001);
	}

	@Test
	void testGetRxValDim() throws MalformedSVGDocument {
		node.setAttribute(SVGAttributes.SVG_RX, "10");
		node.setAttribute(SVGAttributes.SVG_RY, "20");
		node.setAttribute(SVGAttributes.SVG_RX, "10mm");
		final SVGEllipseElement ell = new SVGEllipseElement(node, null);
		assertEquals(UnitProcessor.INSTANCE.toUserUnit(10, SVGLength.LengthType.MM), ell.getRx(), 0.0001);
	}

	@Override
	public String getNameNode() {
		return SVGElements.SVG_ELLIPSE;
	}
}
