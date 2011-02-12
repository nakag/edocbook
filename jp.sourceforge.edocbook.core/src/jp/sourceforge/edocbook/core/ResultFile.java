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

import java.io.File;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

/**
 * ResultFile
 * 
 * @author nakaG
 * 
 */
public class ResultFile {
	/** output file */
	private File resultFile;

	/**
	 * The constructor
	 * 
	 * @param source
	 *            the Docbook file
	 * @param path
	 *            the output path. if null, result files output the same path of
	 *            source.
	 * @param ext
	 *            the extension of the result files.
	 */
	public ResultFile(DocbookFile source, String path, String ext) {
		String systemId = source.getSystemId();
		int idx = systemId.lastIndexOf(".");
		String newPath = systemId.substring(0, idx) + "." + ext;
		// String separetor = System.getProperty("line.separator");
		if (path != null && path.length() != 0) {
			String fileName = newPath.substring(newPath.lastIndexOf("/"));
			newPath = path + fileName;
		}
		this.resultFile = new File(newPath);
	}

	/**
	 * get the Result
	 * 
	 * @return Result
	 */
	public Result getResult() {
		return new StreamResult(resultFile.toURI().getPath());
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "," + resultFile.getAbsolutePath();
	}

}
