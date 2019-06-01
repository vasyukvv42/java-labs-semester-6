package edu.kpi;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		System.out.print("Enter directory path: ");
		var path = scanner.next();

		var file = new File(path);

		DirectoryScanner directoryScanner;
		try {
			directoryScanner = new DirectoryScanner("java", file);
		} catch (InvalidParameterException e) {
			System.err.println(e.getMessage());
			main(args);
			return;
		}

		var task = new FutureTask<>(directoryScanner);
		new Thread(task).start();

		List<String> fileNames;
		try {
			fileNames = task.get();
		} catch (InterruptedException | ExecutionException e) {
			System.err.println("Failed to execute this task! Please try again...");
			main(args);
			return;
		}

		for (var name : Objects.requireNonNull(fileNames)) {
			System.out.println(name);
		}
	}
}
