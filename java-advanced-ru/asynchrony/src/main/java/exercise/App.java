package exercise;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.io.File;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String path1, String path2, String pathResult) {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> readFile(path1));
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> readFile(path2));

        var resultFuture = future1.thenCombine(future2, (text1, text2) -> {
            String resultText = text1 + text2;
            writeToFile(pathResult, resultText);
            return resultText;
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });

        return resultFuture;
    }

    private static String readFile(String filePath) {
        String result = null;

        try {
            Path path = Paths.get(filePath).toAbsolutePath().normalize();
            result = Files.readString(path);
        } catch (NoSuchFileException e) {
                System.out.println("NoSuchFileException exception: " + e.getStackTrace());
        } catch (IOException e) {
            System.out.println("readFile exception: " + e.getStackTrace());
        }

        return result;
    }

    private static void writeToFile(String filePath, String fileContent) {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        try {
            Files.write(path, fileContent.getBytes());
        } catch (IOException e) {
            System.out.println("writeFile exception: " + e.getStackTrace());
        }
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles("src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/file3.txt");
        result.get();
        // END
    }
}

