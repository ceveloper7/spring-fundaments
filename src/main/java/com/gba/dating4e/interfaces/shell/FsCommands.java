package com.gba.dating4e.interfaces.shell;

import com.gba.dating4e.core.FileSystem;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.unit.DataSize;

/**
 * Componente que permite ejecutar comando en el sistema de archivos
 */
@ShellComponent
public class FsCommands {

    // FsCommand se encarga de la creacion del objeto FileSystem
    private final FileSystem fs = new FileSystem();

    // call command: free-disk-space
    @ShellMethod("Free disk space")
    public String freeDiskSpace(){
        return DataSize.ofBytes(fs.getFreeDiskSpace()).toGigabytes() + "GB";
    }

    // call command: minimum-free-disk-space
    @ShellMethod("Display required free disk space")
    public long minimumFreeDiskSpace(){
        return 1_000_000L;
    }

    // shell methods with parameters
    @ShellMethod("Convert to lowercase string")
    public String toLowerCase(String input){
        return input.toLowerCase();
    }
}
