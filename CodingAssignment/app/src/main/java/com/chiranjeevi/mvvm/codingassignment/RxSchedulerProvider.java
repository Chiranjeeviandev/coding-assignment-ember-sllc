package com.chiranjeevi.mvvm.codingassignment;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RxSchedulerProvider implements SchedulerProvider {
    @Override
    public Scheduler UI() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler IO() {
        return Schedulers.io();
    }
}
