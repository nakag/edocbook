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
import jp.sourceforge.edocbook.core.Template;
import jp.sourceforge.edocbook.ui.popup.Activator;
import jp.sourceforge.edocbook.ui.popup.html.actions.AbstractHtmlCreateAction;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * the html preference page
 * 
 * @author nakaG
 * 
 */
public class HtmlPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {
	private HtmlParamPreferencePageComposite htmlParamPreferencePage;
	private HtmlTemplatePreferencePageComposite htmlTemplatePreferencepage;

	/**
	 * the constructor
	 */
	public HtmlPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	/**
	 * the constructor
	 * 
	 * @param title
	 *            the title of the page
	 */
	public HtmlPreferencePage(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
	 * the constructor
	 * 
	 * @param title
	 *            the title of the page
	 * @param image
	 *            the image of the page
	 */
	public HtmlPreferencePage(String title, ImageDescriptor image) {
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
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		htmlParamPreferencePage = new HtmlParamPreferencePageComposite(
				composite,
				SWT.NULL,
				Activator
						.getPreferenceAsParam(AbstractHtmlCreateAction.PARAM_KEYS));
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.verticalAlignment = GridData.FILL;
		htmlParamPreferencePage.setLayoutData(gridData1);
		htmlTemplatePreferencepage = new HtmlTemplatePreferencePageComposite(
				composite,
				SWT.NULL,
				Activator
						.getPreferenceAsTemplate(AbstractHtmlCreateAction.TEMPLATE_KEYS));
		GridData gridData2 = new GridData();
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.verticalAlignment = GridData.FILL;
		htmlTemplatePreferencepage.setLayoutData(gridData2);

		return composite;
	}

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

		keys = new StringBuilder();
		added = false;
		for (Template template : htmlTemplatePreferencepage.getTemplates()) {
			if (added) {
				keys.append(",");
			} else {
				added = true;
			}
			store.setValue(template.getName(), template.getBody());
			keys.append(template.getName());
		}
		store.setValue(AbstractHtmlCreateAction.TEMPLATE_KEYS, keys.toString());

		return super.performOk();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		// TODO Auto-generated method stub
		super.performDefaults();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

}
