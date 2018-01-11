package com.goff.email_desktop.email;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class EmailDefaultBody {
    static final String DEFAULT_BODY_FILE = System.getProperty("user.home") + "/.emailDefaultBody.txt";
    private static final Path PATH_TO_DEFAULT_BODY_FILE = Paths.get(DEFAULT_BODY_FILE);

    public static void define(final String emailMessage) {
        try {
            Files.write(PATH_TO_DEFAULT_BODY_FILE, emailMessage.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (final IOException e) {
            throw new RuntimeException("Failed to write in .emailDefaultBody.txt file.", e);
        }
    }

    public static String read() {
        if(!Files.exists(PATH_TO_DEFAULT_BODY_FILE)){
        	return "";
        }
    	try {
            return new String(Files.readAllBytes(PATH_TO_DEFAULT_BODY_FILE), StandardCharsets.UTF_8);
        } catch (final IOException e) {
            throw new RuntimeException("Failed to read .emailDefaultBody.txt file.", e);
        }
    }

}
