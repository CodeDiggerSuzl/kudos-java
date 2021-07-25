package netty.ch2filechannel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TestWalkFileTree {
    public static void main(String[] args) throws IOException {
        // /**
        // * 删除多级目录
        // */
        // Files.walkFileTree(Paths.get(""), new SimpleFileVisitor<>() {
        //     @Override
        //     public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        //         // 删除文件
        //         Files.delete(file);
        //         return super.visitFile(file, attrs);
        //     }
        //     @Override
        //     public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        //         Files.delete(dir);
        //         return super.postVisitDirectory(dir, exc);
        //     }
        // });

        /**
         * 拷贝文件
         */
        String from = "from";
        String to = "to";
        Files.walk(Paths.get(from)).forEach(path -> {
            String targetName = path.toString().replace(from, to);
            if (Files.isDirectory(path)) {
                try {
                    Files.createDirectory(Paths.get(targetName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (Files.isRegularFile(path)) {
                try {
                    Files.copy(path, Paths.get(targetName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
