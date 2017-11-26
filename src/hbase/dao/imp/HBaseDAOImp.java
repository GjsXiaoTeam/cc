package hbase.dao.imp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import com.iloosen.imall.module.bigdata.vo.WsProductVo;
import com.iloosen.imall.module.bigdata.vo.WsSysAgentAccessVo;
import com.iloosen.imall.module.bigdata.vo.WsSysCommentVo;
import com.iloosen.imall.module.order.vo.WsOrderItemVo;
import com.iloosen.imall.module.order.vo.WsOrderVo;

import hbase.dao.HBaseDAO;
import hbase.resultVo.OrderAnalyzeListVo;
import hbase.resultVo.OrderVo;
import hbase.resultVo.TotalSaleAnalyzeVo;
import hbase.resultVo.VisitAnalyzeListVo;
import hbase.resultVo.VisitVo;
import hbase.resultVo.WsSysDataResultVo;

public class HBaseDAOImp implements HBaseDAO{
/*
 * 数据表
 * tb_data tb_order tb_orderItem  tb_comment tb_product
 * 
 * */
	HConnection hTbalePool = null ;
	public HBaseDAOImp()
	{
		Configuration conf = new Configuration();
		String zk_list = "39.108.130.215,39.108.130.65,120.77.181.4" ;
		conf.set("hbase.zookeeper.quorum",zk_list) ;
		try {
			hTbalePool = HConnectionManager.createConnection(conf) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void save(Put put,String tableName) {
		HTableInterface table = null ;
		try {
			table = hTbalePool.getTable(tableName) ;
			table.put(put);
			
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void insert(String tableName, String rowKey, String family,
			String quailifer, String value) {
		HTableInterface table = null ;
		try {
			table = hTbalePool.getTable(tableName) ;
			Put put = new Put(rowKey.getBytes());
			put.add(family.getBytes(), quailifer.getBytes(), value.getBytes()) ;
			table.put(put);
		} catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void save(List<Put>Put ,String tableName) {
		HTableInterface table = null ;
		try {
			table = hTbalePool.getTable(tableName) ;
			table.put(Put);
			
		}catch (Exception e){
			e.printStackTrace() ;
		}finally{
			try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Result getOneRow(String tableName,String rowkey) {
		HTableInterface table = null ;
		Result rsResult = null ;
		try {
			table = hTbalePool.getTable(tableName) ;
			Get get = new Get(rowkey.getBytes()) ;
			rsResult = table.get(get) ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rsResult ;
	}
	
	@Override
	public List<Result> getRows(String tableName, String rowKeyLike) {
		HTableInterface table = null ;
		List<Result> list = null ;
		try {
			table = hTbalePool.getTable(tableName) ;
			PrefixFilter filter = new PrefixFilter(rowKeyLike.getBytes()) ;
			Scan scan = new Scan() ;
			scan.setFilter(filter) ;
			ResultScanner scanner = table.getScanner(scan) ;
			list = new ArrayList<Result>() ;
			for (Result rs : scanner) {
				list.add(rs) ;
			}
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			try {
				table.close() ;
			} catch (IOException e) {
				e.printStackTrace() ;
			}
		}		
		return list ;
	}
	
	@Override
	public List<Result> getRows(String tableName, String rowKeyLike, String cols[]) {
		HTableInterface table = null ;
		List<Result> list = null ;
		try {
			table = hTbalePool.getTable(tableName) ;
			PrefixFilter filter = new PrefixFilter(rowKeyLike.getBytes()) ;
			Scan scan = new Scan() ;
			for (int i = 0; i < cols.length; i++) {
				scan.addColumn("cf".getBytes(), cols[i].getBytes());
			}
			
			scan.setFilter(filter) ;
			ResultScanner scanner = table.getScanner(scan) ;
			list = new ArrayList<Result>() ;
			for (Result rs : scanner) {
				list.add(rs) ;
			}
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			try {
				table.close() ;
			} catch (IOException e) {
				e.printStackTrace() ;
			}
		}		
		return list ;
	}
	
	@Override
	public List<Result> getRows(String tableName, String startRow, String stopRow) {
		HTableInterface table = null ;
		List<Result> list = null ;
		try {
			table = hTbalePool.getTable(tableName) ;
			Scan scan = new Scan() ;
			scan.setStartRow(startRow.getBytes()) ;
			scan.setStopRow(stopRow.getBytes()) ;
			ResultScanner scanner = table.getScanner(scan) ;
			list = new ArrayList<Result>() ;
			for (Result rs : scanner) {
				list.add(rs) ;
			}
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			try {
				table.close() ;
			} catch (IOException e) {
				e.printStackTrace() ;
			}
		}	
		return list;
	}
	
	@Override
	public void saveAgentAccessData(List<WsSysAgentAccessVo> list) {
		// TODO Auto-generated method stub
		HTableInterface table = null ;
		try {
			table = hTbalePool.getTable("tb_data") ;
			for (WsSysAgentAccessVo vo : list) {
				//行健 ：用户类型+当前时间+用户标识
				//用户类型
				String userTypeString = vo.getUserAgent() ; //1=PC流量，2=WAP流量，3=微信流量
				//当前时间
				Date now = new Date() ; 
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss") ;//可以方便地修改日期格式
				String timeStr = dateFormat.format( now ) ;	
				/*
				 * 用户标识  不唯一  
				 * 有待考虑
				 * */
				String rowkey = userTypeString+"-"+timeStr+"-"+System.currentTimeMillis() ;
				System.out.println("rowkey:"+rowkey) ; 
				Put p = new Put(rowkey.getBytes()) ;
				p.add("cf".getBytes(), "sessionId".getBytes(), vo.getSessionId().getBytes()) ;
				p.add("cf".getBytes(), "userId".getBytes(), vo.getUserId().toString().getBytes()) ;
				p.add("cf".getBytes(), "trackUid".getBytes(), vo.getTrackUid().getBytes()) ;
				p.add("cf".getBytes(), "userAgent".getBytes(), vo.getUserAgent().getBytes()) ;
				p.add("cf".getBytes(), "referer".getBytes(), vo.getReferer().getBytes()) ;
				table.put(p) ;
			}
		}catch (Exception e){
			e.printStackTrace() ;
		}finally{
			try {
				table.close() ;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void saveAgentAccessDataVo(WsSysAgentAccessVo vo) {
		// TODO Auto-generated method stub
				HTableInterface table = null ;
				try {
					table = hTbalePool.getTable("tb_data") ;
					
					//行健 ：用户类型+当前时间+用户标识
					//用户类型
					String userTypeString = vo.getUserAgent() ; //1=PC流量，2=WAP流量，3=微信流量
					//当前时间
					Date now = new Date() ; 
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss") ;//可以方便地修改日期格式
					String timeStr = dateFormat.format( now ) ;	
					/*
					 * 用户标识  不唯一  
					 * 有待考虑
					 * */
					String rowkey = userTypeString+"-"+timeStr+"-"+System.currentTimeMillis() ;
					System.out.println("rowkey:"+rowkey) ; 
					Put p = new Put(rowkey.getBytes()) ;
					p.add("cf".getBytes(), "sessionId".getBytes(), vo.getSessionId().getBytes()) ;
					p.add("cf".getBytes(), "userId".getBytes(), vo.getUserId().toString().getBytes()) ;
					p.add("cf".getBytes(), "trackUid".getBytes(), vo.getTrackUid().getBytes()) ;
					p.add("cf".getBytes(), "userAgent".getBytes(), vo.getUserAgent().getBytes()) ;
					p.add("cf".getBytes(), "referer".getBytes(), vo.getReferer().getBytes()) ;
					table.put(p) ;
				}catch (Exception e){
					e.printStackTrace() ;
				}finally{
					try {
						table.close() ;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	}

	@Override
	public void saveOrderVo(WsOrderVo orderVo) {
		// TODO Auto-generated method stub
		HTableInterface table = null ;
		try {
			table = hTbalePool.getTable("tb_order") ;
			
			//行健 ：
			//当前时间
			Date now = new Date() ; 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss") ;//可以方便地修改日期格式
			String timeStr = dateFormat.format( now ) ;	
			/*
			 * 用户标识  不唯一  
			 * 有待考虑
			 * */
			String rowkey = timeStr+"-"+System.currentTimeMillis() ;
			
			Put p = new Put(rowkey.getBytes()) ;
			p.add("cf".getBytes(), "orderId".getBytes(), String.valueOf(orderVo.getOrderId()).getBytes()) ;
			p.add("cf".getBytes(), "userId".getBytes(), String.valueOf(orderVo.getUserId()).getBytes()) ;
			p.add("cf".getBytes(), "orderNum".getBytes(), orderVo.getOrderNum().getBytes()) ;
			p.add("cf".getBytes(), "payment".getBytes(), String.valueOf(orderVo.getPayment()).getBytes()) ;
			p.add("cf".getBytes(), "productTotalAmount".getBytes(), String.valueOf(orderVo.getProductTotalAmount()).getBytes());
			table.put(p);
		}catch (Exception e){
			e.printStackTrace() ;
		}finally{
			try {
				table.close() ;
			} catch (IOException e) {
				e.printStackTrace() ;
			}
		}
	}
	
	@Override
	public void saveOrderData(List<WsOrderVo> list) {
		// TODO Auto-generated method stub
		HTableInterface table = null ;
		try {
			table = hTbalePool.getTable("tb_order") ;
			for (WsOrderVo vo : list) {
				//行健 ：
				//当前时间
				Date now = new Date() ; 
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss") ;//可以方便地修改日期格式
				String timeStr = dateFormat.format( now ) ;	
				/*
				 * 用户标识  不唯一  
				 * 有待考虑
				 * */
				String rowkey = timeStr+"-"+System.currentTimeMillis() ;
				
				Put p = new Put(rowkey.getBytes()) ;
				p.add("cf".getBytes(), "orderId".getBytes(), String.valueOf(vo.getOrderId()).getBytes()) ;
				p.add("cf".getBytes(), "userId".getBytes(), String.valueOf(vo.getUserId()).getBytes()) ;
				p.add("cf".getBytes(), "orderNum".getBytes(), vo.getOrderNum().getBytes()) ;
				p.add("cf".getBytes(), "payment".getBytes(), String.valueOf(vo.getPayment()).getBytes()) ;
				p.add("cf".getBytes(), "productTotalAmount".getBytes(), String.valueOf(vo.getProductTotalAmount()).getBytes());
				table.put(p);
			}
		}catch (Exception e){
			e.printStackTrace() ;
		}finally{
			try {
				table.close() ;
			} catch (IOException e) {
				e.printStackTrace() ;
			}
		}
	}
	
	@Override
	public void saveOrderItemData(List<WsOrderItemVo> list) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				HTableInterface table = null ;
				try {
					table = hTbalePool.getTable("tb_orderItem") ;
					int i = 0;
					for (WsOrderItemVo vo : list) {
						
//						String cfString = "cf"+i;
						//行健 ：
						//当前时间
//						Date now = new Date() ; 
//						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss") ;//可以方便地修改日期格式
//						String timeStr = dateFormat.format( now ) ;	
						/*
						 * 用户标识  不唯一  
						 * 有待考虑
						 * */
						String rowkey = String.valueOf(vo.getOrderId()) ;
						Put p = new Put(rowkey.getBytes()) ;
						p.add("cf".getBytes(), ("productId"+i).getBytes(), String.valueOf(vo.getProductId()).getBytes()) ;
						p.add("cf".getBytes(), ("productUnitPrice"+i).getBytes(), String.valueOf(vo.getProductUnitPrice()).getBytes()) ;
						p.add("cf".getBytes(), ("num"+i).getBytes(), String.valueOf(vo.getNum()).getBytes()) ;
						i++;
						table.put(p);
					}
				}catch (Exception e){
					e.printStackTrace() ;
				}finally{
					try {
						table.close() ;
					} catch (IOException e) {
						e.printStackTrace() ;
					}
				}
	}

	@Override
	public void saveCommentData(List<WsSysCommentVo> list) {
		// TODO Auto-generated method stub
		HTableInterface table = null ;
		try {
			table = hTbalePool.getTable("tb_comment") ;
			for (WsSysCommentVo vo : list) {
				//行健 ：
				//当前时间
				Date now = new Date() ; 
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss") ;//可以方便地修改日期格式
				String timeStr = dateFormat.format( now ) ;	
				/*
				 * 用户标识  不唯一  
				 * 有待考虑
				 * */
				String rowkey = timeStr+"-"+System.currentTimeMillis() ;
				
				Put p = new Put(rowkey.getBytes()) ;
				p.add("cf".getBytes(), "commentId".getBytes(), vo.getCommentId().getBytes()) ;
				p.add("cf".getBytes(), "userId".getBytes(), vo.getUserId().toString().getBytes()) ;
				p.add("cf".getBytes(), "productId".getBytes(), vo.getProductId().getBytes()) ;
				p.add("cf".getBytes(), "orderId".getBytes(), vo.getOrderId().getBytes()) ;
				p.add("cf".getBytes(), "text".getBytes(), vo.getText().getBytes()) ;
				p.add("cf".getBytes(), "commentTag".getBytes(), vo.getCommentTag().getBytes()) ;
//				p.add("cf".getBytes(), "payment".getBytes(), String.valueOf(vo.getPayment()).getBytes()) ;
//				p.add("cf".getBytes(), "productTotalAmount".getBytes(), String.valueOf(vo.getProductTotalAmount()).getBytes());
				table.put(p);
			}
		}catch (Exception e){
			e.printStackTrace() ;
		}finally{
			try {
				table.close() ;
			} catch (IOException e) {
				e.printStackTrace() ;
			}
		}
	}
	
	@Override
	public void saveWsProductVo(List<WsProductVo> list) {
		// TODO Auto-generated method stub
				HTableInterface table = null ;
				try {
					table = hTbalePool.getTable("tb_product") ;
					for (WsProductVo vo : list) {
						//行健 ：
						//当前时间
						/*
						 * 用户标识  不唯一  
						 * 有待考虑
						 * */
						String rowkey = vo.getProductId() ;      //+"-"+System.currentTimeMillis() ;
						
						Put p = new Put(rowkey.getBytes()) ;
						p.add("cf".getBytes(), "medicinalTypeCode".getBytes(), vo.getMedicinalTypeCode().getBytes()) ;
						p.add("cf".getBytes(), "takeMedicinePeriod".getBytes(), vo.getTakeMedicinePeriod().getBytes()) ;
						table.put(p);
					}
				}catch (Exception e){
					e.printStackTrace() ;
				}finally{
					try {
						table.close() ;
					} catch (IOException e) {
						e.printStackTrace() ;
					}
				}
	}
	
	@Override
	public ArrayList<WsSysDataResultVo> getWsSysDataResultVoList(String sourceType,String reportType,String dateStr){
		HTableInterface table = null ;
		ArrayList<WsSysDataResultVo> list = null;
		String tableName = "tb_data";

		try {
			table = hTbalePool.getTable(tableName) ;
			list = new ArrayList<WsSysDataResultVo>();
			
			String startRow;
			String stopRow;
			
			//用户类型
			String userTypeString = "01"; //C/WAP/微信
			//通过控制循环来查找不同类型的流量信息
			int a=0,b=0;
			if (sourceType.equals("0")) {
				//全部流量
				a = 1;
				b = 3;
			}else if (sourceType.equals("1")) {
				a = 1;
				b = 1;
			}else if (sourceType.equals("2")) {
				a = 2;
				b = 2;
			}else if (sourceType.equals("3")) {
				a = 3;
				b = 3;
			}
			
			String timeStr;
			if (reportType.equals("0")) {
				//日分析  00:00:00 -01:00:00 - 24:00:00
				// dataStr 格式为 yyyy-MM-dd
				timeStr = dateStr.replaceAll("-", "");
				
				for (int i=0;i<24;i++) {
					String startHours;
					String stopHours;
					int pv = 0;
					float visitD = 1;
					List<String> trackList = new ArrayList<String>();
					List<String> sessionList = new ArrayList<String>();
					
					WsSysDataResultVo sysDataResultVo = new WsSysDataResultVo();
					sysDataResultVo.setReportTime(i+"-"+(i+1)); 
					if (i<10) {
						startHours = "0"+i;
					}else{
						startHours = Integer.toString(i);
					}
					if (i<9) {
						stopHours = "0"+(i+1);
					}else{
						stopHours = Integer.toString(i+1);
					}
					//根据a,b来判断是单个查询，还是全部流量查询
					for (int j = a; j <= b; j++) {
						userTypeString = "0"+Integer.toString(j);
						startRow = userTypeString+"-"+timeStr+startHours+"0000"; 
						stopRow = userTypeString+"-"+timeStr+stopHours+"00001";		

						Scan scan = new Scan();
						scan.setStartRow(Bytes.toBytes(startRow));
						scan.setStopRow(Bytes.toBytes(stopRow));
						byte[] famA = Bytes.toBytes("cf1"); //列族
						ResultScanner rs = table.getScanner(scan);
						for (Result result : rs) {
							pv++;
							System.out.println(startRow +":"+stopRow);
							System.out.println("scan:" + result +"\n");								
							String sessionId = Bytes.toString(result.getValue(famA, "sessionId".getBytes()));
							String userId = Bytes.toString(result.getValue(famA, "userId".getBytes()));
							String trackUid = Bytes.toString(result.getValue(famA, "trackUid".getBytes()));
							String userAgent = Bytes.toString(result.getValue(famA, "userAgent".getBytes()));
							String referer = Bytes.toString(result.getValue(famA, "referer".getBytes()));
							
							if (!trackList.contains(trackUid)) {
								trackList.add(trackUid);
							}
							if (!sessionList.contains(sessionId)) {
								sessionList.add(sessionId);
							}
						}
						
					}
					sysDataResultVo.setVisitDepth(visitD);
					sysDataResultVo.setVisitView(sessionList.size());
					sysDataResultVo.setUserView(trackList.size());
					sysDataResultVo.setPageView(pv);
					list.add(sysDataResultVo);
				}
			}else if (reportType.equals("1")) {
				//月分析 2017-05-01 : 2017-06-01
				// dataStr 格式为 yyyy-MM
				timeStr = dateStr.replaceAll("-", "");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
				Calendar calendar = Calendar.getInstance();  
			    calendar.setTime(sdf.parse(dateStr));
			    int months = calendar.get(Calendar.MONTH)+1; 
			    int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
			    
				for (int i=1;i<=days;i++) {
					String startDay;
					String stopDay;
					int pv = 0;
					float visitD = 1;
					List<String> trackList = new ArrayList<String>();
					List<String> sessionList = new ArrayList<String>();
					
					WsSysDataResultVo sysDataResultVo = new WsSysDataResultVo();
					sysDataResultVo.setReportTime(Integer.toString(months)+"-"+Integer.toString(i)); //MM-dd
					System.out.println(Integer.toString(months)+"-"+Integer.toString(i));
					if (i<10) {
						startDay = "0"+i;
					}else{
						startDay = Integer.toString(i);
					}
					if (i<9) {
						stopDay = "0"+(i+1);
					}else{
						stopDay = Integer.toString(i+1);
					}
					//根据a,b来判断是单个查询，还是全部流量查询
					for (int j = a; j <= b; j++) {
						//2017:05:04:00:00-20170505:00:00
						startRow = userTypeString+"-"+timeStr+startDay+"0000"; 
						stopRow = userTypeString+"-"+timeStr+stopDay+"0000";					
					
						Scan scan = new Scan();
						scan.setStartRow(Bytes.toBytes(startRow));
						scan.setStopRow(Bytes.toBytes(stopRow));
						byte[] famA = Bytes.toBytes("cf1"); //列族
						ResultScanner rs = table.getScanner(scan);
						for (Result result : rs) {
							pv++;
							System.out.println("scan:" + result +"\n");
							
							String sessionId = Bytes.toString(result.getValue(famA, "sessionId".getBytes()));
							String userId = Bytes.toString(result.getValue(famA, "userId".getBytes()));
							String trackUid = Bytes.toString(result.getValue(famA, "trackUid".getBytes()));
							String userAgent = Bytes.toString(result.getValue(famA, "userAgent".getBytes()));
							String referer = Bytes.toString(result.getValue(famA, "referer".getBytes()));
							
							if (!trackList.contains(trackUid)) {
								trackList.add(trackUid);
							}
							if (!sessionList.contains(sessionId)) {
								sessionList.add(sessionId);
							}
						}
					}

					sysDataResultVo.setVisitDepth(visitD);
					sysDataResultVo.setVisitView(sessionList.size());
					sysDataResultVo.setUserView(trackList.size());
					sysDataResultVo.setPageView(pv);
					list.add(sysDataResultVo);
				}
			}else if (reportType.equals("3")) {
				//年度分析
				timeStr = "";
				// dataStr 格式为 yyyy 2017:01-2017:02-2018
				timeStr = dateStr;
			     
				for (int i=1;i<=12;i++) {
					String startMonth;
					String stopMonth;
					int pv = 0;
					float visitD = 1;
					List<String> trackList = new ArrayList<String>();
					List<String> sessionList = new ArrayList<String>();
					WsSysDataResultVo sysDataResultVo = new WsSysDataResultVo();
					sysDataResultVo.setReportTime(dateStr+"-"+Integer.toString(i)); //yyyy-MM
					
					if (i<10) {
						startMonth = "0"+i;
					}else{
						startMonth = Integer.toString(i);
					}
					if (i<9) {
						stopMonth = "0"+(i+1);
					}else{
						stopMonth = Integer.toString(i+1);
					}
					//根据a,b来判断是单个查询，还是全部流量查询
					for (int j = a; j <= b; j++) {
						//2017:01:00:00:00-20170200:00:00
						startRow = userTypeString+"-"+timeStr+startMonth+"000000"; 
						stopRow = userTypeString+"-"+timeStr+stopMonth+"000000";					
					
						Scan scan = new Scan();
						scan.setStartRow(Bytes.toBytes(startRow));
						scan.setStopRow(Bytes.toBytes(stopRow));
						byte[] famA = Bytes.toBytes("cf1"); //列族
						ResultScanner rs = table.getScanner(scan);
						for (Result result : rs) {
							pv++;
							System.out.println("scan:" + result +"\n");								
							String sessionId = Bytes.toString(result.getValue(famA, "sessionId".getBytes()));
							String userId = Bytes.toString(result.getValue(famA, "userId".getBytes()));
							String trackUid = Bytes.toString(result.getValue(famA, "trackUid".getBytes()));
							String userAgent = Bytes.toString(result.getValue(famA, "userAgent".getBytes()));
							String referer = Bytes.toString(result.getValue(famA, "referer".getBytes()));
							
							if (!trackList.contains(trackUid)) {
								trackList.add(trackUid);
							}
							if (!sessionList.contains(sessionId)) {
								sessionList.add(sessionId);
							}
						}
					}

					sysDataResultVo.setVisitDepth(visitD);
					sysDataResultVo.setVisitView(sessionList.size());
					sysDataResultVo.setUserView(trackList.size());
					sysDataResultVo.setPageView(pv);
					list.add(sysDataResultVo);
				}
			}else if (reportType.equals("4")) {
				//未来3个月预测 需要根据预测算法推演未来数据的变化趋势。
				timeStr = "";
			}
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			try {
				table.close() ;
			} catch (IOException e) {
				e.printStackTrace() ;
			}
		}
		return list;
	}
	
	@Override
	public ArrayList<OrderVo> getOrderVoList(String reportType,String dateStr) {
		HTableInterface table = null ;
		ArrayList<OrderVo> list = null ;
		try {
			list = new ArrayList<OrderVo>() ;
			table = hTbalePool.getTable("tableName") ;
//			Scan scan = new Scan() ;
//			scan.setStartRow(startRow.getBytes()) ;
//			scan.setStopRow(stopRow.getBytes()) ;
//			ResultScanner scanner = table.getScanner(scan) ;
//			
//			for (Result rs : scanner) {
//				list.add(rs) ;
//			}
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			try {
				table.close() ;
			} catch (IOException e) {
				e.printStackTrace() ;
			}
		}	
		return list;
	}
	
	@Override
	public VisitVo getVisitVo(String startDateStr,String endDateStr) {
		HTableInterface table = null ;
		VisitVo visitVo = null ;
		List<Integer> visitCount = null;
		try {
			visitVo = new VisitVo() ;
			table = hTbalePool.getTable("tableName") ;
//			Scan scan = new Scan() ;
//			scan.setStartRow(startRow.getBytes()) ;
//			scan.setStopRow(stopRow.getBytes()) ;
//			ResultScanner scanner = table.getScanner(scan) ;
//			
//			for (Result rs : scanner) {
//				list.add(rs) ;
//			}

			visitCount = new ArrayList<Integer>();
			visitCount.add(10);
			visitCount.add(100);
			visitCount.add(1000);
			visitCount.add(10000);
			visitVo.setVisitCount(visitCount);
			visitVo.setTotalCount(11110);
		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			try {
				table.close() ;
			} catch (IOException e) {
				e.printStackTrace() ;
			}
		}	
		return visitVo;
	}

	@Override
	public TotalSaleAnalyzeVo getTotalSaleAnalyzeVo(String sourceType,String startDateStr,String endDateStr) {
		HTableInterface table = null ;
		TotalSaleAnalyzeVo totalSaleAnalyzeVo = null ;
		List<VisitAnalyzeListVo> visitAnalyzeListVo = null;
		List<OrderAnalyzeListVo> orderAnalyzeListVo = null;
		try {
			totalSaleAnalyzeVo = new TotalSaleAnalyzeVo();
			visitAnalyzeListVo = new ArrayList<VisitAnalyzeListVo>() ;
			orderAnalyzeListVo = new ArrayList<OrderAnalyzeListVo>() ;

			table = hTbalePool.getTable("tableName") ;
//			Scan scan = new Scan() ;
//			scan.setStartRow(startRow.getBytes()) ;
//			scan.setStopRow(stopRow.getBytes()) ;
//			ResultScanner scanner = table.getScanner(scan) ;
//			
//			for (Result rs : scanner) {
//				list.add(rs) ;
//			}


		}catch (Exception e) {
			e.printStackTrace() ;
		}finally{
			try {
				table.close() ;
			} catch (IOException e) {
				e.printStackTrace() ;
			}
		}	
		return totalSaleAnalyzeVo;
	}

	public static void main(String[] args) {
		
//		HBaseDAO dao = new HBaseDAOImp();
//		dao.insert("test", "testrow", "cf", "age", "55") ;
//		dao.insert("test", "testrow", "cf", "cardid", "12222255") ;
//		dao.insert("test", "testrow", "cf", "tel", "18675629827") ;
		
		
//		List<Put> list = new ArrayList<Put>() ;
//		Put put = new Put("rk_3".getBytes()) ;
//		put.add("cf".getBytes(), "name".getBytes(), "zhangsan3".getBytes()) ;
//		list.add(put);
//		put.add("cf".getBytes(), "addr".getBytes(),	"shanghai3".getBytes()) ;
//		list.add(put) ;
//		dao.save(list, "test");
		
		
//		Result rs = dao.getOneRow("test", "testrow") ;
//		for (KeyValue keyValue : rs.raw()) {
//			System.out.println("rowkey:"+new String(keyValue.getRow())) ;
//			System.out.println("Qualifier:"+new String(keyValue.getQualifier())) ;
//			System.out.println("Value:"+new String(keyValue.getValue())) ;
//			System.out.println("---------------") ;
//		}
		
		
//		List<Result> list = dao.getRows("test", "rk") ;
//		for (Result rs : list) {
//			for (KeyValue keyValue : rs.raw()) {
//				System.out.println("rowkey:"+new String(keyValue.getRow())) ;
//				System.out.println("Qualifier:"+new String(keyValue.getQualifier())) ;
//				System.out.println("Value:"+new String(keyValue.getValue())) ;
//				System.out.println("---------------") ;
//			}   
//		}
		
		
//		List<Result> list = dao.getRows("test", "rk", new String[]{"name","addr"}) ;
//		for (Result rs : list) {
//			for (KeyValue keyValue : rs.raw()) {
//				System.out.println("rowkey:"+new String(keyValue.getRow())) ;
//				System.out.println("Qualifier:"+new String(keyValue.getQualifier())) ;
//				System.out.println("Value:"+new String(keyValue.getValue())) ;
//				System.out.println("---------------") ;
//			}   
//		}
		
		
//		List<Result> list = dao.getRows("test", "rk_1", "rk_3") ;
//		for (Result rs : list) {
//			for (KeyValue keyValue : rs.raw()) {
//				System.out.println("rowkey:"+new String(keyValue.getRow())) ;
//				System.out.println("Qualifier:"+new String(keyValue.getQualifier())) ;
//				System.out.println("Value:"+new String(keyValue.getValue())) ;
//				System.out.println("---------------") ;
//			}   
//		}
		
		//流量数据
//		List<WsSysAgentAccessVo> list = new ArrayList<WsSysAgentAccessVo>() ;
//		int i = 20 ;
//		int type = 0 ;
//		while (i > 0) {
//			type = i%3 ;
//			WsSysAgentAccessVo vo = new WsSysAgentAccessVo() ;
//			vo.setSessionId("000000"+i) ;
//			vo.setUserId(1867500+i) ;
//			vo.setTrackUid("111100"+i);
//			vo.setUserAgent(String.valueOf(type)) ;
//			vo.setReferer("www.baidu.com"+i);
//			i--;
//			list.add(vo);
//		}
//		dao.saveAgentAccessData(list) ;
		
		//订单数据
//		List<WsOrderVo> list = new ArrayList<WsOrderVo>() ;
//		int i = 20 ;
//		while (i > 0) {
//			WsOrderVo vo = new WsOrderVo() ;
//			vo.setOrderId(20170829);
//			vo.setUserId(1867500) ;
//			vo.setOrderNum("111100"+i) ;
//			vo.setPayment(18.50) ;
//			vo.setProductTotalAmount(128.6) ;
//			vo.setIsCod("1") ;
//			vo.setOrderStatus(String.valueOf(i%3)) ;
//			i-- ;
//			list.add(vo) ;
//		}
//		dao.saveOrderData(list) ;
		

		System.err.println("save successful !");
	}


	




	
}
