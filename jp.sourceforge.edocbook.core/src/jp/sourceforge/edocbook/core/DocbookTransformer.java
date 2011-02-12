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
package jp.sourceforge.edocbook.core;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

/**
 * DocbookTransformer
 * 
 * @author nakaG
 * 
 */
public class DocbookTransformer {
	/** the transformer */
	private Transformer transformer;

	/**
	 * the constructor
	 * 
	 * @param xsl
	 *            the source of xsl
	 */
	public DocbookTransformer(Source xsl) {
		transformer = createTransformer(xsl);
	}

	/**
	 * transform docbook to something
	 * 
	 * @param source
	 *            the docbook file
	 * @param result
	 *            the result file
	 * @throws EDocbookRuntimeException
	 */
	public void transform(DocbookFile source, ResultFile result) {
		try {
			assert transformer != null;

			transformer.transform(source.getSource(), result.getResult());
		} catch (TransformerException e) {
			e.printStackTrace();
			throw new EDocbookRuntimeException(e);
		}
	}

	/**
	 * set the attribute of &lt;xsl:output&gt;
	 * 
	 * @param key
	 *            the name of attribute
	 * @param value
	 *            the value of attribute
	 */
	public void setOutputProperty(String key, String value) {
		transformer.setOutputProperty(key, value);
	}

	/**
	 * set the parameter of &lt;xsl:param&gt;
	 * 
	 * @param param
	 *            the name of parameter
	 * @param value
	 *            the value of parameter
	 */
	public void setParameter(String param, String value) {
		transformer.setParameter(param, value);
	}

	private Transformer createTransformer(Source xsl) {
		TransformerFactory tf = TransformerFactory.newInstance();
		// tf.setURIResolver(new URIResolver() {
		//
		// /*
		// * (non-Javadoc)
		// *
		// * @see javax.xml.transform.URIResolver#resolve(java.lang.String,
		// * java.lang.String)
		// */
		// @Override
		// public Source resolve(String arg0, String arg1)
		// throws TransformerException {
		// System.out.println("arg0=" + arg0);
		// System.out.println("arg1=" + arg1);
		// URI f = null;
		// if (arg0 == null || arg0.length() == 0) {
		// return null;
		// }
		//
		// try {
		// String fileName = null;
		// String systemId = null;
		// int idx = arg0.indexOf("/");
		// if (idx > 0) {
		// int i2 = arg0.indexOf("lib/docbook-xsl/");
		//
		// if (i2 > 0) {
		// fileName = "lib/docbook-xsl"
		// + arg0.substring(i2).substring(
		// "lib/docbook-xsl/".length());
		// } else {
		// fileName = "lib/docbook-xsl" + arg0.substring(idx);
		// }
		// } else {
		// fileName = "lib/docbook-xsl/html/" + arg0;
		// }
		// System.out.println(fileName);
		// systemId = fileName.substring(0, fileName.lastIndexOf("/"));
		// System.out.println(systemId);
		// f = Activator.getDefault().getBundle().getEntry(fileName)
		// .toURI();
		// if (f != null) {
		// return new StreamSource(f.toURL().openStream(),
		// Activator.getDefault().getBundle().getEntry(systemId).toString());
		// }
		// } catch (Throwable e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// return null;
		// }
		// return null;
		// }
		// });
		try {
			Transformer transformer = tf.newTransformer(xsl);
			assert transformer != null;

			return transformer;
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new EDocbookRuntimeException(e);
		}
	}
}
