package com.mastercard.ceres.filter.factory;

import com.mastercard.ceres.filter.CeresFilter;

/**
 * @className CeresFilterFactory
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public interface  CeresFilterFactory {
    CeresFilter newInstance(Class<?> clazz) throws Exception;
}
