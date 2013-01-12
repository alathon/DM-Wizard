package com.awesomeware.dm.code.preferences.pages;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.awesomeware.dm.code.Activator;
import com.awesomeware.dm.code.preferences.CompilerPreferenceConstants;

public class CompilerPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public CompilerPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("BYOND compiler locations");
	}
	
	@Override
	public void init(IWorkbench workbench) {}

	@Override
	protected void createFieldEditors() {
		addField(new FileFieldEditor(CompilerPreferenceConstants.DM_COMPILER_LOCATION, "DM &Compiler Location:", getFieldEditorParent()));
		addField(new FileFieldEditor(CompilerPreferenceConstants.DREAM_SEEKER_LOCATION, "Dream &Seeker Location:", getFieldEditorParent()));
		addField(new FileFieldEditor(CompilerPreferenceConstants.DREAM_DAEMON_LOCATION, "Dream &Daemon Location:", getFieldEditorParent()));
	}
}
