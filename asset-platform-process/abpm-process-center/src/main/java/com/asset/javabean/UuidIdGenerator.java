package com.asset.javabean;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * UUID生成器
 * @since 2019/06/05
 * @author Haijie Hu
 */

public class UuidIdGenerator implements IDGenerator {

    // different ProcessEngines on the same classloader share one generator.
    protected static volatile TimeBasedGenerator timeBasedGenerator;

    public UuidIdGenerator() {
        ensureGeneratorInitialized();
    }

    protected void ensureGeneratorInitialized() {
        if (timeBasedGenerator == null) {
            synchronized (UuidIdGenerator.class) {
                if (timeBasedGenerator == null) {
                    timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
                }
            }
        }
    }

    public String generateId() {
        return timeBasedGenerator.generate().toString();
    }


    @Override
    public String generateID() {
        return generateId();
    }
}
