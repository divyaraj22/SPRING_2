package com.div.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlDateEditor extends PropertyEditorSupport {

	private final SimpleDateFormat dateFormat;

	public SqlDateEditor(String dateFormat) {
		this.dateFormat = new SimpleDateFormat(dateFormat);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			java.util.Date parsedDate = dateFormat.parse(text);
			setValue(new java.sql.Date(parsedDate.getTime()));
		} catch (ParseException e) {
			setValue(null);
		}
	}
}
