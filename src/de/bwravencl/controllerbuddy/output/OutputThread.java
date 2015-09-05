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

package de.bwravencl.controllerbuddy.output;

import java.util.Locale;
import java.util.ResourceBundle;

import de.bwravencl.controllerbuddy.gui.Main;
import de.bwravencl.controllerbuddy.input.Input;
import net.brockmatt.util.ResourceBundleUtil;

public abstract class OutputThread extends Thread {

	public static final long DEFAULT_POLL_INTERVAL = 10L;

	protected final Main main;
	protected final Input input;

	protected long pollInterval = DEFAULT_POLL_INTERVAL;
	protected int minAxisValue;
	protected int maxAxisValue;
	protected int nButtons;

	protected final ResourceBundle rb = new ResourceBundleUtil().getResourceBundle(Main.STRING_RESOURCE_BUNDLE_BASENAME,
			Locale.getDefault());

	public OutputThread(Main main, Input input) {
		this.main = main;
		this.input = input;
		input.setOutputThread(this);
	}

	public int getMaxAxisValue() {
		return maxAxisValue;
	}

	public int getMinAxisValue() {
		return minAxisValue;
	}

	public int getnButtons() {
		return nButtons;
	}

	public long getPollInterval() {
		return pollInterval;
	}

	public void setMaxAxisValue(int maxAxisValue) {
		this.maxAxisValue = maxAxisValue;
	}

	public void setMinAxisValue(int minAxisValue) {
		this.minAxisValue = minAxisValue;
	}

	public void setnButtons(int nButtons) {
		this.nButtons = nButtons;
		input.setnButtons(nButtons);
	}

	public void setPollInterval(long pollInterval) {
		this.pollInterval = pollInterval;
	}

	public void stopOutput() {
		input.reset();
	}

}
