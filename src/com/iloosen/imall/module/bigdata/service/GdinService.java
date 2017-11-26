package com.iloosen.imall.module.bigdata.service;



import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.iloosen.imall.module.bigdata.vo.ResultVo;
import com.iloosen.imall.module.bigdata.vo.WsProductVo;
import com.iloosen.imall.module.bigdata.vo.WsSysAgentAccessVo;
import com.iloosen.imall.module.bigdata.vo.WsSysCommentVo;
import com.iloosen.imall.module.order.vo.WsOrderItemVo;
import com.iloosen.imall.module.order.vo.WsOrderVo;
import com.iloosen.imall.service.bigdata.IGdinService;

import hbase.dao.HBaseDAO;
import hbase.dao.imp.HBaseDAOImp;

import java.util.List;

/**
 * 学院数据接口
 * @author JIM
 *
 * 本地测试
 * http://localhost:8080/webservice/gdin?wsdl
 *
 */
@Component
@WebService(endpointInterface="com.iloosen.imall.service.bigdata.IGdinService")
public class GdinService implements IGdinService{

	@Override
	public ResultVo batchSaveAgentAccess(List<WsSysAgentAccessVo> wsSysAgentAccessVoList) {
		System.out.println("batchSaveAgentAccess当前请求数量：" + wsSysAgentAccessVoList.size());
		System.out.println("batchSaveAgentAccess打印前2条：");
		for (int i = 0; i < wsSysAgentAccessVoList.size(); i++) {
			if(i == 2) {
				break;
			}
			System.out.println(wsSysAgentAccessVoList.get(i).getSessionId());
			System.out.println(wsSysAgentAccessVoList.get(i));
			
			HBaseDAO dao = new HBaseDAOImp();
		    dao.saveAgentAccessDataVo(wsSysAgentAccessVoList.get(i));
		}
		return returnResultVo();
	}




	@Override
	public ResultVo addWsOrder(WsOrderVo wsOrderVo, List<WsOrderItemVo> wsOrderItemVoList){
		System.out.println(wsOrderVo.toString());
		for(WsOrderItemVo wsOrderItemVo:wsOrderItemVoList) {
			System.out.println(wsOrderItemVo.toString());
		}
		if (wsOrderItemVoList.size() > 0) {
			HBaseDAO dao = new HBaseDAOImp();
			
			dao.saveOrderVo(wsOrderVo);
		    dao.saveOrderItemData(wsOrderItemVoList);
		}
		return returnResultVo();
	}


	@Override
	public ResultVo batchSaveSysComment(List<WsSysCommentVo> wsSysCommentVoList) {
		System.out.println("batchSaveSysComment当前请求数量：" + wsSysCommentVoList.size());
		System.out.println("batchSaveSysComment打印前2条：");
		for (int i = 0; i < wsSysCommentVoList.size(); i++) {
			if(i == 2) {
				break;
			}
			System.out.println(wsSysCommentVoList.get(i));
		}
		if (wsSysCommentVoList.size() > 0) {
			HBaseDAO dao = new HBaseDAOImp();
			dao.saveCommentData(wsSysCommentVoList);
		}
		return returnResultVo();
	}

	@Override
	public ResultVo batchSaveWsProduct(List<WsProductVo> wsProductVoList) {
		System.out.println("batchSaveWsProduct当前请求数量：" + wsProductVoList.size());
		System.out.println("batchSaveWsProduct打印前2条：");
		for (int i = 0; i < wsProductVoList.size(); i++) {
			if(i == 2) {
				break;
			}
			System.out.println(wsProductVoList.get(i));
		}
		if (wsProductVoList.size() > 0) {
//			HBaseDAO dao = new HBaseDAOImp();
		}
		return returnResultVo();
	}

	private ResultVo returnResultVo(){
		ResultVo resultVo =  new ResultVo();
		resultVo.setSuccess(true);
		return resultVo;
	}
}
