package com.chiranjeevi.mvvm.codingassignment;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import rx.Scheduler;
import rx.schedulers.Schedulers;


public class TestSchedulerProvider implements SchedulerProvider {
    @Override
    public Scheduler UI() {
        return Schedulers.from(new Executor() {
            @Override
            public void execute(@NonNull Runnable command) {
                command.run();
            }
        });
    }

    @Override
    public Scheduler IO() {
        return Schedulers.from(new Executor() {
            @Override
            public void execute(@NonNull Runnable command) {
                command.run();
            }
        });
    }
}
