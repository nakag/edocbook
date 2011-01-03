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

import jp.sourceforge.edocbook.core.Param;
import jp.sourceforge.edocbook.ui.preferences.dialog.ParameterEditDialog;

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

/**
 * @author nakaG
 *
 */
public class HtmlParamPreferencePageComposite extends Composite {
	private java.util.List<Param> parameters;
	private Group parameterGroup = null;
	private Table parameterTable = null;
	private Composite paramButtonComposite = null;
	private Button paramAddButton = null;
	private Button paramEditButton = null;
	private Button paramDeleteButton = null;
	public HtmlParamPreferencePageComposite(Composite parent, int style, java.util.List<Param> parameters) {
		super(parent, style);
		this.parameters = parameters;
		initialize();
	}

	private void initialize() {
		createParameterGroup();
		this.setLayout(new GridLayout());
		setSize(new Point(308, 132));
		setLayout(new GridLayout());
		updateParamTable();
	}
	private void updateParamTable() {
		parameterTable.removeAll();
		for (Param param : parameters) {
			TableItem item = new TableItem(parameterTable, SWT.NULL);
			item.setText(0, param.getName());
			item.setText(1, param.getValue());
		}
	}
	/**
	 * This method initializes parameterGroup	
	 *
	 */
	private void createParameterGroup() {
		GridData gridData6 = new GridData();
		gridData6.grabExcessHorizontalSpace = true;
		gridData6.verticalAlignment = GridData.FILL;
		gridData6.grabExcessVerticalSpace = true;
		gridData6.horizontalAlignment = GridData.FILL;
		GridData gridData1 = new GridData();
		gridData1.heightHint = -1;
		gridData1.verticalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.horizontalAlignment = GridData.FILL;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		parameterGroup = new Group(this, SWT.NONE);
		parameterGroup.setLayout(gridLayout);
		parameterGroup.setLayoutData(gridData6);
		parameterGroup.setText("xsl:param");
		parameterTable = new Table(parameterGroup, SWT.NONE);
		parameterTable.setHeaderVisible(true);
		parameterTable.setLayoutData(gridData1);
		parameterTable.setLinesVisible(true);
		createParamButtonComposite();
		TableColumn tableColumn = new TableColumn(parameterTable, SWT.NONE);
		tableColumn.setWidth(150);
		tableColumn.setText("param name");
		TableColumn tableColumn1 = new TableColumn(parameterTable, SWT.NONE);
		tableColumn1.setWidth(60);
		tableColumn1.setText("value");
	}

	/**
	 * This method initializes paramButtonComposite	
	 *
	 */
	private void createParamButtonComposite() {
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = org.eclipse.swt.SWT.VERTICAL;
		rowLayout.justify = false;
		rowLayout.spacing = 5;
		rowLayout.fill = true;
		GridData gridData = new GridData();
		gridData.grabExcessVerticalSpace = false;
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalAlignment = GridData.FILL;
		paramButtonComposite = new Composite(parameterGroup, SWT.NONE);
		paramButtonComposite.setLayoutData(gridData);
		paramButtonComposite.setLayout(rowLayout);
		paramAddButton = new Button(paramButtonComposite, SWT.NONE);
		paramAddButton.setText("Add");
		paramAddButton
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						System.out.println("widgetSelected()"); // TODO Auto-generated Event stub widgetSelected()
						ParameterEditDialog dialog = new ParameterEditDialog(getShell());
						if (dialog.open() == Dialog.OK) {
							parameters.add(dialog.getEditModel());
							updateParamTable();
						}
					}
				});
		paramEditButton = new Button(paramButtonComposite, SWT.NONE);
		paramEditButton.setText("Edit");
		paramEditButton
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						int index = parameterTable.getSelectionIndex();
						ParameterEditDialog dialog = new ParameterEditDialog(getShell(), parameters.get(index));
						if (dialog.open() == Dialog.OK) {
							Param param = dialog.getEditModel();
							parameters.remove(index);
							parameters.add(index, param);
							updateParamTable();
						}
					}
				});
		paramDeleteButton = new Button(paramButtonComposite, SWT.NONE);
		paramDeleteButton.setText("Delete");
		paramDeleteButton
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						int[] indices = parameterTable.getSelectionIndices();
						if (indices.length == 0) {
							return;
						}
						List<Param> removedList = new ArrayList<Param>(0);
						for (int i : indices) {
							removedList.add(parameters.get(i));
						}
						for (Param p : removedList) {
							parameters.remove(p);
						}
						updateParamTable();
					}
				});
	}

	/**
	 * @return the parameters
	 */
	public java.util.List<Param> getParameters() {
		return parameters;
	}
	
}  //  @jve:decl-index=0:visual-constraint="-9,6"
