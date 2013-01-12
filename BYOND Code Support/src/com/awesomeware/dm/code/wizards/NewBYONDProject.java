package com.awesomeware.dm.code.wizards;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.ide.undo.CreateProjectOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;

import com.awesomeware.dm.nature.BYONDNature;

public class NewBYONDProject extends Wizard implements INewWizard {

	private WizardNewProjectCreationPage _pageOne;
	
	private IProject project;
	
	public NewBYONDProject() {
		setWindowTitle("New BYOND Project");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void addPages() {
		super.addPages();
		_pageOne = new WizardNewProjectCreationPage("BYOND Project Wizard");
		_pageOne.setTitle("Default BYOND Project");
		_pageOne.setDescription("Creates a BYOND project with the default layout.");
		addPage(_pageOne);
	}

	@Override
	public boolean performFinish() {
		createProject();
		return true;
	}
	
	private IProject createProject() {
		if (project != null) {
			return project;
		}
		
		final IProject newP = _pageOne.getProjectHandle();
		
		URI location = null;
		if (!_pageOne.useDefaults()) {
			location = _pageOne.getLocationURI();
		}
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IProjectDescription description = workspace.newProjectDescription(newP.getName());
		description.setLocationURI(location);
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				CreateProjectOperation op = new CreateProjectOperation(description, "New BYOND Project");
				try {
					op.execute(monitor, WorkspaceUndoUtil.getUIInfoAdapter(getShell()));
				} catch (ExecutionException e) {
					throw new InvocationTargetException(e);
				}
			}
		};
		
		try {
			getContainer().run(true, true, op);
			if (!newP.hasNature(BYONDNature.NATURE_ID)) {
				final IProjectDescription d2 = newP.getDescription();
				String[] prevNatures = d2.getNatureIds();
				String[] newNatures = new String[prevNatures.length + 1];
				System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
				newNatures[prevNatures.length] = BYONDNature.NATURE_ID;
				d2.setNatureIds(newNatures);
				newP.setDescription(d2, null);
			}
			project = newP;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return project;
	}
}
