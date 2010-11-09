/*
 * This file is part of Eclipse Docbook Plugin
 * 
 * Copyright (C) 2010 nakaG <nakag@sourceforge.jp>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Additional permission under GNU GPL version 3 section 7
 * 
 * If you modify this Program, or any covered work, by linking or combining 
 * it with Eclipse (or a modified version of that library), 
 * containing parts covered by the terms of Eclipse Public License, 
 * the licensors of this Program grant you additional permission to convey the resulting work.
 * {Corresponding Source for a non-source form of such a combination shall 
 * include the source code for the parts of Eclipse used as well as that of the covered work.}
 */
package jp.sourceforge.edocbook.ui.popup;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "jp.sourceforge.edocbook.ui.popup";

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * Error Dialog
	 * 
	 * @param t
	 *            the Throwable
	 */
	public static void showErrorDialog(Throwable t) {
		IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, 0,
				t.getMessage(), t);

		log(t);

		ErrorDialog.openError(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), "Error",
				"Error occured!", status);
	}

	/**
	 * Information Dialog
	 * 
	 * @param message
	 *            the message
	 */
	public static void showMessageDialog(String message) {
		MessageBox messageBox = new MessageBox(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), SWT.ICON_INFORMATION
				| SWT.OK);
		messageBox.setText("information");
		messageBox.setMessage(message);
		messageBox.open();
	}

	/**
	 * Exception Log
	 * 
	 * @param t
	 *            the Throwable
	 */
	public static void log(Throwable t) {
		IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, t
				.getMessage(), t);
		getDefault().getLog().log(status);
		t.printStackTrace();
	}

}
