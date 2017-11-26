package hbase.resultVo;

public class WsSysDataResultVo {

	//时间维度 
	private String reportTime;
	//浏览量
	private int pageView;
	//访客数
	private int userView;
	//访问次数
	private int visitView;
	//平均访问深度
	private float visitDepth;
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public int getPageView() {
		return pageView;
	}
	public void setPageView(int pageView) {
		this.pageView = pageView;
	}
	public int getUserView() {
		return userView;
	}
	public void setUserView(int userView) {
		this.userView = userView;
	}
	public int getVisitView() {
		return visitView;
	}
	public void setVisitView(int visitView) {
		this.visitView = visitView;
	}
	public float getVisitDepth() {
		return visitDepth;
	}
	public void setVisitDepth(float visitDepth) {
		this.visitDepth = visitDepth;
	}

}
