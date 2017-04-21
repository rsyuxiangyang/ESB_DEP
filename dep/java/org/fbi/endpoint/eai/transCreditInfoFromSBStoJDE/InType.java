
package org.fbi.endpoint.eai.transCreditInfoFromSBStoJDE;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>inType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="inType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="szNumber_DL01" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="szClearDate_DL02" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="szZongAccountNumber_DL03" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="szZongAccountNumber_DL04" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="szClearAccount_DL05" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="szClearDirection_DL06" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="szVersionR04110ZA_DL07" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="szVersionR03B551_DL08" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inType", propOrder = {
    "szNumberDL01",
    "szClearDateDL02",
    "szZongAccountNumberDL03",
    "szZongAccountNumberDL04",
    "szClearAccountDL05",
    "szClearDirectionDL06",
    "szVersionR04110ZADL07",
    "szVersionR03B551DL08"
})
public class InType {

    @XmlElement(name = "szNumber_DL01", required = true)
    protected String szNumberDL01;
    @XmlElement(name = "szClearDate_DL02", required = true)
    protected String szClearDateDL02;
    @XmlElement(name = "szZongAccountNumber_DL03", required = true)
    protected String szZongAccountNumberDL03;
    @XmlElement(name = "szZongAccountNumber_DL04", required = true)
    protected String szZongAccountNumberDL04;
    @XmlElement(name = "szClearAccount_DL05", required = true)
    protected String szClearAccountDL05;
    @XmlElement(name = "szClearDirection_DL06", required = true)
    protected String szClearDirectionDL06;
    @XmlElement(name = "szVersionR04110ZA_DL07", required = true)
    protected String szVersionR04110ZADL07;
    @XmlElement(name = "szVersionR03B551_DL08", required = true)
    protected String szVersionR03B551DL08;

    /**
     * ��ȡszNumberDL01���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzNumberDL01() {
        return szNumberDL01;
    }

    /**
     * ����szNumberDL01���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzNumberDL01(String value) {
        this.szNumberDL01 = value;
    }

    /**
     * ��ȡszClearDateDL02���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzClearDateDL02() {
        return szClearDateDL02;
    }

    /**
     * ����szClearDateDL02���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzClearDateDL02(String value) {
        this.szClearDateDL02 = value;
    }

    /**
     * ��ȡszZongAccountNumberDL03���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzZongAccountNumberDL03() {
        return szZongAccountNumberDL03;
    }

    /**
     * ����szZongAccountNumberDL03���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzZongAccountNumberDL03(String value) {
        this.szZongAccountNumberDL03 = value;
    }

    /**
     * ��ȡszZongAccountNumberDL04���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzZongAccountNumberDL04() {
        return szZongAccountNumberDL04;
    }

    /**
     * ����szZongAccountNumberDL04���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzZongAccountNumberDL04(String value) {
        this.szZongAccountNumberDL04 = value;
    }

    /**
     * ��ȡszClearAccountDL05���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzClearAccountDL05() {
        return szClearAccountDL05;
    }

    /**
     * ����szClearAccountDL05���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzClearAccountDL05(String value) {
        this.szClearAccountDL05 = value;
    }

    /**
     * ��ȡszClearDirectionDL06���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzClearDirectionDL06() {
        return szClearDirectionDL06;
    }

    /**
     * ����szClearDirectionDL06���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzClearDirectionDL06(String value) {
        this.szClearDirectionDL06 = value;
    }

    /**
     * ��ȡszVersionR04110ZADL07���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzVersionR04110ZADL07() {
        return szVersionR04110ZADL07;
    }

    /**
     * ����szVersionR04110ZADL07���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzVersionR04110ZADL07(String value) {
        this.szVersionR04110ZADL07 = value;
    }

    /**
     * ��ȡszVersionR03B551DL08���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzVersionR03B551DL08() {
        return szVersionR03B551DL08;
    }

    /**
     * ����szVersionR03B551DL08���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzVersionR03B551DL08(String value) {
        this.szVersionR03B551DL08 = value;
    }

}
