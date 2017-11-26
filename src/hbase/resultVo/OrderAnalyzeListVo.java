package hbase.resultVo;

public class OrderAnalyzeListVo {
    private int dealPercent;             //下单件数
    private int codPercent;              //上架商品数量
    private int weekReVisitPercent;      //访问量
    private int monthReVisitPercent;      //访客数
    private int monthReBuy;             //下单件数
	public int getDealPercent() {
		return dealPercent;
	}
	public void setDealPercent(int dealPercent) {
		this.dealPercent = dealPercent;
	}
	public int getCodPercent() {
		return codPercent;
	}
	public void setCodPercent(int codPercent) {
		this.codPercent = codPercent;
	}
	public int getWeekReVisitPercent() {
		return weekReVisitPercent;
	}
	public void setWeekReVisitPercent(int weekReVisitPercent) {
		this.weekReVisitPercent = weekReVisitPercent;
	}
	public int getMonthReVisitPercent() {
		return monthReVisitPercent;
	}
	public void setMonthReVisitPercent(int monthReVisitPercent) {
		this.monthReVisitPercent = monthReVisitPercent;
	}
	public int getMonthReBuy() {
		return monthReBuy;
	}
	public void setMonthReBuy(int monthReBuy) {
		this.monthReBuy = monthReBuy;
	}

}
