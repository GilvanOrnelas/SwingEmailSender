package com.goff.email_desktop.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public final class FileSeeker {

    public static Set<String> findFeedBackFilesIn(final String path) throws IOException {
        final Set<String> feedBackFiles = new HashSet<>();
        final Path rootPath = Paths.get(path);
        Files.walk(rootPath).filter(Files::isRegularFile)
                .filter(file -> file.getFileName().toString().endsWith("feedback.xls"))
                .forEach(file -> feedBackFiles.add(file.toAbsolutePath().toString()));
        return feedBackFiles;
    }

}
