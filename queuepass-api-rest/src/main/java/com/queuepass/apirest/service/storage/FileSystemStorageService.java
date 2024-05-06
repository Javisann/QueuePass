package com.queuepass.apirest.service.storage;

import com.queuepass.apirest.error.storage.StorageException;
import com.queuepass.apirest.error.storage.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {


    private final Path rootLocation;

    public FileSystemStorageService(@Value("${system.storage.dirname}") String dirname) {
        this.rootLocation = Path.of(dirname);
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            String originalFilename = file.getOriginalFilename();

            String filenameWithoutSpaces = originalFilename != null ? originalFilename.replaceAll("\\s+", "_") : null;

            String filename = filenameWithoutSpaces;

            int number = 0;
            String extension = "";
            int dotIndex = filename.lastIndexOf('.');
            if (dotIndex != -1) {
                extension = filename.substring(dotIndex);
                filename = filename.substring(0, dotIndex);
            }

            // Verifica si ya existe un archivo con el mismo nombre
            while (existsResource(filename + (number == 0 ? "" : "(" + number + ")") + extension)) {
                number++;
            }

            // Agrega el número entre paréntesis al final del nombre antes de la extensión si el número es mayor que 0
            filename = filename + (number == 0 ? "" : "(" + number + ")") + extension;


            Path destinationFile = this.rootLocation.resolve(Paths.get(Objects.requireNonNull(filename))).normalize().toAbsolutePath();

            if (!destinationFile.startsWith(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return filename;
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }


    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public boolean existsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            return Files.exists(file);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}

