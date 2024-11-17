package com.travelbuddy.upload.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StorageExecutorServiceImpl implements StorageExecutorService{
    private final AsyncTaskExecutor taskExecutor;

    private final StorageService storageService;

    public StorageExecutorServiceImpl(AsyncTaskExecutor taskExecutor,
                                      StorageService storageService) {
        this.taskExecutor = taskExecutor;
        this.storageService = storageService;
    }

    @Override
    public void makeFilePermanent(String id) {
        execute(() -> storageService.makeFilePermanent(id));
    }

    @Override
    public void deleteFile(String id) {
        execute(() -> storageService.deleteFile(id));
    }

    @Override
    public void deleteFiles(List<String> mediaIdsToDelete) {
        execute(() -> storageService.deleteFiles(mediaIdsToDelete));
    }

    private void execute(Runnable runnable) {
        taskExecutor.execute(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                log.error("Error while storage task, ERROR: [{}]", e.getMessage(), e);
            }
        });
    }
}
