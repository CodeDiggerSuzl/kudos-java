package netty.ch2filechannel;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NOTE 遍历文件树(不用递归了, 有现成的 api)
 */
public class TestPaths {
    public static void main(String[] args) throws IOException {
        AtomicInteger dirCnt = new AtomicInteger();
        AtomicInteger fileCnt = new AtomicInteger();

        // NOTE 访问者模式
        Files.walkFileTree(Paths.get("learn-netty"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                dirCnt.incrementAndGet();
                System.out.println("----" + dir.getFileName());
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                fileCnt.incrementAndGet();
                System.out.println(file.getFileName());
                return super.visitFile(file, attrs);
            }
        });
        System.out.println(dirCnt.get());
        System.out.println(fileCnt.get());
    }
}
