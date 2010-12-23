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
package jp.sourceforge.edocbook.ui.preferences;

import jp.sourceforge.edocbook.core.Param;
import jp.sourceforge.edocbook.ui.popup.Activator;
import jp.sourceforge.edocbook.ui.popup.html.actions.AbstractHtmlCreateAction;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author nakaG
 *
 */
public class WorkbenchPreferencePage1 extends PreferencePage implements
		IWorkbenchPreferencePage {
	private HtmlParamPreferencePageComposite htmlParamPreferencePage;
	private HtmlTemplatePreferencePageComposite htmlTemplatePreferencepage;
	/**
	 * 
	 */
	public WorkbenchPreferencePage1() {
		// TODO Auto-generated constructor stub
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	/**
	 * @param title
	 */
	public WorkbenchPreferencePage1(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param image
	 */
	public WorkbenchPreferencePage1(String title, ImageDescriptor image) {
		super(title, image);
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		RowLayout layout = new RowLayout(SWT.VERTICAL);
		composite.setLayout(layout);
		htmlParamPreferencePage = new HtmlParamPreferencePageComposite(composite, SWT.NULL, Activator.getPreferenceAsParam(AbstractHtmlCreateAction.PARAM_KEYS));
		htmlTemplatePreferencepage = new HtmlTemplatePreferencePageComposite(composite, SWT.NULL);
//		initializeValue();
		return composite;
	}
//	private void initializeValue() {
//		for (Map.Entry<String, String> entry : Activator.setupPreference(AbstractHtmlCreateAction.PARAM_KEYS).entrySet()) {
//			
//		}
//		for (Map.Entry<String, String> entry : Activator.setupPreference(AbstractHtmlCreateAction.TEMPLATE_KEYS).entrySet()) {
//			
//		}
//	}
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		IPreferenceStore store = getPreferenceStore();
		StringBuilder keys = new StringBuilder();
		boolean added = false;
		for (Param param : htmlParamPreferencePage.getParameters()) {
			if (added) {
				keys.append(",");
			} else {
				added = true;
			}
			store.setValue(param.getName(), param.getValue());
			keys.append(param.getName());
		}
		store.setValue(AbstractHtmlCreateAction.PARAM_KEYS, keys.toString());

		return super.performOk();
	}

}
