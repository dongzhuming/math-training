package org.molecule.tools.mathtraining.util;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * @author Dong Zhuming
 */
public class SnowFlakeIdGenerator extends IdentityGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws MappingException {
        return SnowFlakeIdHelper.nextId();

    }
}
