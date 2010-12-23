/**
 * 
 */
package jp.sourceforge.edocbook.ui.preferences;

import jp.sourceforge.edocbook.core.Param;

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
//	private java.util.List<Param> parameters;
	private Group templateGroup = null;
	private Table templateTable = null;
	private Composite templateButtonComposite = null;
	private Button temlpateAddButton = null;
	private Button templateEditButton = null;
	private Button templateDeleteButton = null;
	private Text templateTextArea = null;
	public HtmlTemplatePreferencePageComposite(Composite parent, int style) {
		super(parent, style);
//		this.parameters = parameters;
		initialize();
	}

	private void initialize() {
		createTemplateGroup();
		this.setLayout(new GridLayout());
		setSize(new Point(399, 369));
		setLayout(new GridLayout());
		updateParamTable();
	}
	private void updateParamTable() {
		templateTable.removeAll();
//		for (Param param : parameters) {
//			TableItem item = new TableItem(templateTable, SWT.NULL);
//			item.setText(0, param.getName());
//			item.setText(1, param.getValue());
//		}
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
		gridData4.horizontalSpan = 2;
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.grabExcessVerticalSpace = true;
		gridData4.verticalAlignment = GridData.FILL;
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.heightHint = 80;
		gridData3.grabExcessHorizontalSpace = true;
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
		createTemplateButtonComposite();
		templateTextArea = new Text(templateGroup, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL|SWT.BORDER);
		templateTextArea.setLayoutData(gridData4);
		TableColumn tableColumn2 = new TableColumn(templateTable, SWT.NONE);
		tableColumn2.setWidth(150);
		tableColumn2.setText("template name");
		TableColumn tableColumn3 = new TableColumn(templateTable, SWT.NONE);
		tableColumn3.setWidth(60);
		tableColumn3.setText("body");
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
		templateEditButton = new Button(templateButtonComposite, SWT.NONE);
		templateEditButton.setText("Edit");
		templateDeleteButton = new Button(templateButtonComposite, SWT.NONE);
		templateDeleteButton.setText("Delete");
	}

//	/**
//	 * @return the parameters
//	 */
//	public java.util.List<Param> getParameters() {
//		return parameters;
//	}
	
}  //  @jve:decl-index=0:visual-constraint="-9,6"
