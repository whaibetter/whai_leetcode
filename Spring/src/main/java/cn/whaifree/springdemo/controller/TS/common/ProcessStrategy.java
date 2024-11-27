package cn.whaifree.springdemo.controller.TS.common;

public interface ProcessStrategy {
    void process(byte[] frame);

    void process(Object o);
}
