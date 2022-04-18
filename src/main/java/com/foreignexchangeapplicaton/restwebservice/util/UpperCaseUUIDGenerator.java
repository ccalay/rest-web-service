package com.foreignexchangeapplicaton.restwebservice.util;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

public class UpperCaseUUIDGenerator extends UUIDGenerator
{
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException
    {
        String uuid = super.generate(session, object).toString();
        return StringUtils.upperCase(uuid);
    }
}

