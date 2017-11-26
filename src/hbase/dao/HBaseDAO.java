package hbase.dao;

import hbase.resultVo.OrderVo;
import hbase.resultVo.TotalSaleAnalyzeVo;
import hbase.resultVo.VisitVo;
import hbase.resultVo.WsSysDataResultVo;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;

import com.iloosen.imall.module.bigdata.vo.WsProductVo;
import com.iloosen.imall.module.bigdata.vo.WsSysAgentAccessVo;
import com.iloosen.imall.module.bigdata.vo.WsSysCommentVo;
import com.iloosen.imall.module.order.vo.WsOrderItemVo;
import com.iloosen.imall.module.order.vo.WsOrderVo;

public interface HBaseDAO {
//test commit
	public void save(Put put,String tableName) ;
	
	public void insert(String tableName,String rowKey,String family,String quailifer,String value) ;
	
	public void save(List<Put>Put ,String tableName) ;
	
	public Result getOneRow(String tableName,String rowkey) ;
	
	public List<Result> getRows(String tableName, String rowKeyLike) ;
	
	public List<Result> getRows(String tableName, String rowKeyLike, String cols[]) ;
	
	public List<Result> getRows(String tableName, String startRow, String stopRow) ;
	
	public void saveAgentAccessData(List<WsSysAgentAccessVo>list) ;
	
	public void saveAgentAccessDataVo(WsSysAgentAccessVo vo) ;
	
	public void saveOrderVo(WsOrderVo orderVo);
	
	public void saveOrderData(List<WsOrderVo>list) ;
		
	public void saveOrderItemData(List<WsOrderItemVo>list) ;
	
	public void saveCommentData(List<WsSysCommentVo>list) ;
	
	public void saveWsProductVo(List<WsProductVo> list) ;
	
	//流量数据分析
	public ArrayList<WsSysDataResultVo> getWsSysDataResultVoList(String sourceType,String reportType,String dateStr) ;
	//订单数据分析
	public ArrayList<OrderVo> getOrderVoList(String reportType,String dateStr) ;
    //访问频次
	public VisitVo getVisitVo(String startDateStr,String endDateStr) ;
    //总体销量分析
	public TotalSaleAnalyzeVo getTotalSaleAnalyzeVo(String sourceType,String startDateStr,String endDateStr) ;
	
}
