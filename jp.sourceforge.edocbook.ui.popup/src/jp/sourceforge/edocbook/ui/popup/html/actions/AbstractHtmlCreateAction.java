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
package jp.sourceforge.edocbook.ui.popup.html.actions;

import java.util.List;
import java.util.Properties;

import javax.xml.transform.OutputKeys;

import jp.sourceforge.edocbook.core.DocbookFile;
import jp.sourceforge.edocbook.core.DocbookXsl;
import jp.sourceforge.edocbook.core.EDocbookRuntimeException;
import jp.sourceforge.edocbook.core.Param;
import jp.sourceforge.edocbook.core.Template;
import jp.sourceforge.edocbook.ui.popup.Activator;
import jp.sourceforge.edocbook.ui.popup.actions.AbstractCreateAction;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;

/**
 * AbstractHtmlCreateAction
 * 
 * @author nakaG
 * 
 */
public abstract class AbstractHtmlCreateAction extends AbstractCreateAction {
	public static final String PARAM_KEYS = "jp.sourceforge.edocbook.ui.popup.html.param_keys";
	public static final String TEMPLATE_KEYS = "jp.sourceforge.edocbook.ui.popup.html.template_keys";

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
		try {
			DocbookFile source = getSourceFile();
			if (source == null) {
				return;
			}
			// ResultFile result =
			createXslFile().apply(source);
			reflesh();
			Activator.showMessageDialog("Output completed.");
		} catch (EDocbookRuntimeException e) {
			Activator.showErrorDialog(e);
		}
	}

	@Override
	protected abstract DocbookXsl createXslFile();

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
		prop.put(OutputKeys.ENCODING, "UTF-8");
		return prop;
	}

	@Override
	protected List<Param> createParameters() {
		return Activator.getPreferenceAsParam(PARAM_KEYS);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see jp.sourceforge.edocbook.ui.popup.actions.AbstractCreateAction#createTemlates()
	 */
	@Override
	protected List<Template> createTemlates() {
		return Activator.getPreferenceAsTemplate(TEMPLATE_KEYS);
	}
}
