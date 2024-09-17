package com.travelbuddy.common.utils;

import java.util.Optional;

public class FilenameUtils {
    public static Optional<String> getExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return Optional.empty();
        }
        int index = indexOfExtension(filename);
        if (index == -1) {
            return Optional.empty();
        } else {
            return Optional.of(filename.substring(index + 1));
        }
    }

    private static int indexOfExtension(String filename) {
        int extensionPos = filename.lastIndexOf('.');
        int lastSeparator = indexOfLastSeparator(filename);
        return lastSeparator > extensionPos ? -1 : extensionPos;
    }

    private static int indexOfLastSeparator(String filename) {
        int lastUnixPos = filename.lastIndexOf('/');
        int lastWindowsPos = filename.lastIndexOf('\\');
        return Math.max(lastUnixPos, lastWindowsPos);
    }
}
