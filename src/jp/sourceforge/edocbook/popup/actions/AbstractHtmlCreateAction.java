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
package jp.sourceforge.edocbook.popup.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.transform.OutputKeys;

import jp.sourceforge.edocbook.Activator;
import jp.sourceforge.edocbook.EDocbookRuntimeException;
import jp.sourceforge.edocbook.model.DocbookFile;
import jp.sourceforge.edocbook.model.ResultFile;
import jp.sourceforge.edocbook.model.XslFile;
import jp.sourceforge.edocbook.transform.HtmlTransformer;

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
	/** the xsl */
	private XslFile xslFile;
	/** transformer */
	private HtmlTransformer transformer;

	public AbstractHtmlCreateAction() {
		transformer = new HtmlTransformer(createXslFile());
	}

	/**
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
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
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
			e.printStackTrace();
			throw new EDocbookRuntimeException(e);
		}
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		try {
			DocbookFile source = getSourceFile();
			if (source == null) {
				return;
			}
			ResultFile result = new ResultFile(source
					.getReplaceExtensionFile("html"));

			transformer.transform(source, result);

			reflesh();
		} catch (EDocbookRuntimeException e) {
			Activator.showErrorDialog(e);
		}
	}

	protected abstract XslFile createXslFile();

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

	protected Map<String, Object> createParameters() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("use.extensions", "1");
		return params;
	}
}
