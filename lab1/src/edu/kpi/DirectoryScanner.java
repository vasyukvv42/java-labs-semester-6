package edu.kpi;

import java.io.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.*;

public class DirectoryScanner implements Callable<List<String>> {
    private String fileExtension;
    private File directory;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(8);

    public DirectoryScanner(String fileExtension, File directory) {
        if (directory == null || !directory.isDirectory()) {
            throw new InvalidParameterException("Not a directory");
        }

        this.fileExtension = fileExtension;
        this.directory = directory;

    }

    private void lexemesToUppercase(File file) throws IOException {
        final var sb = new StringBuilder();
        try (var in = new FileInputStream(file)) {
            var scanner = new Scanner(in);

            while (scanner.hasNextLine()) {

                var oldLine = scanner.nextLine();
                for (String lexeme : oldLine.split(" ")) {
                    if (lexeme.length() > 3)
                        sb.append(lexeme.toUpperCase());
                    else
                        sb.append(lexeme);

                    sb.append(" ");
                }

                sb.append(System.lineSeparator());
            }
        }

        try (var out = new FileOutputStream(file)) {
            out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    public List<String> call() {
        System.out.println("New task started for directory " + directory.getAbsolutePath());

        var result = new ArrayList<String>();

        try {
            var futures = new ArrayList<Future<List<String>>>();

            for (var file: Objects.requireNonNull(directory.listFiles())) {
                if (file.isDirectory()) {
                    var dirScanner = new DirectoryScanner(fileExtension, file);
                    var future = threadPool.submit(dirScanner);
                    futures.add(future);
                } else {
                    var name = file.getName();
                    var extension = name.substring(name.lastIndexOf('.') + 1);
                    if (extension.equals(fileExtension)) {
                        lexemesToUppercase(file);
                        result.add(file.getAbsolutePath());
                    }
                }
            }

            for (var future: futures) {
                result.addAll(future.get());
            }
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
