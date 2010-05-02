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
 */
package jp.sourceforge.edocbook.popup.actions;

import jp.sourceforge.edocbook.Activator;
import jp.sourceforge.edocbook.model.XslFile;

/**
 * SingleHtmlCreateAction
 * 
 * @author nakaG
 * 
 */
public class SingleHtmlCreateAction extends AbstractHtmlCreateAction {

	/**
	 * Constructor for SingleHtmlCreateAction.
	 */
	public SingleHtmlCreateAction() {
		super();
	}

	/**
	 * @see jp.sourceforge.edocbook.popup.actions.AbstractHtmlCreateAction#createXslFile()
	 */
	@Override
	protected XslFile createXslFile() {
		return new XslFile(Activator
				.getResourceAsUrlString("lib/docbook-xsl/html/docbook.xsl"),
				createOutputProperties(), createParameters());
	}
}
