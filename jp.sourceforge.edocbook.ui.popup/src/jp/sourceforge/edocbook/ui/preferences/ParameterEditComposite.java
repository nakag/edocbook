package jp.sourceforge.edocbook.ui.preferences;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class ParameterEditComposite extends Composite {
	private ParameterEditModel model;
	private Label nameLabel = null;
	private Text paramNameText = null;
	private Label valueLabel = null;
	private Text paramValueText = null;

	public ParameterEditComposite(Composite parent, int style, ParameterEditModel model) {
		super(parent, style);
		this.model = model;
		initialize();
	}

	private void initialize() {
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.verticalAlignment = GridData.CENTER;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = -1;
		gridData.widthHint = 200;
		gridData.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		nameLabel = new Label(this, SWT.NONE);
		nameLabel.setText("param name");
		paramNameText = new Text(this, SWT.BORDER);
		paramNameText.setLayoutData(gridData);
		paramNameText.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				model.setName(((Text)e.getSource()).getText());
			}
		});
		valueLabel = new Label(this, SWT.NONE);
		valueLabel.setText("param value");
		paramValueText = new Text(this, SWT.BORDER);
		paramValueText.setLayoutData(gridData1);
		paramValueText.addModifyListener(new org.eclipse.swt.events.ModifyListener() {
			public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
				model.setValue(((Text)e.getSource()).getText());
			}
		});
		this.setLayout(gridLayout);
		this.setSize(new Point(248, 51));
		initializeValue();
	}
	public void initializeValue() {
		paramNameText.setText(model.getName());
		paramValueText.setText(model.getValue());
	}
	public String getParameterName() {
		return paramNameText.getText();
	}
	public String getParameterValue() {
		return paramValueText.getText();
	}
	
}  //  @jve:decl-index=0:visual-constraint="0,0"
