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
package jp.sourceforge.edocbook.ui.popup.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.transform.OutputKeys;

import jp.sourceforge.edocbook.core.DocbookFile;
import jp.sourceforge.edocbook.core.DocbookXsl;
import jp.sourceforge.edocbook.core.EDocbookRuntimeException;
import jp.sourceforge.edocbook.ui.popup.Activator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
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
 * AbstractHtmlCreateAction
 * 
 * @author nakaG
 * 
 */
public abstract class AbstractHtmlCreateAction implements IObjectActionDelegate {
	/** IWorkbenchPart */
	private IWorkbenchPart part;

	/**
	 * the constructor
	 */
	public AbstractHtmlCreateAction() {
		// transformer = new DocbookTransformer(createXslFile());
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
	 * get theselect file
	 * 
	 * @return IFile
	 */
	private IFile getSelection() {
		ISelectionProvider selectionProvider = part.getSite()
				.getSelectionProvider();
		ISelection selection = selectionProvider.getSelection();
		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selectionProvider
					.getSelection();
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
	}

	protected DocbookFile getSourceFile() {
		IFile iFile = getSelection();
		if (iFile != null) {
			return new DocbookFile(iFile.getLocation().toString());
		}
		return null;
	}

	protected void reflesh() {
		try {
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(
					IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			Activator.showErrorDialog(e);
			throw new EDocbookRuntimeException(e);
		}
	}

	/**
	 * {inheritDoc}
	 * 
	 * @see IActionDelegate#run(IAction)
	 */
	@Override
	public void run(IAction action) {
		try {
			DocbookFile source = getSourceFile();
			if (source == null) {
				return;
			}
			// ResultFile result =
			createXslFile().apply(source);
			reflesh();
			Activator.showMessageDialog("Output completed.");
		} catch (EDocbookRuntimeException e) {
			Activator.showErrorDialog(e);
		}
	}

	protected abstract DocbookXsl createXslFile();

	protected Properties createOutputProperties() {
		// TODO get output property from anywhere(preference?)
		Properties prop = new Properties();
		prop.put(OutputKeys.DOCTYPE_PUBLIC,
				"-//W3C//DTD HTML 4.01 Transitional//EN");
		prop.put(OutputKeys.DOCTYPE_SYSTEM,
				"http://www.w3.org/TR/html4/loose.dtd");
		prop.put(OutputKeys.METHOD, "html");
		prop.put(OutputKeys.INDENT, "yes");
		prop.put(OutputKeys.ENCODING, "UTF-8");
		return prop;
	}

	protected Map<String, String> createParameters() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("use.extensions", "1");
		return params;
	}
}
