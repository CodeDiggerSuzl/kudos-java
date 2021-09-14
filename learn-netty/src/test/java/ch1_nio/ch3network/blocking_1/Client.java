package ch1_nio.ch3network.blocking_1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("localhost", 8080));
        channel.write(Charset.defaultCharset().encode("hello world !"));
        System.out.println("waiting..");
        System.in.read();
    }
}
