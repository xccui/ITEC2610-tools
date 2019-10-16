package ca.yorku.itec.C2610.a1;

import java.io.File;
import java.util.Objects;

/**
 * GradingRunner
 */
public class GradingRunner {
    public static void main(String[] args) {
        String basePath = "/Users/xccui/Desktop/2610/";
        String destBase = "/Users/xccui/Desktop/marked/";
        File baseDir = new File(basePath);

        GradingTool gradingTool = new GradingTool(
                new File(basePath + "/test/Input.txt"),
                new File(basePath + "/test/Output.txt"));

        int i = 0;
        int marked = 0;
        for (File userDir : Objects.requireNonNull(baseDir.listFiles(file -> file.isDirectory() && !file.getName().equals("test")))) {
            System.out.println(userDir);
            File sourcePath = Objects.requireNonNull(userDir.listFiles(File::isDirectory))[0];
//                   System.out.println(sourcePath);
//                   System.out.println(Arrays.toString(sourcePath.listFiles(file -> file.getName().endsWith("java"))));
            File[] tempFiles;
            while(Objects.requireNonNull(sourcePath.listFiles((dir, name) -> name.endsWith("java"))).length < 1) {
               tempFiles =  Objects.requireNonNull(sourcePath.listFiles(f -> f.isDirectory() && !f.getName().equals("__MACOSX")));
               if (tempFiles.length > 0) {
                   sourcePath = tempFiles[0];
               } else {
                   sourcePath = null;
                   break;
               }
            }
            if (null != sourcePath) {
                System.out.println(++i + " " + sourcePath.getPath());
                System.out.print("Result: ");
                double result = gradingTool.getMatchRate(sourcePath.toString());
                System.out.println(result);
                if (result > 0.99) {
                    ++marked;
                    System.out.println("All correct!!!");
//                try {
//                    File destFile = new File(destBase + "/" + userDir.getName());
//                    System.out.println("Move to " + destFile.getPath());
//                    Files.move(userDir.toPath(), destFile.toPath());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                } else if (result > 0) {
                    ++marked;
                } else if (result == GradingTool.MISSING_FILE) {
                    System.out.println("Missing File!");
                } else if (result == GradingTool.COMPILE_ERROR) {
                    System.out.println("Compile error!");
                } else if (result == GradingTool.RUN_ERROR) {
                    System.out.println("Running error!");
                } else if (result == GradingTool.NO_OUTPUT) {
                    System.out.println("No output!");
                } else if (result == GradingTool.COMPARE_ERROR) {
                    System.out.println("Compare error!");
                } else {
                    System.out.println("Other error!");
                }
            } else {
                System.out.println(++i + " " + userDir);
                System.out.println("Cannot find source files!");
            }
            System.out.println("======================================\n");
        }
        System.out.println("Marked: " + marked);
    }
}
