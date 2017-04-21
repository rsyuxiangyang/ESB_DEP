package org.fbi.dep.transform;

import org.fbi.dep.model.base.TIA;
import org.fbi.dep.util.PropertyManager;

/**
 * Created by IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 12-2-13
 * Time: ����9:12
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractTiaTransform {
    protected static boolean DEP_IS_RUNNING_DEBUG = !"0".equals(PropertyManager.getProperty("dep.running.debug"));
    protected static String LOCAL_IP =PropertyManager.getProperty("dep.local.ip");
    public abstract Object transform(TIA tia);
}
