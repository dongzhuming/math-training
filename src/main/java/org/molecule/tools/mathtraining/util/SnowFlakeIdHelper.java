package org.molecule.tools.mathtraining.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dong Zhuming
 */
@Slf4j
public class SnowFlakeIdHelper {

    private static final String ABNORMAL_CLOCK_MOVE = "Clock moved backwards. Refusing to generate instanceId for %d milliseconds";

    private static long sequence = 0L;
    private static final long twepoch = 1288834974657L;
    private static final long nodeIdBits = 3L;
    private static final long appIdBits = 5L;
    private static final long maxNodeId = ~(-1L << nodeIdBits);
    private static final long maxAppId = ~(-1L << appIdBits);
    private static final long sequenceBits = 10L;
    private static final long nodeIdShift = sequenceBits;
    private static final long appIdShift = sequenceBits + nodeIdBits;
    private static final long timestampLeftShift = sequenceBits + nodeIdBits + appIdBits;
    private static final long sequenceMask = ~(-1L << sequenceBits);
    private static long lastTimestamp = -1L;

    static synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(ABNORMAL_CLOCK_MOVE, lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) | sequence;
    }


    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private static long timeGen() {
        return System.currentTimeMillis();
    }
}
