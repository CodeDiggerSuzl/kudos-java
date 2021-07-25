package netty.ch2filechannel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

// TODO: 底层用了零拷贝
public class TestFileChannelTransfer {
    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("learn-netty/data.txt").getChannel();
             FileChannel to = new FileOutputStream("learn-netty/to.txt").getChannel()
        ) {
            // NOTE 底层用了 0 拷贝进行优化,
            // ! 一次只能传输 2G 的数据 下面的 for 改进了代码, 多次传输
            long size = from.size();
            for (long left = size; left > 0; ) {
                // 从哪里开始, 传多少, 到哪里
                long transferred = from.transferTo((size - left), left, to);
                left -= transferred;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
