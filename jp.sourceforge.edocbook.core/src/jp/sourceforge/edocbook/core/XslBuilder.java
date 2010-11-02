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

/**
 * XSL String Builder.
 * 
 * @author nakaG
 * 
 */
public class XslBuilder {
	private StringBuilder builder = new StringBuilder();

	public XslBuilder() {
		reset();
	}

	public void reset() {
		builder.delete(0, builder.length());
		builder
				.append("<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:exsl=\"http://exslt.org/common\" version=\"1.0\" exclude-result-prefixes=\"exsl\">");
	}

	public void addImport(String href) {
		builder.append("<xsl:import href=\"" + escape(href) + "\"/>");
	}

	public void addTemplate(String name, String body) {
		builder.append("<xsl:template name=\"" + name + "\">");
		builder.append(escape(body));
		builder.append("</xsl:template> ");
	}

	public String getXslString() {
		return builder.toString() + "</xsl:stylesheet>";
	}
	private String escape(String source) {
		return source.replace("\"", "\\\"");
	}
}
