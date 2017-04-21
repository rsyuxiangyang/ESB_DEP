
package org.fbi.endpoint.mbp.domain.queryresultrequest;

import org.fbi.endpoint.mbp.domain.ClientRequestRoot;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "head",
    "param"
})
@XmlRootElement(name = "root")
public class QueryResultRequestRoot extends ClientRequestRoot {

    @XmlElement(name = "Param", required = true)
    protected QueryResultRequestParam param;

    public QueryResultRequestParam getParam() {
        return param;
    }

    public void setParam(QueryResultRequestParam value) {
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
