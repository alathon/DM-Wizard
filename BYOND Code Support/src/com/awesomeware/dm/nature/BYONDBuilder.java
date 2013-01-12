package com.awesomeware.dm.nature;

import java.io.File;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;

import com.awesomeware.dm.code.Activator;
import com.awesomeware.dm.code.preferences.CompilerPreferenceConstants;

public class BYONDBuilder extends IncrementalProjectBuilder {

	public static final String BUILDER_ID = "com.awesomeware.dm.builder";
	private static final String dmCompilerError = "Could not find a suitable DM compiler to use. Please configure this setting in workspace preferences.";

	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		if (kind == FULL_BUILD) {
			IPreferenceStore store = Activator.getDefault().getPreferenceStore();
			String dm = store.getString(CompilerPreferenceConstants.DM_COMPILER_LOCATION);
			if (dm == null || dm.isEmpty() || !new File(dm).exists() || !new File(dm).canExecute()) {
				boolean marked = false;
				for (IMarker marker : getProject().findMarkers(IMarker.PROBLEM, false, 0)) {
					String msg = (String) marker.getAttribute(IMarker.MESSAGE);
					if (msg != null && msg.equals(dmCompilerError)) {
						marked = true;
						break;
					}
				}
				if (!marked) {
					IMarker marker = getProject().createMarker(IMarker.PROBLEM);
					marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					marker.setAttribute(IMarker.MESSAGE, dmCompilerError);
				}
			} else {
				for (IMarker marker : getProject().findMarkers(IMarker.PROBLEM, false, 0)) {
					String msg = (String) marker.getAttribute(IMarker.MESSAGE);
					if (msg != null && msg.equals(dmCompilerError)) {
						marker.delete();
					}
				}
			}
		}
		return new IProject[0];
	}
}
