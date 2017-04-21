package org.fbi.endpoint.bestpay.com.bestpay.txn.base;

import java.util.List;

/**
 * Created by XIANGYANG on 2015-10-23.
 */
public class FasQueryListResponse {
    public String totalCount;
    public String totalPage;
//    public List<FasTransResponse> details;   与接口描述不符
    public List<FasTransResponse> results;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public List<FasTransResponse> getResults() {
        return results;
    }

    public void setResults(List<FasTransResponse> results) {
        this.results = results;
    }
}
