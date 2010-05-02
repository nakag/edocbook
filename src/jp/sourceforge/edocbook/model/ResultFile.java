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
	 * @param file
	 *            output file
	 */
	public ResultFile(File file) {
		this.resultFile = file;

	}

	/**
	 * get the Result
	 * 
	 * @return Result
	 */
	public Result getResult() {
		return new StreamResult(resultFile.toURI().getPath());
	}

	@Override
	public String toString() {
		return super.toString() + "," + resultFile.getAbsolutePath();
	}

}
