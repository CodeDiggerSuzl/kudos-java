package netty.ch1basic;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 处理粘包/半包问题
 */
public class TestByteBufferExam {
    public static void main(String[] args) {
        /*
         网络上有多条数据发送给服务端，数据之间使用 \n 进行分隔
         但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为
             Hello,world\n
             I'm zhangsan\n
             How are you?\n
         变成了下面的两个 byteBuffer (黏包，半包)
             Hello,world\nI'm zhangsan\nHo
             w are you?\n
         现在要求你编写程序，将错乱的数据恢复成原始的按 \n 分隔的数据
         */
        // *粘包 发送方一次发的多

        // *半包问题 接收方 ByteBuffer 大小有限

        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes(StandardCharsets.UTF_8));
        split(source);
        source.put("w are you?\n".getBytes(StandardCharsets.UTF_8));
        split(source);

    }

    /**
     * ! 重要代码 底层
     *
     * @param source bf
     */
    private static void split(ByteBuffer source) {
        source.flip(); // 读模式
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                // 找到消息的长度
                int len = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(len);
                // from source to target
                for (int j = 0; j < len; j++) {
                    target.put(source.get(j));
                }

            }
        }
        source.compact();
    }
}
