package com.gba.dating4e.core.photo;

import com.gba.dating4e.core.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.Optional;
import java.util.UUID;

/**
 * PhotoService interactua con FileSystem y AwtBicubicThumbnail
 * FileSystem <----uses--- |PhotoService| ---uses---> AwtBicubicThumbnail
 */
@Service
public class PhotoService {

    private final FileSystem fs;
    // tenemos dos candidatos de implementacion: AwtBicubicThumbnail, AwtNearestNeighborThumbnail
    @Qualifier("qualityThumbnailRenderer")
    private final Thumbnail thumbnail;

    // constructor injection
    @Autowired
    public PhotoService(FileSystem fs, Thumbnail thumbnail){
        this.fs = fs;
        this.thumbnail = thumbnail;
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

    /**
     *
     * @param imageBytes -> imagen que se va a guardar
     * @return
     */
    public String upload(byte[] imageBytes){
        String imageName = UUID.randomUUID().toString();
        // 1. almacenamos la imagen original
        fs.store(imageName + ".jpg", imageBytes);
        // 2. almacenamos una miniatura
        byte[] thumbnailBytes = thumbnail.thumbnail(imageBytes);
        fs.store(imageName + ".jpg", thumbnailBytes);
        return imageName;
    }
}
