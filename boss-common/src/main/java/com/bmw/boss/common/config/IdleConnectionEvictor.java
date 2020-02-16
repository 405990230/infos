package com.bmw.boss.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.springframework.beans.factory.annotation.Autowired;
@Slf4j
public class IdleConnectionEvictor extends Thread {

    //private static final Logger logger = LoggerFactory.getLogger(IdleConnectionEvictor.class);

    private final HttpClientConnectionManager connMgr;

    private volatile boolean shutdown;

    @Autowired
    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    public IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
        this.start();//启动线程
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    // 关闭失效的连接
                    PoolStats poolStats = poolingHttpClientConnectionManager.getTotalStats();
                    //log.info("========================poolStats.toString:"+poolStats.toString());
                    //log.info("========================>关闭失效的连接！！！");
                    connMgr.closeExpiredConnections();
                    //connMgr.closeIdleConnections(300, TimeUnit.SECONDS);
                }
            }
        } catch (InterruptedException ex) {
            // 结束
        }
    }
    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}