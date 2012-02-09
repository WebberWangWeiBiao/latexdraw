package net.sf.latexdraw.instruments;

import java.awt.Dimension;
import java.awt.ItemSelectable;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import net.sf.latexdraw.actions.ModifyPencilParameter;
import net.sf.latexdraw.actions.ModifyShapeProperty;
import net.sf.latexdraw.actions.ShapeProperties;
import net.sf.latexdraw.badaboom.BadaboomCollector;
import net.sf.latexdraw.glib.models.interfaces.IArrow;
import net.sf.latexdraw.glib.models.interfaces.IArrow.ArrowStyle;
import net.sf.latexdraw.glib.models.interfaces.IShape;
import net.sf.latexdraw.lang.LangTool;
import net.sf.latexdraw.ui.LabelListCellRenderer;
import net.sf.latexdraw.util.LResources;

import org.malai.ui.UIComposer;
import org.malai.widget.MComboBox;
import org.malai.widget.MSpinner;

/**
 * This instrument customises the arrows of shapes or of the pencil.<br>
 * <br>
 * This file is part of LaTeXDraw.<br>
 * Copyright (c) 2005-2012 Arnaud BLOUIN<br>
 * <br>
 * LaTeXDraw is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 * <br>
 * LaTeXDraw is distributed without any warranty; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.<br>
 * <br>
 * 08/05/2011<br>
 * @author Arnaud BLOUIN
 * @since 3.0
 */
public class ShapeArrowCustomiser extends ShapePropertyCustomiser {
	/** Allows to change the style of the left-end of the shape. */
	protected MComboBox arrowLeftCB;

	/** Allows to change the style of the right-end of the shape. */
	protected MComboBox arrowRightCB;

	protected MSpinner dotSizeNum;

	protected MSpinner dotSizeDim;

	protected MSpinner bracketNum;

	protected MSpinner rbracketNum;

	protected MSpinner tbarsizeNum;

	protected MSpinner tbarsizeDim;

	protected MSpinner arrowSizeDim;

	protected MSpinner arrowSizeNum;

	protected MSpinner arrowLength;

	protected MSpinner arrowInset;


	/**
	 * Creates the instrument.
	 * @param composer The composer that manages the widgets of the instrument.
	 * @param hand The Hand instrument.
	 * @param pencil The Pencil instrument.
	 * @throws IllegalArgumentException If one of the given argument is null or if the drawing cannot
	 * be accessed from the hand.
	 * @since 3.0
	 */
	public ShapeArrowCustomiser(final UIComposer<?> composer, final Hand hand, final Pencil pencil) {
		super(composer, hand, pencil);
		initialiseWidgets();
	}


	@Override
	protected void initialiseWidgets() {
     	arrowLeftCB = createLeftArrowStyleList();
     	arrowLeftCB.setPreferredSize(new Dimension(80,30));
     	arrowLeftCB.setMaximumSize(new Dimension(80,30));

     	arrowRightCB = createRightArrowStyleList();
     	arrowRightCB.setPreferredSize(new Dimension(80,30));
     	arrowRightCB.setMaximumSize(new Dimension(80,30));

     	SpinnerNumberModel model = new SpinnerNumberModel(10., 0., 1000., 1.);
     	dotSizeDim = new MSpinner(model, new JLabel(LangTool.INSTANCE.getStringDialogFrame("AbstractParametersFrame.24")));
     	dotSizeDim.setEditor(new JSpinner.NumberEditor(dotSizeDim, "0.00"));//$NON-NLS-1$

     	arrowSizeDim = new MSpinner(model, new JLabel(LangTool.INSTANCE.getStringDialogFrame("AbstractParametersFrame.24")));
     	arrowSizeDim.setEditor(new JSpinner.NumberEditor(arrowSizeDim, "0.00"));//$NON-NLS-1$

     	tbarsizeDim = new MSpinner(model, new JLabel(LangTool.INSTANCE.getStringDialogFrame("AbstractParametersFrame.24")));
     	tbarsizeDim.setEditor(new JSpinner.NumberEditor(tbarsizeDim, "0.00"));//$NON-NLS-1$

     	model = new SpinnerNumberModel(10., 0.1, 100., 0.1);
     	dotSizeNum = new MSpinner(model, new JLabel(LangTool.INSTANCE.getStringDialogFrame("AbstractParametersFrame.20")));
     	dotSizeNum.setEditor(new JSpinner.NumberEditor(dotSizeNum, "0.00"));//$NON-NLS-1$

     	tbarsizeNum = new MSpinner(model, new JLabel(LangTool.INSTANCE.getStringDialogFrame("AbstractParametersFrame.21")));
     	tbarsizeNum.setEditor(new JSpinner.NumberEditor(tbarsizeNum, "0.00"));//$NON-NLS-1$

     	model = new SpinnerNumberModel(10., 0.1, 100., 0.01);
     	bracketNum = new MSpinner(model, new JLabel(LangTool.INSTANCE.getStringDialogFrame("AbstractParametersFrame.22")));
     	bracketNum.setEditor(new JSpinner.NumberEditor(bracketNum, "0.00"));//$NON-NLS-1$

     	rbracketNum = new MSpinner(model, new JLabel(LangTool.INSTANCE.getStringDialogFrame("AbstractParametersFrame.23")));
     	rbracketNum.setEditor(new JSpinner.NumberEditor(rbracketNum, "0.00"));//$NON-NLS-1$

     	arrowSizeNum = new MSpinner(model, new JLabel(LangTool.INSTANCE.getStringDialogFrame("AbstractParametersFrame.28")));
     	arrowSizeNum.setEditor(new JSpinner.NumberEditor(arrowSizeNum, "0.00"));//$NON-NLS-1$

     	model = new SpinnerNumberModel(10., 0., 100., 0.01);
     	arrowLength = new MSpinner(model, new JLabel(LangTool.INSTANCE.getStringDialogFrame("AbstractParametersFrame.26")));
     	arrowLength.setEditor(new JSpinner.NumberEditor(arrowLength, "0.00"));//$NON-NLS-1$

     	model = new SpinnerNumberModel(0., 0., 100., 0.01);
     	arrowInset = new MSpinner(model, new JLabel(LangTool.INSTANCE.getStringDialogFrame("AbstractParametersFrame.27")));
     	arrowInset.setEditor(new JSpinner.NumberEditor(arrowInset, "0.00"));//$NON-NLS-1$
	}


	/**
	 * Creates a list of the different styles of arrowhead (right).
	 * @return The created list.
	 */
	public static MComboBox createRightArrowStyleList() {
		final MComboBox lineArrowRChoice = new MComboBox();

		lineArrowRChoice.setRenderer(new LabelListCellRenderer());
		JLabel label = new JLabel(ArrowStyle.NONE.name());
		label.setIcon(LResources.ARROW_STYLE_NONE_R_ICON);
		lineArrowRChoice.addItem(label);
     	label = new JLabel(ArrowStyle.BAR_IN.name());
     	label.setIcon(LResources.ARROW_STYLE_BAR_IN_R_ICON);
     	lineArrowRChoice.addItem(label);
		label = new JLabel(ArrowStyle.BAR_END.name());
     	label.setIcon(LResources.ARROW_STYLE_BAR_END_R_ICON);
     	lineArrowRChoice.addItem(label);
     	label = new JLabel(ArrowStyle.CIRCLE_END.name());
     	label.setIcon(LResources.ARROW_STYLE_CIRCLE_END_R_ICON);
     	lineArrowRChoice.addItem(label);
     	label = new JLabel(ArrowStyle.CIRCLE_IN.name());
     	label.setIcon(LResources.ARROW_STYLE_CIRCLE_IN_R_ICON);
     	lineArrowRChoice.addItem(label);
     	label = new JLabel(ArrowStyle.DISK_END.name());
     	label.setIcon(LResources.ARROW_STYLE_DISK_END_R_ICON);
     	lineArrowRChoice.addItem(label);
     	label = new JLabel(ArrowStyle.DISK_IN.name());
     	label.setIcon(LResources.ARROW_STYLE_DISK_IN_R_ICON);
     	lineArrowRChoice.addItem(label);
		label = new JLabel(ArrowStyle.RIGHT_ARROW.name());
     	label.setIcon(LResources.ARROW_STYLE_ARROW_R_ICON);
     	lineArrowRChoice.addItem(label);
		label = new JLabel(ArrowStyle.LEFT_ARROW.name());
     	label.setIcon(LResources.ARROW_STYLE_R_ARROW_R_ICON);
     	lineArrowRChoice.addItem(label);
		label = new JLabel(ArrowStyle.RIGHT_ROUND_BRACKET.name());
     	label.setIcon(LResources.ARROW_STYLE_ARC_R_ICON);
     	lineArrowRChoice.addItem(label);
		label = new JLabel(ArrowStyle.LEFT_ROUND_BRACKET.name());
     	label.setIcon(LResources.ARROW_STYLE_ARC_RR_ICON);
     	lineArrowRChoice.addItem(label);
		label = new JLabel(ArrowStyle.RIGHT_SQUARE_BRACKET.name());
     	label.setIcon(LResources.ARROW_STYLE_BRACK_R_ICON);
     	lineArrowRChoice.addItem(label);
		label = new JLabel(ArrowStyle.LEFT_SQUARE_BRACKET.name());
     	label.setIcon(LResources.ARROW_STYLE_BRACK_RR_ICON);
     	lineArrowRChoice.addItem(label);
		label = new JLabel(ArrowStyle.RIGHT_DBLE_ARROW.name());
     	label.setIcon(LResources.ARROW_STYLE_DBLE_ARROW_R_ICON);
     	lineArrowRChoice.addItem(label);
		label = new JLabel(ArrowStyle.LEFT_DBLE_ARROW.name());
     	label.setIcon(LResources.ARROW_STYLE_R_DBLE_ARROW_R_ICON);
     	lineArrowRChoice.addItem(label);
     	label = new JLabel(ArrowStyle.ROUND_IN.name());
     	label.setIcon(LResources.ARROW_STYLE_ROUND_IN_R_ICON);
     	lineArrowRChoice.addItem(label);
     	lineArrowRChoice.setPreferredSize(new Dimension(75, 30));
     	lineArrowRChoice.setSize(new Dimension(75, 30));
     	lineArrowRChoice.setMaximumSize(new Dimension(75, 30));
     	lineArrowRChoice.setMinimumSize(new Dimension(75, 30));

     	return lineArrowRChoice;
	}



	/**
	 * Creates a list of the different styles of arrowhead (left).
	 * @return The created list.
	 */
	public static MComboBox createLeftArrowStyleList() {
		final MComboBox lineArrowLChoice = new MComboBox();

		lineArrowLChoice.setRenderer(new LabelListCellRenderer());
		JLabel label = new JLabel(ArrowStyle.NONE.name());
		label.setIcon(LResources.ARROW_STYLE_NONE_L_ICON);
     	lineArrowLChoice.addItem(label);
     	label = new JLabel(ArrowStyle.BAR_IN.name());
     	label.setIcon(LResources.ARROW_STYLE_BAR_IN_L_ICON);
     	lineArrowLChoice.addItem(label);
		label = new JLabel(ArrowStyle.BAR_END.name());
     	label.setIcon(LResources.ARROW_STYLE_BAR_END_L_ICON);
		lineArrowLChoice.addItem(label);
     	label = new JLabel(ArrowStyle.CIRCLE_END.name());
     	label.setIcon(LResources.ARROW_STYLE_CIRCLE_END_L_ICON);
     	lineArrowLChoice.addItem(label);
     	label = new JLabel(ArrowStyle.CIRCLE_IN.name());
     	label.setIcon(LResources.ARROW_STYLE_CIRCLE_IN_L_ICON);
     	lineArrowLChoice.addItem(label);
     	label = new JLabel(ArrowStyle.DISK_END.name());
     	label.setIcon(LResources.ARROW_STYLE_DISK_END_L_ICON);
     	lineArrowLChoice.addItem(label);
     	label = new JLabel(ArrowStyle.DISK_IN.name());
     	label.setIcon(LResources.ARROW_STYLE_DISK_IN_L_ICON);
		lineArrowLChoice.addItem(label);
		label = new JLabel(ArrowStyle.LEFT_ARROW.name());
     	label.setIcon(LResources.ARROW_STYLE_ARROW_L_ICON);
     	lineArrowLChoice.addItem(label);
		label = new JLabel(ArrowStyle.RIGHT_ARROW.name());
     	label.setIcon(LResources.ARROW_STYLE_R_ARROW_L_ICON);
		lineArrowLChoice.addItem(label);
		label = new JLabel(ArrowStyle.LEFT_ROUND_BRACKET.name());
     	label.setIcon(LResources.ARROW_STYLE_ARC_L_ICON);
		lineArrowLChoice.addItem(label);
		label = new JLabel(ArrowStyle.RIGHT_ROUND_BRACKET.name());
     	label.setIcon(LResources.ARROW_STYLE_ARC_LR_ICON);
		lineArrowLChoice.addItem(label);
		label = new JLabel(ArrowStyle.LEFT_SQUARE_BRACKET.name());
     	label.setIcon(LResources.ARROW_STYLE_BRACK_L_ICON);
		lineArrowLChoice.addItem(label);
		label = new JLabel(ArrowStyle.RIGHT_SQUARE_BRACKET.name());
     	label.setIcon(LResources.ARROW_STYLE_BRACK_LR_ICON);
		lineArrowLChoice.addItem(label);
		label = new JLabel(ArrowStyle.LEFT_DBLE_ARROW.name());
     	label.setIcon(LResources.ARROW_STYLE_DBLE_ARROW_L_ICON);
		lineArrowLChoice.addItem(label);
		label = new JLabel(ArrowStyle.RIGHT_DBLE_ARROW.name());
     	label.setIcon(LResources.ARROW_STYLE_R_DBLE_ARROW_L_ICON);
		lineArrowLChoice.addItem(label);
     	label = new JLabel(ArrowStyle.ROUND_IN.name());
     	label.setIcon(LResources.ARROW_STYLE_ROUND_IN_L_ICON);
     	lineArrowLChoice.addItem(label);
     	lineArrowLChoice.setPreferredSize(new Dimension(75, 30));
     	lineArrowLChoice.setSize(new Dimension(75, 30));
     	lineArrowLChoice.setMaximumSize(new Dimension(75, 30));
     	lineArrowLChoice.setMinimumSize(new Dimension(75, 30));

		return lineArrowLChoice;
	}


	@Override
	protected void setWidgetsVisible(final boolean visible) {
		composer.setWidgetVisible(arrowLeftCB, visible);
		composer.setWidgetVisible(arrowRightCB, visible);
		composer.setWidgetVisible(arrowInset, visible);
		composer.setWidgetVisible(arrowLength, visible);
		composer.setWidgetVisible(arrowSizeDim, visible);
		composer.setWidgetVisible(arrowSizeNum, visible);
		composer.setWidgetVisible(dotSizeNum, visible);
		composer.setWidgetVisible(dotSizeDim, visible);
		composer.setWidgetVisible(tbarsizeDim, visible);
		composer.setWidgetVisible(tbarsizeNum, visible);
		composer.setWidgetVisible(bracketNum, visible);
		composer.setWidgetVisible(rbracketNum, visible);
	}


	@Override
	protected void initialiseLinks() {
		try{
			addLink(new List2PencilArrowStyle(this));
			addLink(new List2ShapeArrowStyle(this));
			addLink(new Spinner2SelectionArrowParam(this));
			addLink(new Spinner2PencilArrowParam(this));
		}catch(InstantiationException e){
			BadaboomCollector.INSTANCE.add(e);
		}catch(IllegalAccessException e){
			BadaboomCollector.INSTANCE.add(e);
		}
	}



	@Override
	protected void update(final IShape shape) {
		if(shape!=null && shape.isArrowable()) {
			final IArrow arr1 = shape.getArrowAt(0);
			final IArrow arr2 = shape.getArrowAt(1);
			final ArrowStyle arrStyle1 = arr1.getArrowStyle();
			final ArrowStyle arrStyle2 = arr2.getArrowStyle();

			//TODO this code suppose that if arrowable, there are 2 arrows.
			arrowLeftCB.setSelectedItemSafely(arrStyle1.name());
			arrowRightCB.setSelectedItemSafely(arrStyle2.name());

			final boolean isArrow = arrStyle1.isArrow() || arrStyle2.isArrow();
			final boolean isDot = arrStyle1.isCircleDisk() || arrStyle2.isCircleDisk();
			final boolean isBar = arrStyle1.isBar() || arrStyle2.isBar();
			final boolean isSBracket = arrStyle1.isSquareBracket() || arrStyle2.isSquareBracket();
			final boolean isRBracket = arrStyle1.isRoundBracket() || arrStyle2.isRoundBracket();

			// Updating the visibility of the widgets.
			composer.setWidgetVisible(arrowInset, isArrow);
			composer.setWidgetVisible(arrowLength, isArrow);
			composer.setWidgetVisible(arrowSizeDim, isArrow);
			composer.setWidgetVisible(arrowSizeNum, isArrow);
			composer.setWidgetVisible(dotSizeNum, isDot);
			composer.setWidgetVisible(dotSizeDim, isDot);
			composer.setWidgetVisible(tbarsizeDim, isBar || isSBracket || isRBracket);
			composer.setWidgetVisible(tbarsizeNum, isBar || isSBracket || isRBracket);
			composer.setWidgetVisible(bracketNum, isSBracket);
			composer.setWidgetVisible(rbracketNum, isRBracket);

			// Updating the value of the widgets.
			if(isArrow) {
				arrowInset.setValueSafely(arr1.getArrowInset());
				arrowLength.setValueSafely(arr1.getArrowLength());
				arrowSizeDim.setValueSafely(arr1.getArrowSizeDim());
				arrowSizeNum.setValueSafely(arr1.getArrowSizeNum());
			}

			if(isDot) {
				dotSizeNum.setValueSafely(arr1.getDotSizeNum());
				dotSizeDim.setValueSafely(arr1.getDotSizeDim());
			}

			if(isBar || isSBracket || isRBracket) {
				tbarsizeDim.setValueSafely(arr1.getTBarSizeDim());
				tbarsizeNum.setValueSafely(arr1.getTBarSizeNum());
			}

			if(isSBracket)
				bracketNum.setValueSafely(arr1.getBracketNum());

			if(isRBracket)
				rbracketNum.setValueSafely(arr1.getRBracketNum());
		}
		else setActivated(false);
	}


	/**
	 * @return The left arrow style combo box.
	 * @since 3.0
	 */
	public MComboBox getArrowLeftCB() {
		return arrowLeftCB;
	}

	/**
	 * @return The right arrow style combo box.
	 * @since 3.0
	 */
	public MComboBox getArrowRightCB() {
		return arrowRightCB;
	}


	/**
	 * @return the dotSizeNum.
	 * @since 3.0
	 */
	public MSpinner getDotSizeNum() {
		return dotSizeNum;
	}


	/**
	 * @return the dotSizeDim.
	 * @since 3.0
	 */
	public MSpinner getDotSizeDim() {
		return dotSizeDim;
	}


	/**
	 * @return the bracketNum.
	 * @since 3.0
	 */
	public MSpinner getBracketNum() {
		return bracketNum;
	}


	/**
	 * @return the rbracketNum.
	 * @since 3.0
	 */
	public MSpinner getRbracketNum() {
		return rbracketNum;
	}


	/**
	 * @return the tbarsizeNum.
	 * @since 3.0
	 */
	public MSpinner getTbarsizeNum() {
		return tbarsizeNum;
	}


	/**
	 * @return the tbarsizeDim.
	 * @since 3.0
	 */
	public MSpinner getTbarsizeDim() {
		return tbarsizeDim;
	}


	/**
	 * @return the arrowSizeDim.
	 * @since 3.0
	 */
	public MSpinner getArrowSizeDim() {
		return arrowSizeDim;
	}


	/**
	 * @return the arrowSizeNum.
	 * @since 3.0
	 */
	public MSpinner getArrowSizeNum() {
		return arrowSizeNum;
	}


	/**
	 * @return the arrowLength.
	 * @since 3.0
	 */
	public MSpinner getArrowLength() {
		return arrowLength;
	}


	/**
	 * @return the arrowInset.
	 * @since 3.0
	 */
	public MSpinner getArrowInset() {
		return arrowInset;
	}
}



/** This link maps spinners to a ModifyShapeProperty action. */
class Spinner2PencilArrowParam extends SpinnerForCustomiser<ModifyPencilParameter, ShapeArrowCustomiser> {
	protected Spinner2PencilArrowParam(final ShapeArrowCustomiser ins) throws InstantiationException, IllegalAccessException {
		super(ins, ModifyPencilParameter.class);
	}


	@Override
	public void initAction() {
		action.setProperty(ShapeProperties.ARROW_INSET);
		action.setPencil(instrument.pencil);
	}


	@Override
	public boolean isConditionRespected() {
		final Object obj = interaction.getSpinner();
		return obj==instrument.arrowInset;
	}
}


/** This link maps spinners to a ModifyShapeProperty action. */
class Spinner2SelectionArrowParam extends SpinnerForCustomiser<ModifyShapeProperty, ShapeArrowCustomiser> {
	protected Spinner2SelectionArrowParam(final ShapeArrowCustomiser ins) throws InstantiationException, IllegalAccessException {
		super(ins, ModifyShapeProperty.class);
	}


	@Override
	public void initAction() {
		final Object obj = interaction.getSpinner();

		if(obj==instrument.arrowInset) action.setProperty(ShapeProperties.ARROW_INSET);
		else action.setProperty(ShapeProperties.ARROW_LENGTH);
		action.setGroup(instrument.pencil.drawing.getSelection().duplicate());
	}


	@Override
	public boolean isConditionRespected() {
		final Object obj = interaction.getSpinner();
		return obj==instrument.arrowInset || obj==instrument.arrowLength;
	}
}



/**
 * This link maps a list to a ModifyPencil action.
 */
class List2PencilArrowStyle extends ListForCustomiser<ModifyPencilParameter, ShapeArrowCustomiser> {
	/**
	 * Creates the link.
	 * @param instrument The instrument that contains the link.
	 * @throws InstantiationException If an error of instantiation (interaction, action) occurs.
	 * @throws IllegalAccessException If no free-parameter constructor are provided.
	 */
	protected List2PencilArrowStyle(final ShapeArrowCustomiser instrument) throws InstantiationException, IllegalAccessException {
		super(instrument, ModifyPencilParameter.class);
	}

	@Override
	public void initAction() {
		if(getInteraction().getList()==instrument.arrowLeftCB)
			action.setProperty(ShapeProperties.ARROW1_STYLE);
		else
			action.setProperty(ShapeProperties.ARROW2_STYLE);

		action.setPencil(instrument.pencil);
		action.setValue(ArrowStyle.getArrowStyle(getLabelText()));
	}

	@Override
	public boolean isConditionRespected() {
		final ItemSelectable is	= interaction.getList();
		return (is==instrument.arrowLeftCB || is==instrument.arrowRightCB) && instrument.pencil.isActivated();
	}
}


/**
 * This link maps a list to a ModifyShape action.
 */
class List2ShapeArrowStyle extends ListForCustomiser<ModifyShapeProperty, ShapeArrowCustomiser> {
	/**
	 * Creates the link.
	 * @param instrument The instrument that contains the link.
	 * @throws InstantiationException If an error of instantiation (interaction, action) occurs.
	 * @throws IllegalAccessException If no free-parameter constructor are provided.
	 */
	protected List2ShapeArrowStyle(final ShapeArrowCustomiser instrument) throws InstantiationException, IllegalAccessException {
		super(instrument, ModifyShapeProperty.class);
	}


	@Override
	public void initAction() {
		if(getInteraction().getList()==instrument.arrowLeftCB)
			action.setProperty(ShapeProperties.ARROW1_STYLE);
		else
			action.setProperty(ShapeProperties.ARROW2_STYLE);

		action.setGroup(instrument.pencil.drawing.getSelection().duplicate());
		action.setValue(ArrowStyle.getArrowStyle(getLabelText()));
	}

	@Override
	public boolean isConditionRespected() {
		final ItemSelectable is	= interaction.getList();
		return (is==instrument.arrowLeftCB || is==instrument.arrowRightCB) && instrument.hand.isActivated();
	}
}

