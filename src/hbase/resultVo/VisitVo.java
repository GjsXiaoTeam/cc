package hbase.resultVo;

import java.util.List;

/**
 * Created by SEELE on 2017/5/6.
 */
public class VisitVo {
	private List<Integer> visitCount;     //次数
    private int totalCount;     //总次数
	public List<Integer> getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(List<Integer> visitCount) {
		this.visitCount = visitCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
