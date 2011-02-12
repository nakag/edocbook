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

import jp.sourceforge.edocbook.core.DocbookXsl;

/**
 * MultipleHtmlCreateAction
 * 
 * @author nakaG
 * 
 */
public class MultipleHtmlCreateAction extends AbstractHtmlCreateAction {

	/**
	 * Constructor for MultipleHtmlCreateAction.
	 */
	public MultipleHtmlCreateAction() {
		super();
	}

	/**
	 * {inheritDoc}
	 * 
	 * @see jp.sourceforge.edocbook.ui.popup.html.actions.AbstractHtmlCreateAction#createXslFile()
	 */
	@Override
	protected DocbookXsl createXslFile() {
		DocbookXsl xsl = new DocbookXsl();
		xsl.setOutputProperties(createOutputProperties());
		xsl.setParameters(createParameters());
		xsl.setTemplates(createTemlates());
		xsl.setResultFileExtension("html");
		xsl.addImport("html/chunk.xsl");
		return xsl;
	}
}
