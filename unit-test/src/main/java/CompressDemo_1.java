import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressDemo_1 {
    public static void main(String[] args) throws IOException {
        String originalText = "This is a long piece of text that we want to llllllllllllllllllllllllllllllllllllllllllllllllllmap to a short text";
        String compressedText = compress(originalText);
        String restoredText = decompress(compressedText);
        System.out.println("Original text: " + originalText);
        System.out.println("Compressed text: " + compressedText);
        System.out.println("Restored text: " + restoredText);
    }

    public static String compress(String text) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
        gzipOutputStream.write(text.getBytes("UTF-8"));
        gzipOutputStream.close();
        byte[] compressedText = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(compressedText);
    }

    public static String decompress(String compressedText) throws IOException {
        byte[] compressedBytes = Base64.getDecoder().decode(compressedText);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedBytes);
        GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = gzipInputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        gzipInputStream.close();
        return new String(outputStream.toByteArray(), "UTF-8");
    }
}