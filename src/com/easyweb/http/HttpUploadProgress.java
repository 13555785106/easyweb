package com.easyweb.http;

import java.io.File;

public interface HttpUploadProgress {
	void onStart(File file);

	void onProgress(File file, int increment);

	void onFinish(File file);
}
