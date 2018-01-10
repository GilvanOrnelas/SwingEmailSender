package com.goff.email_desktop.email;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmailRepository {
    static final String DEFAULT_BODY_DIR = System.getProperty("user.home") + "/.emailManager/";

    public void save(final Email email) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final String emailJson = mapper.writeValueAsString(email);
            writeJsonFile(email, emailJson);
        } catch (final IOException e) {
            throw new RuntimeException("Failed to write in " + DEFAULT_BODY_DIR + email.getDestination(), e);
        }
    }

    public Email read(final String email) throws IOException {
        final byte[] jsonBytes = readAllBytes(findJsonPath(email));
        final ObjectMapper mapper = new ObjectMapper();
        final Email emailObject = mapper.readValue(jsonBytes, Email.class);
        return emailObject;
    }

    public List<Email> readAll() {
        Set<byte[]> jsonBytes;
        try {
            jsonBytes = findAllJsonFiles();
            final List<Email> emails = new ArrayList<>();
            for (final byte[] jsonByte : jsonBytes) {
                final ObjectMapper mapper = new ObjectMapper();
                final Email emailObject = mapper.readValue(jsonByte, Email.class);
                emails.add(emailObject);
            }
            return emails;
        } catch (final IOException e) {
            throw new RuntimeException("Failed to read file" + DEFAULT_BODY_DIR, e);
        }
    }

    public void remove(final Email email) {
        final Path path = findJsonPath(email.getDestination());
        try {
            Files.delete(path);
        } catch (final IOException e) {
            throw new RuntimeException(
                    "Failed to delete file" + DEFAULT_BODY_DIR + email.getDestination() + ".json", e);
        }
    }

    private Set<byte[]> findAllJsonFiles() throws IOException {
        final Path rootPath = Paths.get(DEFAULT_BODY_DIR);
        final Set<byte[]> jsonBytes = new LinkedHashSet<>();
        Files.walk(rootPath).filter(Files::isRegularFile)
                .filter(file -> file.getFileName().toString().endsWith(".json"))
                .forEach(file -> jsonBytes.add(readAllBytes(file)));
        return jsonBytes;
    }

    private Path findJsonPath(final String email) {
        final Path rootPath = Paths.get(DEFAULT_BODY_DIR);
        Path jsonFile;
        try {
            jsonFile = Files.walk(rootPath).filter(Files::isRegularFile)
                    .filter(file -> file.getFileName().toString().equals(email + ".json")).findFirst().get();
            return jsonFile;
        } catch (final IOException e) {
            throw new RuntimeException("Failed to read file" + DEFAULT_BODY_DIR + email + ".json", e);
        }
    }

    private byte[] readAllBytes(final Path jsonFile) {
        try {
            return Files.readAllBytes(jsonFile);
        } catch (final IOException e) {
            throw new RuntimeException("Failed to read file" + DEFAULT_BODY_DIR + jsonFile.getFileName(), e);
        }
    }

    private void writeJsonFile(final Email email, final String emailJson) throws IOException {

        Files.createDirectories(Paths.get(DEFAULT_BODY_DIR));
        Files.write(Paths.get(DEFAULT_BODY_DIR + email.getDestination() + ".json"),
                emailJson.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

}
