package com.div.util;

import org.springframework.web.multipart.MultipartFile;
import java.beans.PropertyEditorSupport;
import java.io.IOException;

public class MultipartFileEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
	}

	@Override
	public void setValue(Object value) {
		if (value instanceof MultipartFile) {
			MultipartFile file = (MultipartFile) value;
			try {
				setValue(file.getBytes());
			} catch (IOException e) {
				throw new IllegalArgumentException("Could not convert MultipartFile to byte[]", e);
			}
		} else {
			super.setValue(value);
		}
	}

	@Override
	public String getAsText() {
		byte[] bytes = (byte[]) getValue();
		return (bytes != null ? new String(bytes) : "");
	}

}
