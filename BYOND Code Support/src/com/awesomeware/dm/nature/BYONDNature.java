package com.awesomeware.dm.nature;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class BYONDNature implements IProjectNature {
	
	private IProject project;

	@Override
	public void configure() throws CoreException {
		
	}

	@Override
	public void deconfigure() throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public void setProject(IProject project) {
		this.project = project;
	}
}
