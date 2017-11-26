package com.iloosen.imall.module.bigdata.vo;



import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by cjp on 2017/3/3.
 */
public class WsSysAgentAccessVo implements Serializable {

    private String sessionId;
    private Integer userId;
    private String trackUid;
    private String userAgent;
    private String referer;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTrackUid() {
        return trackUid;
    }

    public void setTrackUid(String trackUid) {
        this.trackUid = trackUid;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getReferer() {
        return referer;
    }

    @Override
	public String toString() {
		return "WsSysAgentAccessVo [sessionId=" + sessionId + ", userId=" + userId + ", trackUid=" + trackUid
				+ ", userAgent=" + userAgent + ", referer=" + referer + "]";
	}

	public void setReferer(String referer) {
        this.referer = referer;
    }


//    @Override
//    public String toString() {
//        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
//                .append("SESSION_ID",getSessionId())
//                .append("USER_ID",getUserId())
//                .append("TRACK_UID",getTrackUid())
//                .append("USER_AGENT",getUserAgent())
//                .append("REFERER",getReferer())
//                .toString();
//    }
}
