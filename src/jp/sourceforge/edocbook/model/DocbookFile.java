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
package jp.sourceforge.edocbook.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 * DocbookFile
 * 
 * @author nakG
 * 
 */
public class DocbookFile {
	/** docbook file */
	private File sourceFile;

	/**
	 * The constructor
	 * 
	 * @param file
	 *            docbook file
	 */
	public DocbookFile(File file) {
		this.sourceFile = file;
	}

	/**
	 * get the source
	 * 
	 * @return Source
	 */
	public Source getSource() {
		return new StreamSource(getInputStream());
	}

	/**
	 * get InputStream from sourceFile
	 * 
	 * @return inputStream or null
	 */
	private InputStream getInputStream() {
		try {
			return sourceFile.toURI().toURL().openStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * get file replaced extension
	 * 
	 * @param ext
	 *            extension to replace
	 * @return the file named fileName + extension
	 */
	public File getReplaceExtensionFile(String ext) {
		int idx = sourceFile.getAbsolutePath().lastIndexOf(".");
		String newPath = sourceFile.getAbsolutePath().substring(0, idx) + "."
				+ ext;
		return new File(newPath);
	}

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "," + sourceFile.getAbsolutePath();
	}

}
