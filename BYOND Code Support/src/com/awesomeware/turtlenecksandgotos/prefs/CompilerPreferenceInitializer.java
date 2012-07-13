package com.awesomeware.turtlenecksandgotos.prefs;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.IPreferencesService;

public class CompilerPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public CompilerPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferencesService preferencesService = Platform.getPreferencesService();
	}
}
