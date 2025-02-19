package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.serailizer.StreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private StreamSerializer streamSerializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        Objects.requireNonNull(dir, "Directory must not be null.");
        this.streamSerializer = streamSerializer;
        directory = Paths.get(dir);
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory.");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Path dir) {
        try {
            streamSerializer.doWrite(r, new BufferedOutputStream(Files.newOutputStream(dir)));
        } catch (IOException e) {
            throw new StorageException("File update error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path dir) {
        return Files.exists(dir);
    }

    @Override
    protected void doSave(Resume r, Path dir) {
        try {
            Files.createFile(dir);
            streamSerializer.doWrite(r, new FileOutputStream(dir.toFile()));
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + dir, r.getUuid(), e);
        }
        doUpdate(r, dir);
    }

    @Override
    protected Resume doGet(Path dir) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(dir)));
        } catch (IOException e) {
            throw new StorageException("File read error", getPathName(dir), e);
        }
    }

    @Override
    protected void doDelete(Path dir) {
        try {
            Files.delete(dir);
        } catch (IOException e) {
            throw new StorageException("Path dir delete error ", getPathName(dir), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return getFilesList(directory).map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getFilesList(directory).forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList(directory).count();
    }

    private String getPathName(Path path) {
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList(Path directory) {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", e);
        }
    }
}
