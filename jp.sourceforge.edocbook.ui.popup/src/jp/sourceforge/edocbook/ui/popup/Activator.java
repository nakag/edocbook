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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
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
