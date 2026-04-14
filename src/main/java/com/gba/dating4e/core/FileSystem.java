package com.gba.dating4e.core;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Component for an abstraction of a file System for store picture in file system
 *
 */
@Service
public class FileSystem {
    // root directory of logical file system
    private final Path root = Paths.get(System.getProperty("user.home")).resolve("fs");

    public FileSystem(){
        try{
            if (!Files.isDirectory(root)){
                Files.createDirectory(root);
            }
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    /**
     * @return free space
     */
    public long getFreeDiskSpace(){
        return root.toFile().getFreeSpace();
    }

    public byte[] load(String fileName){
        try{
            return Files.readAllBytes(root.resolve(fileName));
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    public void store(String fileName, byte[] bytes){
        try{
            Files.write(root.resolve(fileName), bytes);
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }
}
