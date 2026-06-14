package com.gba.dating4e.core.photo;

/**
 * Abstraemos el concepto miniatura de su tipo concreto AwtBicubicThumbnail
 * Tipo base Thumbnail.
 * Si un programa necesita algo de basic type thumbnail, spring provee el tipo concreto durante la injeccion
 */
public interface Thumbnail {
    byte[] thumbnail(byte[] imageBytes);
}
