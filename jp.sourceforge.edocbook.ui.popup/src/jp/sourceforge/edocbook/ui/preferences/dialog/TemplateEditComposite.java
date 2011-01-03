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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author nakaG
 *
 */
public class TemplateEditComposite extends Composite {
	private TemplateEditModel model;
	private Label templateNameLabel = null;
	private Text templateNameText = null;
	private Label templateBodyLabel = null;
	private Text templateBodyTextArea = null;
	public TemplateEditComposite(Composite parent, int style, TemplateEditModel model) {
		super(parent, style);
		this.model = model;
		initialize();
	}

	private void initialize() {
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.verticalAlignment = GridData.CENTER;
		gridData1.grabExcessVerticalSpace = false;
		gridData1.heightHint = 200;
		gridData1.horizontalAlignment = GridData.FILL;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = false;
		gridData.heightHint = -1;
		gridData.widthHint = 200;
		gridData.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		templateNameLabel = new Label(this, SWT.NONE);
		templateNameLabel.setText("template name");
		templateNameText = new Text(this, SWT.BORDER);
		templateNameText.setLayoutData(gridData);
		templateNameText.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				model.setName(((Text)e.getSource()).getText());
			}
		});
		templateBodyLabel = new Label(this, SWT.NONE);
		templateBodyLabel.setText("template body");
		templateBodyTextArea = new Text(this, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL|SWT.BORDER);
		templateBodyTextArea.setLayoutData(gridData1);
		templateBodyTextArea
				.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
					public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
						model.setBody(((Text)e.getSource()).getText());
					}
				});
		this.setLayout(gridLayout);
		this.setSize(new Point(298, 239));
		initializeValue();
	}
	private void initializeValue() {
		templateNameText.setText(model.getName());
		templateBodyTextArea.setText(model.getBody());
	}
}  //  @jve:decl-index=0:visual-constraint="0,0"
