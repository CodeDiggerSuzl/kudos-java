package com.codigger.samples;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
public class CompressDemo_2 {


        public static void main(String[] args) throws IOException {
            String originalText = "This is a long piece of text that we want to compress";
            byte[] compressedText = compress(originalText);
            String decompressedText = decompress(compressedText);
            System.out.println("Original text: " + originalText);
            System.out.println("Compressed text: " + new String(compressedText));
            System.out.println("Decompressed text: " + decompressedText);
        }

        public static byte[] compress(String text) throws UnsupportedEncodingException, IOException {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            gzipOutputStream.write(text.getBytes("UTF-8"));
            gzipOutputStream.close();
            return outputStream.toByteArray();
        }

        public static String decompress(byte[] compressedText) throws IOException {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedText);
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


