package org.ruan;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class QRCodeService {
	private String generateQrCode(String paymentData) throws WriterException, IOException{
		int width = 300;
		int height = 300;
		String fileType = "png";

		//cria o qrcode
		BitMatrix bitMatrix = new MultiFormatWriter().encode(paymentData, BarcodeFormat.QR_CODE, width, height);

		//converte o qrcode para imagem
		BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

		//converte para 64bits
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(qrCodeImage, fileType, outputStream);
		String qrCodeBase64 = Base64.getEncoder().encodeToString(outputStream.toByteArray());

		return qrCodeBase64;

	}
}
