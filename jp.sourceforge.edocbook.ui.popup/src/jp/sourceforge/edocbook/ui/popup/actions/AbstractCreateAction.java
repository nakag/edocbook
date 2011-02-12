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
package jp.sourceforge.edocbook.ui.popup.actions;

import java.util.List;
import java.util.Properties;

import jp.sourceforge.edocbook.core.DocbookFile;
import jp.sourceforge.edocbook.core.DocbookXsl;
import jp.sourceforge.edocbook.core.EDocbookRuntimeException;
import jp.sourceforge.edocbook.core.Param;
import jp.sourceforge.edocbook.core.Template;
import jp.sourceforge.edocbook.ui.popup.Activator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * AbstractCreateAction
 * 
 * @author nakaG
 * 
 */
public abstract class AbstractCreateAction implements IObjectActionDelegate {

	/** IWorkbenchPart */
	private IWorkbenchPart part;
	/** the current selection file */
	protected IFile currentSelection;

	/**
	 * create parameteres
	 * 
	 * @return the list of param
	 */
	protected abstract List<Param> createParameters();

	/**
	 * create templates
	 * 
	 * @return the list of Template
	 */
	protected abstract List<Template> createTemlates();

	/**
	 * create output properties
	 * 
	 * @return the properties
	 */
	protected abstract Properties createOutputProperties();

	/**
	 * create xsl file.
	 * 
	 * @return the docbook xsl file
	 */
	protected abstract DocbookXsl createXslFile();

	/**
	 * the constructor
	 */
	public AbstractCreateAction() {
		super();
	}

	/**
	 * {inheritDoc}
	 * 
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
	 *      org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		part = targetPart;
	}

	/**
	 * get the select file
	 * 
	 * @return IFile
	 */
	protected IFile getSelection() {
		if (currentSelection != null) {
			return currentSelection;
		}
		ISelectionProvider selectionProvider = part.getSite()
				.getSelectionProvider();
		ISelection selection = selectionProvider.getSelection();
		currentSelection = getSelectionFile(selection);
		return currentSelection;
	}

	private IFile getSelectionFile(ISelection selection) {
		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;
			if (!(structuredSelection.getFirstElement() instanceof IResource)) {
				return null;
			}

			IResource resource = (IResource) structuredSelection
					.getFirstElement();
			if (resource == null || resource.getType() != IResource.FILE) {
				return null;
			}

			return (IFile) resource;
		} else if (part instanceof IEditorPart) {
			Object obj = ((IEditorPart) part).getEditorInput().getAdapter(
					IFile.class);
			if (obj == null) {
				System.out.println("obj is null.");
				return null;
			}
			return (IFile) obj;
		} else {
			return null;
		}

	}

	/**
	 * {inheritDoc}
	 * 
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		currentSelection = getSelectionFile(selection);
	}

	/**
	 * get docbook file.
	 * 
	 * @return the docbook file
	 */
	protected DocbookFile getSourceFile() {
		IFile iFile = getSelection();
		if (iFile != null) {
			return new DocbookFile(iFile.getLocation().toString());
		}
		return null;
	}

	/**
	 * get project that contain the docbook file
	 * 
	 * @return project of the docbook file
	 */
	private IProject getProject() {
		IFile iFile = getSelection();
		if (iFile == null) {
			return null;
		}
		IContainer container = iFile.getParent();
		if (container == null) {
			return null;
		}
		return container.getProject();
	}

	/**
	 * get output directory, and create output directory if specify and not
	 * exists.
	 * 
	 * @return the output directory
	 */
	protected String getOutputDirectory() {
		String directory = Activator.getOutputDirectory();
		if (directory.length() == 0) {
			return null;
		}
		IProject project = getProject();
		if (project == null) {
			return null;
		}
		IFolder folder = project.getFolder(directory);
		if (!folder.exists()) {
			try {
				folder.create(false, true, null);
			} catch (CoreException e) {
				Activator.showErrorDialog(e);
				throw new EDocbookRuntimeException(e);
			}
		}
		return folder.getLocation().toString();
	}

	/**
	 * reflesh project that contains the docbook file.
	 */
	protected void reflesh() {
		try {
			IProject project = getProject();
			if (project != null) {
				project.refreshLocal(IResource.DEPTH_INFINITE, null);
			}
		} catch (CoreException e) {
			Activator.showErrorDialog(e);
			throw new EDocbookRuntimeException(e);
		}
	}

	/**
	 * @return the part
	 */
	protected IWorkbenchPart getPart() {
		return part;
	}

}