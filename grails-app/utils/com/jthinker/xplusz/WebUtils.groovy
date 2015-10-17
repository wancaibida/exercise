package com.jthinker.xplusz

import org.apache.commons.lang.math.NumberUtils

/**
 * User: Gang Chen
 * Date: 2015/10/17 22:08
 */
class WebUtils {

    static int param(Map<?, ?> params, String key, int ... defaults) {
        return NumberUtils.toInt((String) params.get(key), defaults.length > 0 ? defaults[0] : 0);
    }
}
