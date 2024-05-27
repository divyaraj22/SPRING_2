package com.div.util;

import java.util.Base64;

public class ImageUtil {
	public static String getBase64EncodedImage(byte[] imageData, String contentType) {
		if (imageData == null || imageData.length == 0) {
			return null;
		}

		String base64Image;
		if (contentType.equals("image/jpeg") || contentType.equals("image/jpg")) {
			base64Image = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageData);
		} else if (contentType.equals("image/png")) {
			base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(imageData);
		} else {
			return null;
		}

		return base64Image;
	}
}
