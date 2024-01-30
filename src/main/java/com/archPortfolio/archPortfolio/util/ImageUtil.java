package com.archPortfolio.archPortfolio.util;

import java.util.Base64;

public class ImageUtil {

	public static String encodeImage(byte[] imageData) {
		return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageData);
	}
}
