/* Copyright (C) 2015  Matteo Hausner
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

public class ButtonToButtonAction extends ToButtonAction {

	public static final float DEFAULT_ACTIVATION_VALUE = 1.0f;

	private float activationValue = DEFAULT_ACTIVATION_VALUE;

	@Override
	public void doAction(Input input, float value) {
		final boolean down = value == activationValue;
		input.setButtons(buttonId, down ? 1.0f : 0.0f);
	}

	public float getActivationValue() {
		return activationValue;
	}

	public void setActivationValue(Float activationValue) {
		this.activationValue = activationValue;
	}

}
