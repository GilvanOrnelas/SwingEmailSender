package com.goff.email_desktop.email.report;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ReportMapper {

    private static final String PROPERTY_FILE =
            System.getProperty("user.home") + "/.reportEmailMapping.properties";
    private static Properties EMAIL_PATH_MAPPER;

    static void load() throws IOException {
        EMAIL_PATH_MAPPER = new Properties();
        criarArquivoDePropriedades();
        final InputStream propertyFile = new FileInputStream(PROPERTY_FILE);
        EMAIL_PATH_MAPPER.load(propertyFile);
    }

    public static void add(final String keyEmail, final String valueReportPath) throws IOException {
        EMAIL_PATH_MAPPER.setProperty(keyEmail, valueReportPath);
        save();
    }

    public static String get(final String email) {
        return EMAIL_PATH_MAPPER.getProperty(email);
    }

    public static void remove(final String email) throws FileNotFoundException, IOException {
        EMAIL_PATH_MAPPER.remove(email);
        save();

    }

    private static void criarArquivoDePropriedades() throws IOException {
        final Path path = Paths.get(PROPERTY_FILE);
        if (Files.exists(path)) {
            return;
        }
        Files.createFile(path);
    }

    private static void save() throws IOException, FileNotFoundException {
        try (final OutputStream propertiesFile = new FileOutputStream(PROPERTY_FILE);) {
            EMAIL_PATH_MAPPER.store(propertiesFile, "");
        }
    }
}
