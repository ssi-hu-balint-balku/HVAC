package com.training.fakes;

import com.training.SocketWrapper;

public class SocketSpy extends SocketWrapper {
    public boolean startCalled;
    public boolean closeCalled;

    public SocketSpy() {
        super(8080);
    }

    @Override
    public void start() {
        this.startCalled = true;
    }

    @Override
    public void close() {
        this.closeCalled = true;
    }
}