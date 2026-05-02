package com.gba.dating4e.core.photo;

import com.gba.dating4e.core.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.Optional;

@Service
public class PhotoService {

    private final FileSystem fs;

    @Autowired
    public PhotoService(FileSystem fs){
        this.fs = fs;
    }

    // retorna un Optional, puede o no puede retornar una foto
    public Optional<byte[]> download(String name){
        try{
            // delegamos la tarea de recuperar el archivo a descargar al FileSystem
            // envolvemos el array de byte en un Optional.
            return Optional.of(fs.load(name + ".jpg"));
        }catch (UncheckedIOException e){
            // si se produce un excepcion al cargar la imagen, retornamos Optional.empty()
            return Optional.empty();
        }
    }
}
