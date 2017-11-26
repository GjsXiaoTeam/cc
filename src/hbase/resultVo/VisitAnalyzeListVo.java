package hbase.resultVo;

public class VisitAnalyzeListVo {
    private int visitCount;      //访问次数
    private int codPercent;      //上架商品数量
    private int pageView;        //访问量
    private int userView;       //访客数
    private int monthReBuy;      //下单件数
    public int getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	public int getCodPercent() {
		return codPercent;
	}
	public void setCodPercent(int codPercent) {
		this.codPercent = codPercent;
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
	public int getMonthReBuy() {
		return monthReBuy;
	}
	public void setMonthReBuy(int monthReBuy) {
		this.monthReBuy = monthReBuy;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	private float totalAmount;      //下单金额

}
