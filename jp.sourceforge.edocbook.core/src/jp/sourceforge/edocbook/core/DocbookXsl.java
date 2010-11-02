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
package jp.sourceforge.edocbook.core;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 * DocbookXsl
 * 
 * @author nakaG
 * 
 */
public class DocbookXsl {
	/** xsl:output properties */
	private Properties outputProperties;
	/** xsl:param parameter */
	private Map<String, String> parameters;
	/** doctype systemid */
	private String rootDir;
	private List<String> importXslList = new ArrayList<String>();
	private Map<String, String> templateList = new HashMap<String, String>();
	private String resultFileExtension;

	/**
	 * The defalut constructor
	 */
	public DocbookXsl() {
		this.rootDir = Activator.getResourceAsUrlString("lib/docbook-xsl");
	}

	/**
	 * The constructor
	 * 
	 * @param rootDir the path of root directory of docbook-xsl
	 * @param outputProperties the attributes of &lt;xsl:output&gt;
	 */
	public DocbookXsl(String rootDir, Properties outputProperties,
			Map<String, String> parameters) {
		this.rootDir = rootDir;
		this.outputProperties = outputProperties;
		this.parameters = parameters;
	}

	/**
	 * get Source
	 * 
	 * @return the Source of xsl
	 */
	private Source getSource() {
		XslBuilder builder = new XslBuilder();
		for (String s : importXslList) {
			builder.addImport(s);
		}
		for (Map.Entry<String, String> entry : templateList.entrySet()) {
			builder.addTemplate(entry.getKey(), entry.getValue());
		}
		return new StreamSource(new ByteArrayInputStream(builder.getXslString()
				.getBytes()), rootDir);// + "/virtual.xsl");
	}

	public void addImport(String importXsl) {
		importXslList.add(importXsl);
	}

	public void addTemplate(String name, String body) {
		templateList.put(name, body);
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
	public Map<String, String> getParameters() {
		return parameters;
	}

	public ResultFile apply(DocbookFile docbook) {
		DocbookTransformer transformer = new DocbookTransformer(getSource());
		for (Map.Entry<Object, Object> entry : outputProperties.entrySet()) {
			transformer.setOutputProperty((String) entry.getKey(),
					(String) entry.getValue());
		}
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			transformer.setParameter(entry.getKey(), entry.getValue());
		}
		return transformer.transform(docbook, resultFileExtension);

	}

	/**
	 * @return the resultFileExtension
	 */
	public String getResultFileExtension() {
		return resultFileExtension;
	}

	/**
	 * @param resultFileExtension
	 *            the resultFileExtension to set
	 */
	public void setResultFileExtension(String resultFileExtension) {
		this.resultFileExtension = resultFileExtension;
	}

	/**
	 * @param outputProperties the outputProperties to set
	 */
	public void setOutputProperties(Properties outputProperties) {
		this.outputProperties = outputProperties;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
}
