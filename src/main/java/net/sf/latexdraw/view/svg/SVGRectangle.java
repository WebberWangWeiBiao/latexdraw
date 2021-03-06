/*
 * This file is part of LaTeXDraw.
 * Copyright (c) 2005-2018 Arnaud BLOUIN
 * LaTeXDraw is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 * LaTeXDraw is distributed without any warranty; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 */
package net.sf.latexdraw.view.svg;

import net.sf.latexdraw.model.ShapeFactory;
import net.sf.latexdraw.model.api.shape.Point;
import net.sf.latexdraw.model.api.shape.Rectangle;
import net.sf.latexdraw.parser.svg.SVGAttributes;
import net.sf.latexdraw.parser.svg.SVGDocument;
import net.sf.latexdraw.parser.svg.SVGElement;
import net.sf.latexdraw.parser.svg.SVGGElement;
import net.sf.latexdraw.parser.svg.SVGRectElement;
import net.sf.latexdraw.util.LNamespace;
import net.sf.latexdraw.view.pst.PSTricksConstants;

/**
 * SVG/latexdraw rectangle import export.
 * @author Arnaud BLOUIN
 */
class SVGRectangle extends SVGRectangular<Rectangle> {
	/**
	 * Creates a generator of SVG rectangle.
	 * @param rect The rectangle shape used for the generation.
	 * @throws IllegalArgumentException If rect is null.
	 */
	SVGRectangle(final Rectangle rect) {
		super(rect);
	}


	/**
	 * Creates a rectangle from an SVG rect element.
	 * @param elt The source element.
	 * @throws IllegalArgumentException If the given element is null.
	 */
	SVGRectangle(final SVGRectElement elt) {
		this(ShapeFactory.INST.createRectangle());

		setSVGRectParameters(elt);
		applyTransformations(elt);
	}


	/**
	 * Creates a rectangle from a latexdraw-SVG element.
	 * @param elt The source element.
	 * @throws IllegalArgumentException If the given element is null or not valid.
	 */
	SVGRectangle(final SVGGElement elt, final boolean withTransformation) {
		this(ShapeFactory.INST.createRectangle());
		initRectangle(elt, withTransformation);
	}


	@Override
	final void setSVGRectParameters(final SVGRectElement elt) {
		if(elt == null) {
			return;
		}

		setSVGParameters(elt);

		final double gap = getPositionGap();

		shape.setPosition(elt.getX() + gap / 2d, elt.getY() + elt.getHeight() - gap / 2d);
		shape.setWidth(elt.getWidth() - gap);
		shape.setHeight(elt.getHeight() - gap);
		setLineArc(elt.getRx());
	}


	@Override
	SVGElement toSVG(final SVGDocument document) {
		if(document == null || document.getFirstChild().getDefs() == null) {
			throw new IllegalArgumentException();
		}

		final double gap = getPositionGap();
		final Point tl = shape.getTopLeftPoint();
		final Point br = shape.getBottomRightPoint();
		SVGElement elt;
		final SVGElement root = new SVGGElement(document);
		final double width = Math.max(1d, br.getX() - tl.getX() + gap);
		final double height = Math.max(1d, br.getY() - tl.getY() + gap);
		final double x = tl.getX() - gap / 2d;
		final double y = tl.getY() - gap / 2d;

		root.setAttribute(LNamespace.LATEXDRAW_NAMESPACE + ':' + LNamespace.XML_TYPE, LNamespace.XML_TYPE_RECT);
		root.setAttribute(SVGAttributes.SVG_ID, getSVGID());

		setShadowSVGRect(root, x, y, width, height, document);

		if(shape.hasShadow() && !PSTricksConstants.LINE_NONE_STYLE.equals(shape.getLineStyle().getLatexToken())) {
			// The background of the borders must be filled is there is a shadow.
			elt = new SVGRectElement(x, y, width, height, document);
			setSVGBorderBackground(elt, root);
			setSVGRoundCorner(elt);
		}

		elt = new SVGRectElement(x, y, width, height, document);
		root.appendChild(elt);
		setSVGAttributes(document, elt, true);
		setSVGRoundCorner(elt);
		setDbleBordSVGRect(root, x, y, width, height, document);
		setSVGRotationAttribute(root);

		return root;
	}
}
