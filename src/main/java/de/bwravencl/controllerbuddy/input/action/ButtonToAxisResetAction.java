/* Copyright (C) 2020  Matteo Hausner
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package de.bwravencl.controllerbuddy.input.action;

import static de.bwravencl.controllerbuddy.gui.Main.strings;

import java.text.MessageFormat;

import de.bwravencl.controllerbuddy.input.Input;
import de.bwravencl.controllerbuddy.input.Input.VirtualAxis;
import de.bwravencl.controllerbuddy.input.action.annotation.Action;
import de.bwravencl.controllerbuddy.input.action.annotation.Action.ActionCategory;
import de.bwravencl.controllerbuddy.input.action.annotation.ActionProperty;
import de.bwravencl.controllerbuddy.input.action.gui.AxisValueEditorBuilder;
import de.bwravencl.controllerbuddy.input.action.gui.BooleanEditorBuilder;
import de.bwravencl.controllerbuddy.input.action.gui.LongPressEditorBuilder;
import de.bwravencl.controllerbuddy.input.action.gui.VirtualAxisEditorBuilder;

@Action(label = "BUTTON_TO_AXIS_RESET_ACTION", category = ActionCategory.BUTTON_AND_CYCLES, order = 135)
public final class ButtonToAxisResetAction extends DescribableAction<Byte> implements IButtonToAction {

	private static final float DEFAULT_RESET_VALUE = 0f;

	private static final boolean DEFAULT_FLUID = false;

	@ActionProperty(label = "VIRTUAL_AXIS", editorBuilder = VirtualAxisEditorBuilder.class, order = 10)
	VirtualAxis virtualAxis = VirtualAxis.X;

	@ActionProperty(label = "RESET_VALUE", editorBuilder = AxisValueEditorBuilder.class, order = 20)
	private float resetValue = DEFAULT_RESET_VALUE;

	@ActionProperty(label = "FLUID", editorBuilder = BooleanEditorBuilder.class, order = 30)
	private boolean fluid = DEFAULT_FLUID;

	@ActionProperty(label = "LONG_PRESS", editorBuilder = LongPressEditorBuilder.class, order = 400)
	private boolean longPress = DEFAULT_LONG_PRESS;

	private transient boolean wasUp = true;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public void doAction(final Input input, final int component, Byte value) {
		value = handleLongPress(input, component, value);

		if (value == 0)
			wasUp = true;
		else if (wasUp) {
			if (fluid)
				input.moveAxis(virtualAxis, resetValue);
			else
				input.setAxis(virtualAxis, resetValue, false, null);

			wasUp = false;
		}
	}

	@Override
	public String getDescription(final Input input) {
		if (!isDescriptionEmpty())
			return super.getDescription(input);

		return MessageFormat.format(strings.getString("RESET_VJOY_AXIS_NAME"), virtualAxis);
	}

	public float getResetValue() {
		return resetValue;
	}

	public VirtualAxis getVirtualAxis() {
		return virtualAxis;
	}

	public boolean isFluid() {
		return fluid;
	}

	@Override
	public boolean isLongPress() {
		return longPress;
	}

	public void setFluid(final boolean fluid) {
		this.fluid = fluid;
	}

	@Override
	public void setLongPress(final boolean longPress) {
		this.longPress = longPress;
	}

	public void setResetValue(final float resetValue) {
		this.resetValue = resetValue;
	}

	public void setVirtualAxis(final VirtualAxis virtualAxis) {
		this.virtualAxis = virtualAxis;
	}
}
