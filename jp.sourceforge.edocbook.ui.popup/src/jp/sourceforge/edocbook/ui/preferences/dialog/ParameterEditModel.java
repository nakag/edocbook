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
package jp.sourceforge.edocbook.ui.preferences.dialog;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Model for editing of xsl:param
 * 
 * @author nakaG
 * 
 */
public class ParameterEditModel {
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	public static final String PROPERTY_NAME = "_parameter_name";
	public static final String PROPERTY_VALUE = "_parameter_value";
	/** the name attribute of xsl:param */
	private String name = "";
	/** the select attribute of xsl:param */
	private String value = "";

	/**
	 * The constructor
	 */
	public ParameterEditModel() {
	}

	/**
	 * The constructor
	 * 
	 * @param name
	 *            the name of xsl:param
	 * @param value
	 *            the select attribute of xsl:param
	 */
	public ParameterEditModel(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		String oldValue = this.name;
		this.name = name;
		firePropertyChange(PROPERTY_NAME, oldValue, name);
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		String oldValue = this.value;
		this.value = value;
		firePropertyChange(PROPERTY_VALUE, oldValue, value);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	public void firePropertyChange(String propName, Object oldValue,
			Object newValue) {
		listeners.firePropertyChange(propName, oldValue, newValue);
	}

}
