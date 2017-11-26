package com.iloosen.imall.service.bigdata;

 

import javax.jws.WebService;

import com.iloosen.imall.module.bigdata.vo.ResultVo;
import com.iloosen.imall.module.bigdata.vo.WsProductVo;
import com.iloosen.imall.module.bigdata.vo.WsSysAgentAccessVo;
import com.iloosen.imall.module.bigdata.vo.WsSysCommentVo;
import com.iloosen.imall.module.order.vo.WsOrderItemVo;
import com.iloosen.imall.module.order.vo.WsOrderVo;

import java.util.List;

@WebService
public interface IGdinService {
	ResultVo batchSaveAgentAccess(List<WsSysAgentAccessVo> wsSysAgentAccessVoList);
	ResultVo addWsOrder(WsOrderVo wsOrderVo, List<WsOrderItemVo> wsOrderItemVoList);
	ResultVo batchSaveSysComment(List<WsSysCommentVo> wsSysCommentVoList);
	ResultVo batchSaveWsProduct(List<WsProductVo> wsProductVoList);
}
