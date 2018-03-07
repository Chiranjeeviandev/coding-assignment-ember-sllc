package com.chiranjeevi.mvvm.codingassignment;

import rx.Scheduler;


public interface SchedulerProvider {
    Scheduler UI();
    Scheduler IO();
}
