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
package jp.sourceforge.edocbook.ui.preferences.dialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import jp.sourceforge.edocbook.core.Param;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * the Parameter Edit Dialog
 * 
 * @author nakaG
 * 
 */
public class ParameterEditDialog extends Dialog {
	private ParameterEditComposite composite;
	private ParameterEditModel model;
	private ParameterChangeListener listener;

	/**
	 * the constructor
	 * 
	 * @param parentShell
	 *            parent
	 */
	public ParameterEditDialog(Shell parentShell) {
		this(parentShell, null);
	}

	/**
	 * the constructor
	 * 
	 * @param parentShell
	 *            parent
	 * @param model
	 *            the model to be editing
	 */
	public ParameterEditDialog(Shell parentShell, Param model) {
		super(parentShell);
		this.model = new ParameterEditModel();
		if (model != null) {
			this.model.setName(model.getName());
			this.model.setValue(model.getValue());
		}
		this.listener = new ParameterChangeListener();
		this.model.addPropertyChangeListener(listener);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		getShell().setText("Edit xsl:parameter");
		composite = new ParameterEditComposite(parent, SWT.NULL, model);
		return composite;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		Button okButton = getButton(IDialogConstants.OK_ID);
		okButton.setEnabled(false);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#close()
	 */
	@Override
	public boolean close() {
		this.model.removePropertyChangeListener(listener);
		return super.close();
	}

	/**
	 * get the edit model.
	 * 
	 * @return the edited Parameter object
	 */
	public Param getEditModel() {
		return new Param(model.getName(), model.getValue());
	}

	/**
	 * the listener of Parameter Edit Model
	 * 
	 */
	private class ParameterChangeListener implements PropertyChangeListener {

		/**
		 * {@inheritDoc}
		 * 
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		@Override
		public void propertyChange(PropertyChangeEvent e) {
			Button okButton = getButton(IDialogConstants.OK_ID);
			okButton.setEnabled(((ParameterEditModel) e.getSource()).getName()
					.length() != 0);
		}

	}
}
