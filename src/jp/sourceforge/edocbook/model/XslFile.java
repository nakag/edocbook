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
package jp.sourceforge.edocbook.model;

import java.util.Map;
import java.util.Properties;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 * XslFile
 * 
 * @author nakaG
 * 
 */
public class XslFile {
	/** xsl:output properties */
	private Properties outputProperties;
	/** xsl:param parameter */
	private Map<String, Object> parameters;
	/** doctype systemid */
	private String systemId;
	
	/**
	 * The constructor
	 * 
	 * @param xslURL
	 */
	public XslFile(String systemId, Properties outputProperties, Map<String, Object> parameters) {
		this.systemId = systemId;
		this.outputProperties = outputProperties;
		this.parameters = parameters;
	}

	/**
	 * get Source
	 * 
	 * @return the Source of xsl
	 */
	public Source getSource() {
		return new StreamSource(systemId);
	}

	/**
	 * @return the outputProperties
	 */
	public Properties getOutputProperties() {
		return outputProperties;
	}

	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}	
	
}
