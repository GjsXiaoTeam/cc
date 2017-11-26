package hbase.resultVo;

import java.util.List;

public class TotalSaleAnalyzeVo {
    private double dealPercent;             //成交转化率
    private double codPercent;              //先款付款率
    private double weekReVisitPercent;      //7日回访率
    private double monthReVisitPercent;      //30日客户回头率
    private double monthReBuy;               //30日重复购买率
    private List<VisitAnalyzeListVo> visitAnalyzeList;      //访问次数分析列表
    private List<OrderAnalyzeListVo> orderAnalyzeList;      //下单件数分析列表
	public double getDealPercent() {
		return dealPercent;
	}
	public void setDealPercent(double dealPercent) {
		this.dealPercent = dealPercent;
	}
	public double getCodPercent() {
		return codPercent;
	}
	public void setCodPercent(double codPercent) {
		this.codPercent = codPercent;
	}
	public double getWeekReVisitPercent() {
		return weekReVisitPercent;
	}
	public void setWeekReVisitPercent(double weekReVisitPercent) {
		this.weekReVisitPercent = weekReVisitPercent;
	}
	public double getMonthReVisitPercent() {
		return monthReVisitPercent;
	}
	public void setMonthReVisitPercent(double monthReVisitPercent) {
		this.monthReVisitPercent = monthReVisitPercent;
	}
	public double getMonthReBuy() {
		return monthReBuy;
	}
	public void setMonthReBuy(double monthReBuy) {
		this.monthReBuy = monthReBuy;
	}
	public List<VisitAnalyzeListVo> getVisitAnalyzeList() {
		return visitAnalyzeList;
	}
	public void setVisitAnalyzeList(List<VisitAnalyzeListVo> visitAnalyzeList) {
		this.visitAnalyzeList = visitAnalyzeList;
	}
	public List<OrderAnalyzeListVo> getOrderAnalyzeList() {
		return orderAnalyzeList;
	}
	public void setOrderAnalyzeList(List<OrderAnalyzeListVo> orderAnalyzeList) {
		this.orderAnalyzeList = orderAnalyzeList;
	}
   
}
