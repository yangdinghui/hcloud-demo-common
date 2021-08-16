package hcloud.demo.utils;

import java.util.Random;

/**
 * @author tomato
 * @version 1.0
 * @desc ❄️
 * @date 2021/8/3 19:14
 */
public class IdWorker {

    private final long workerId;
    private final long datacenterId;
    private final long idepoch;
    private static final long workerIdBits = 5L;
    private static final long datacenterIdBits = 5L;
    private static final long maxWorkerId = 31L;
    private static final long maxDatacenterId = 31L;
    private static final long sequenceBits = 12L;
    private static final long workerIdShift = 12L;
    private static final long datacenterIdShift = 17L;
    private static final long timestampLeftShift = 22L;
    private static final long sequenceMask = 4095L;
    private long lastTimestamp;
    private long sequence;
    private static final Random r = new Random();

    public IdWorker() {
        this(1288834974657L);
    }

    public IdWorker(long idepoch) {
        this((long) r.nextInt(31), (long) r.nextInt(31), 0L, idepoch);
    }

    public IdWorker(long workerId, long datacenterId, long sequence) {
        this(workerId, datacenterId, sequence, 1344322705519L);
    }

    public IdWorker(long workerId, long datacenterId, long sequence, long idepoch) {
        this.lastTimestamp = -1L;
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
        this.idepoch = idepoch;
        if (workerId >= 0L && workerId <= 31L) {
            if (datacenterId >= 0L && datacenterId <= 31L) {
                if (idepoch >= System.currentTimeMillis()) {
                    throw new IllegalArgumentException("idepoch is illegal: " + idepoch);
                }
            } else {
                throw new IllegalArgumentException("datacenterId is illegal: " + workerId);
            }
        } else {
            throw new IllegalArgumentException("workerId is illegal: " + workerId);
        }
    }

    public long getDatacenterId() {
        return this.datacenterId;
    }

    public long getWorkerId() {
        return this.workerId;
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public Long getId() {
        long id = this.nextId();
        return id;
    }

    private synchronized long nextId() {
        long timestamp = this.timeGen();
        if (timestamp < this.lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards.");
        } else {
            if (this.lastTimestamp == timestamp) {
                this.sequence = this.sequence + 1L & 4095L;
                if (this.sequence == 0L) {
                    timestamp = this.tilNextMillis(this.lastTimestamp);
                }
            } else {
                this.sequence = 0L;
            }

            this.lastTimestamp = timestamp;
            long id = timestamp - this.idepoch << 22 | this.datacenterId << 17 | this.workerId << 12 | this.sequence;
            return id;
        }
    }

    public long getIdTimestamp(long id) {
        return this.idepoch + (id >> 22);
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for (timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("IdWorker{");
        sb.append("workerId=").append(this.workerId);
        sb.append(", datacenterId=").append(this.datacenterId);
        sb.append(", idepoch=").append(this.idepoch);
        sb.append(", lastTimestamp=").append(this.lastTimestamp);
        sb.append(", sequence=").append(this.sequence);
        sb.append('}');
        return sb.toString();
    }
}
