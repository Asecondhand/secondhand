package com.secondhand.util.fileUtil;

/**
 * @author Erica
 * @since 2020/3/8
 */
public class IDHelper {

    /**
     * 创建一个雪花算法的值
     * @return
     */
    public static long createSnowFlateWorker(){
        IdWorker idWorker = new IdWorker(0,0);
        return idWorker.nextId();
    }
}
