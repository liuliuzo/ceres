package com.mastercard.ceres.filter.factory;

import com.mastercard.ceres.filter.CeresFilter;

/**
 * @className DefaultFilterFactory
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class DefaultFilterFactory implements CeresFilterFactory {
    @Override
    public CeresFilter newInstance(Class clazz) throws InstantiationException, IllegalAccessException {
        return (CeresFilter) clazz.newInstance();
    }
}
