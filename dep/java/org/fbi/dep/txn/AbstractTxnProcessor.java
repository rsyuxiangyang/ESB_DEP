package org.fbi.dep.txn;

import org.fbi.dep.model.base.TiaXml;
import org.fbi.dep.model.base.ToaXml;
import org.fbi.endpoint.sbs.domain.SOFFormBody;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 交易处理
 */
public abstract class AbstractTxnProcessor implements TxnProcessor {
    public abstract String process(String userid, String msgData) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException;

    protected void copyFormBodyToToa(SOFFormBody formBody, ToaXml toa) {
        try {
            Field[] fields = formBody.getClass().getFields();
            Class toaCLass = toa.getClass();
            Object obj = null;
            for (Field field : fields) {
                obj = field.get(formBody);
                if (obj != null) {
                    Field toaField = toaCLass.getField(field.getName());
                    if (toaField != null) {
                        toaField.set(toa, obj);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("TxnProcessor copyFormBodyToToa 解析异常");
        }
    }

    public void copyTiaInfoToToa(TiaXml tia, ToaXml toa) {
        try {
            Field[] fields = tia.getClass().getFields();
            Class toaCLass = toa.getClass();
            Object obj = null;
            for (Field field : fields) {
                if ("info".equals(field.getName())){
                    obj = field.get(tia);
                    if (obj != null) {
                        Field toaField = toaCLass.getField(field.getName());
                        if (toaField != null) {
                            Field[] tiaInfoFields = Class.forName(field.getType().getName()).newInstance().getClass().getFields();
                            Class toaInfoCLass = Class.forName(toaCLass.getField(field.getName()).getType().getName()).newInstance().getClass();
                            Object tiaInfoObj = null;
                            for (Field tiaInfofield : tiaInfoFields) {
                                tiaInfoObj = tiaInfofield.get(obj);
                                if (tiaInfoObj != null) {
                                    Field toaInfoField = toaInfoCLass.getField(tiaInfofield.getName());
                                    if (toaInfoField != null) {
                                        toaInfoField.set(toaField.get(toa), tiaInfoObj);
                                    }
                                }
                            }
                        }
                    }
                }else{
                    continue;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("DepAbstractTxnProcessor copyTiaInfoToToa 解析异常");
        }
    }
}
