package com.agarwal.ashish.qna;

import com.facebook.stetho.Stetho;

public class QnaDbAccessApplication extends QnaApplication
{
	public static final String TAG = "QnaCustomDevApplication";

	@Override
	public void onCreate()
	{
		super.onCreate();
		Stetho.initialize(Stetho.newInitializerBuilder(this).enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
				.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build());
	}
}
