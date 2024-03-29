/*
 * This file is part of Eclipse Docbook Plugin
 * 
 * Copyright (C) 2010-2011 nakaG <nakag@users.sourceforge.jp>
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
package jp.sourceforge.edocbook.ui.popup.html.actions;

import java.util.List;
import java.util.Properties;

import javax.xml.transform.OutputKeys;

import jp.sourceforge.edocbook.core.DocbookFile;
import jp.sourceforge.edocbook.core.DocbookXsl;
import jp.sourceforge.edocbook.core.Param;
import jp.sourceforge.edocbook.core.ResultFile;
import jp.sourceforge.edocbook.core.Template;
import jp.sourceforge.edocbook.ui.popup.Activator;
import jp.sourceforge.edocbook.ui.popup.actions.AbstractCreateAction;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;

/**
 * AbstractHtmlCreateAction
 * 
 * @author nakaG
 * 
 */
public abstract class AbstractHtmlCreateAction extends AbstractCreateAction {
	/**
	 * the constructor
	 */
	public AbstractHtmlCreateAction() {
		// transformer = new DocbookTransformer(createXslFile());
	}

	/**
	 * {inheritDoc}
	 * 
	 * @see IActionDelegate#run(IAction)
	 */
	@Override
	public void run(IAction action) {
		Shell shell = getPart().getSite().getShell();
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
		final DocbookFile source = getSourceFile();
		if (source == null) {
			return;
		}

		try {
			dialog.run(true, false, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) {
					monitor.beginTask("start eclipse docbook plugin", 4);

					monitor.setTaskName("create result file");
					ResultFile result = new ResultFile(source,
							getOutputDirectory(), "html");
					monitor.worked(1);

					monitor.setTaskName("create xsl file");
					DocbookXsl xsl = createXslFile();
					monitor.worked(2);

					monitor.setTaskName("apply style sheet");
					xsl.apply(source, result);
					monitor.worked(3);

					monitor.setTaskName("reflesh project");
					reflesh();
					monitor.worked(4);
					monitor.done();
				}
			});
		} catch (Exception e) {
			Activator.showErrorDialog(e);
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see jp.sourceforge.edocbook.ui.popup.actions.AbstractCreateAction#createOutputProperties()
	 */
	@Override
	protected Properties createOutputProperties() {
		// TODO get output property from anywhere(preference?)
		Properties prop = new Properties();
		prop.put(OutputKeys.DOCTYPE_PUBLIC,
				"-//W3C//DTD HTML 4.01 Transitional//EN");
		prop.put(OutputKeys.DOCTYPE_SYSTEM,
				"http://www.w3.org/TR/html4/loose.dtd");
		prop.put(OutputKeys.METHOD, "html");
		prop.put(OutputKeys.INDENT, "yes");
		try {
			prop.put(OutputKeys.ENCODING, getSelection().getCharset());
		} catch (CoreException e) {
			e.printStackTrace();
			prop.put(OutputKeys.ENCODING, "UTF-8");
		}
		return prop;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see jp.sourceforge.edocbook.ui.popup.actions.AbstractCreateAction#createParameters()
	 */
	@Override
	protected List<Param> createParameters() {
		return Activator.getPreferenceAsParam();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see jp.sourceforge.edocbook.ui.popup.actions.AbstractCreateAction#createTemlates()
	 */
	@Override
	protected List<Template> createTemlates() {
		return Activator.getPreferenceAsTemplate();
	}
}
