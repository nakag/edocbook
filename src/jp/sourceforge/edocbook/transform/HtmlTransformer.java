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
package jp.sourceforge.edocbook.transform;

import java.util.Map;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import jp.sourceforge.edocbook.EDocbookRuntimeException;
import jp.sourceforge.edocbook.model.DocbookFile;
import jp.sourceforge.edocbook.model.ResultFile;
import jp.sourceforge.edocbook.model.XslFile;

/**
 * HtmlTransformer
 * 
 * @author nakaG
 * 
 */
public class HtmlTransformer {
	/** the xsl */
	private XslFile xsl;
	private Transformer transformer;
	/**
	 * Constructor
	 * 
	 * @param xsl
	 *            the xsl
	 */
	public HtmlTransformer(XslFile xsl) {
		this.xsl = xsl;
		this.transformer = createTransformer();		
	}

	/**
	 * transform docbook to something
	 * 
	 * @param source
	 *            the docbook file
	 * @param result
	 *            the result file
	 */
	public void transform(DocbookFile source, ResultFile result) {
		try {
			System.out.println(source.toString());
			System.out.println(result.toString());
			assert transformer != null;

			transformer.setOutputProperties(xsl.getOutputProperties());
			setupParameter(transformer);

			transformer.transform(source.getSource(), result.getResult());
		} catch (TransformerException e) {
			e.printStackTrace();
			throw new EDocbookRuntimeException(e);
		}
	}

	private void setupParameter(Transformer transformer) {
		for (Map.Entry<String, Object> entry : xsl.getParameters().entrySet()) {
			transformer.setParameter(entry.getKey(), entry.getValue());
		}
	}

	private Transformer createTransformer() {
		TransformerFactory tf = TransformerFactory.newInstance();
//		tf.setURIResolver(new URIResolver() {
//
//			/*
//			 * (non-Javadoc)
//			 * 
//			 * @see javax.xml.transform.URIResolver#resolve(java.lang.String,
//			 * java.lang.String)
//			 */
//			@Override
//			public Source resolve(String arg0, String arg1)
//					throws TransformerException {
//				System.out.println("arg0=" + arg0);
//				System.out.println("arg1=" + arg1);
//				URI f = null;
//				if (arg0 == null || arg0.length() == 0) {
//					return null;
//				}
//
//				try {
//					String fileName = null;
//					String systemId = null;
//					int idx = arg0.indexOf("/");
//					if (idx > 0) {
//						int i2 = arg0.indexOf("lib/docbook-xsl/");
//
//						if (i2 > 0) {
//							fileName = "lib/docbook-xsl"
//									+ arg0.substring(i2).substring(
//											"lib/docbook-xsl/".length());
//						} else {
//							fileName = "lib/docbook-xsl" + arg0.substring(idx);
//						}
//					} else {
//						fileName = "lib/docbook-xsl/html/" + arg0;
//					}
//					System.out.println(fileName);
//					systemId = fileName.substring(0, fileName.lastIndexOf("/"));
//					System.out.println(systemId);
//					f = Activator.getDefault().getBundle().getEntry(fileName)
//							.toURI();
//					if (f != null) {
//						return new StreamSource(f.toURL().openStream(), Activator.getDefault().getBundle().getEntry(systemId).toString());
//					}
//				} catch (Throwable e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					return null;
//				}
//				return null;
//			}
//		});
		try {
			Transformer transformer = tf.newTransformer(xsl.getSource());
			assert transformer != null;

			return transformer;
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new EDocbookRuntimeException(e);
		}
	}
}
