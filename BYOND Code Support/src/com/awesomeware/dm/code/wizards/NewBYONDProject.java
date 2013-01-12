package com.awesomeware.dm.code.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class NewBYONDProject extends Wizard implements INewWizard {

	private WizardNewProjectCreationPage _pageOne;
	
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
		return true;
	}
}
