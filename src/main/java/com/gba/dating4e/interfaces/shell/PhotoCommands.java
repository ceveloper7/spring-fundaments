package com.gba.dating4e.interfaces.shell;

import com.gba.dating4e.core.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Se implementan una serie de comandos aplicables a fotos
 * La secuencia es
 *
 * PhotoCommands -- necesita --> PhotoService -- necesita --> FileSystem
 */

@ShellComponent
public class PhotoCommands {

    private final PhotoService photoService;

    @Autowired
    public PhotoCommands(PhotoService photoService){
        this.photoService = photoService;
    }

    /**
     * showPhoto pasa el nombre de la imagen y delega la tarea de recuperar la imagen a PhotoService
     *
     */
    @ShellMethod("Show photo")
    public String showPhoto(String name){
        return photoService.download(name)
                // si hay foto
                .map(bytes -> {
                   try{
                       var image = ImageIO.read(new ByteArrayInputStream(bytes));
                       // mostrarmos metadatos de la foto
                       return "Width: " + image.getWidth() + ", Height: " + image.getHeight();
                   }catch (IOException e){
                       return "Unableto read image dimensions";
                   }
                })
                .orElse("Image not found");
    }

    @ShellMethod("Upload photo")
    public String uploadPhoto(String fileName) throws IOException{
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        return "Uploaded" + photoService.upload(bytes);
    }

}
