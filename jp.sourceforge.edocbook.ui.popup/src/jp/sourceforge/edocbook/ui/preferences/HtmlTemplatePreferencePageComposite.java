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

import java.util.ArrayList;
import java.util.List;

import jp.sourceforge.edocbook.core.Template;
import jp.sourceforge.edocbook.ui.preferences.dialog.TemplateEditDialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * @author nakaG
 *
 */
public class HtmlTemplatePreferencePageComposite extends Composite {
	private java.util.List<Template> templates;
	private Group templateGroup = null;
	private Table templateTable = null;
	private Composite templateButtonComposite = null;
	private Button temlpateAddButton = null;
	private Button templateEditButton = null;
	private Button templateDeleteButton = null;
	private Text templateTextArea = null;
	public HtmlTemplatePreferencePageComposite(Composite parent, int style, java.util.List<Template> templates) {
		super(parent, style);
		this.templates = templates;
		initialize();
	}

	private void initialize() {
		createTemplateGroup();
		this.setLayout(new GridLayout());
		setSize(new Point(399, 369));
		setLayout(new GridLayout());
		updateTemplateTable();
	}
	private void updateTemplateTable() {
		templateTable.removeAll();
		for (Template template : templates) {
			TableItem item = new TableItem(templateTable, SWT.NULL);
			item.setText(0, template.getName());
//			item.setText(1, template.getBody());
		}
	}
	/**
	 * This method initializes templateGroup	
	 *
	 */
	private void createTemplateGroup() {
		GridData gridData5 = new GridData();
		gridData5.grabExcessHorizontalSpace = true;
		gridData5.verticalAlignment = GridData.FILL;
		gridData5.grabExcessVerticalSpace = true;
		gridData5.horizontalAlignment = GridData.FILL;
		GridData gridData4 = new GridData();
		gridData4.horizontalAlignment = GridData.FILL;
		gridData4.heightHint = -1;
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.grabExcessVerticalSpace = true;
		gridData4.verticalAlignment = GridData.FILL;
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.heightHint = -1;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.grabExcessVerticalSpace = false;
		gridData3.verticalAlignment = GridData.FILL;
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 2;
		templateGroup = new Group(this, SWT.NONE);
		templateGroup.setText("xsl:template");
		templateGroup.setLayoutData(gridData5);
		templateGroup.setLayout(gridLayout1);
		templateTable = new Table(templateGroup, SWT.NONE);
		templateTable.setHeaderVisible(true);
		templateTable.setLayoutData(gridData3);
		templateTable.setLinesVisible(true);
		templateTable
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						int index = templateTable.getSelectionIndex();
						if (index == -1) {
							return;
						}
						templateTextArea.setText(templates.get(index).getBody());
					}
				});
		createTemplateButtonComposite();
		templateTextArea = new Text(templateGroup, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
		templateTextArea.setLayoutData(gridData4);
		TableColumn tableColumn2 = new TableColumn(templateTable, SWT.NONE);
		tableColumn2.setWidth(200);
		tableColumn2.setText("template name");
	}

	/**
	 * This method initializes templateButtonComposite	
	 *
	 */
	private void createTemplateButtonComposite() {
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.verticalAlignment = GridData.FILL;
		RowLayout rowLayout1 = new RowLayout();
		rowLayout1.justify = false;
		rowLayout1.type = org.eclipse.swt.SWT.VERTICAL;
		rowLayout1.pack = false;
		rowLayout1.spacing = 5;
		rowLayout1.wrap = false;
		rowLayout1.fill = false;
		templateButtonComposite = new Composite(templateGroup, SWT.NONE);
		templateButtonComposite.setLayout(rowLayout1);
		templateButtonComposite.setLayoutData(gridData2);
		temlpateAddButton = new Button(templateButtonComposite, SWT.NONE);
		temlpateAddButton.setText("Add");
		temlpateAddButton
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						TemplateEditDialog dialog = new TemplateEditDialog(getShell());
						if (dialog.open() == Dialog.OK) {
							templates.add(dialog.getEditModel());
							updateTemplateTable();
						}
					}
				});
		templateEditButton = new Button(templateButtonComposite, SWT.NONE);
		templateEditButton.setText("Edit");
		templateEditButton
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						int index = templateTable.getSelectionIndex();
						if (index == -1) {
							return;
						}
						TemplateEditDialog dialog = new TemplateEditDialog(getShell(), templates.get(index));
						if (dialog.open() == Dialog.OK) {
							Template template = dialog.getEditModel();
							templates.remove(index);
							templates.add(template);
							updateTemplateTable();
						}
					}
				});
		templateDeleteButton = new Button(templateButtonComposite, SWT.NONE);
		templateDeleteButton.setText("Delete");
		templateDeleteButton
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						int[] indices = templateTable.getSelectionIndices();
						if (indices.length == 0) {
							return;
						}
						List<Template> removedList = new ArrayList<Template>();
						for (int i : indices) {
							removedList.add(templates.get(i));
						}
						for (Template t : removedList) {
							templates.remove(t);
						}
						updateTemplateTable();
					}
				});
	}

	/**
	 * @return the templates
	 */
	public java.util.List<Template> getTemplates() {
		return templates;
	}

	
}  //  @jve:decl-index=0:visual-constraint="-9,6"
