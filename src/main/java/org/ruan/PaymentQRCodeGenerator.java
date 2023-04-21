package org.ruan;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.WriterException;
import java.util.HashMap;
import java.util.Map;

public class PaymentQRCodeGenerator {

	private static final String PAYMENT_AMOUNT = "10.00";
	private static final String PAYMENT_RECEIVER = "ruan.diego@exemplo.com";

	public String genarteQRCode() {
		String qrCodeData = "upi://pay?pa=" + PAYMENT_RECEIVER + "&am=" + PAYMENT_AMOUNT;

		int qrCodeWidth = 350;
		int qrCodeHeight = 350;

		Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		hintMap.put(EncodeHintType.MARGIN, 1);

		try {
			BitMatrix matrix = new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, qrCodeWidth, qrCodeHeight, hintMap);

			String qrCodeImage = MatrixToImageWriter.writeToPath(matrix, "png");
			return qrCodeImage;
		}   catch (WriterException e) {
			System.err.println("Erro ao gerar o QR code: " + e.getMessage());
			return null;
		}

	}
}
