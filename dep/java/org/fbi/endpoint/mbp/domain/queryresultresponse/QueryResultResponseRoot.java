
package org.fbi.endpoint.mbp.domain.queryresultresponse;


import org.fbi.endpoint.mbp.domain.ClientResponseRoot;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "head",
    "param"
})
@XmlRootElement(name = "root")
public class QueryResultResponseRoot extends ClientResponseRoot {
    @XmlElement(name = "Param", required = true)
    protected QueryResultResponseParam param;

    public QueryResultResponseParam getParam() {
        return param;
    }


    public void setParam(QueryResultResponseParam value) {
        this.param = value;
    }

    @Override
    public String toString() {
        return "Root{" +
                "head=" + head +
                ", param=" + param +
                '}';
    }
}
