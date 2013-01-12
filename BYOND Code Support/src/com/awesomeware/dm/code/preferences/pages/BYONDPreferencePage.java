package com.awesomeware.dm.code.preferences.pages;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.awesomeware.dm.code.Activator;

public class BYONDPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public BYONDPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Preferences for BYOND support");
	}
	
	@Override
	public void init(IWorkbench workbench) {}

	@Override
	protected void createFieldEditors() {}
}
