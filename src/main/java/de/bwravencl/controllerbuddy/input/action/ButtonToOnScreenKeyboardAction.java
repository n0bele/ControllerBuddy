/* Copyright (C) 2017  Matteo Hausner
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

import de.bwravencl.controllerbuddy.input.Input;

public class ButtonToOnScreenKeyboardAction implements IButtonToAction {

	private float activationValue = DEFAULT_ACTIVATION_VALUE;
	private boolean longPress = DEFAULT_LONG_PRESS;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public void doAction(final Input input, float value) {
		value = handleLongPress(value);

		if (value == activationValue)
			input.getMain().toggleOnScreenKeyboard();
	}

	@Override
	public float getActivationValue() {
		return activationValue;
	}

	@Override
	public boolean isLongPress() {
		return longPress;
	}

	@Override
	public void setActivationValue(final Float activationValue) {
		this.activationValue = activationValue;
	}

	@Override
	public void setLongPress(final Boolean longPress) {
		this.longPress = longPress;
	}

	@Override
	public String toString() {
		return rb.getString("BUTTON_TO_ON_SCREEN_KEYBOARD_ACTION_STRING");
	}

}