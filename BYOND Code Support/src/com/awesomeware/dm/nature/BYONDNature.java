package com.awesomeware.dm.nature;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class BYONDNature implements IProjectNature {
	
	public static final String NATURE_ID = "com.awesomeware.dm.nature";
	private IProject project;

	@Override
	public void configure() throws CoreException {
		IProjectDescription description = project.getDescription();
		ICommand[] commands = description.getBuildSpec();
		boolean needsBuilder = true;
		for (ICommand c : commands) {
			if (c.getBuilderName().equals(BYONDBuilder.BUILDER_ID)) {
				needsBuilder = false;
				break;
			}
		}
		if (needsBuilder) {
			ICommand command = description.newCommand();
		    command.setBuilderName(BYONDBuilder.BUILDER_ID);
		    ICommand[] nc = new ICommand[commands.length + 1];
		    System.arraycopy(commands, 0, nc, 1, commands.length);
		    nc[0] = command;
		    description.setBuildSpec(nc);
		    project.setDescription(description, null);
		}
	}

	@Override
	public void deconfigure() throws CoreException {
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
