package com.travelbuddy.upload.cloud;

import java.util.List;

/**
 * Tasks that are executed by this service are not critical and can be executed asynchronously.
 */
public interface StorageExecutorService {
    void makeFilePermanent(String id);
    void deleteFile(String id);

    void deleteFiles(List<String> mediaIdsToDelete);
}
