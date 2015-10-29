// package com.toparchy.molecule.permission.model;
//
// import static
// org.picketlink.idm.model.annotation.IdentityStereotype.Stereotype.UNDEFINED;
//
// import javax.persistence.CascadeType;
// import javax.persistence.FetchType;
// import javax.persistence.ManyToOne;
//
// import org.picketlink.idm.model.AbstractIdentityType;
// import org.picketlink.idm.model.annotation.AttributeProperty;
// import org.picketlink.idm.model.annotation.IdentityStereotype;
// import org.picketlink.idm.query.QueryParameter;
//
// @IdentityStereotype(UNDEFINED)
// public class Device extends AbstractIdentityType {
//
// private static final long serialVersionUID = 8265338322893127199L;
// public static final QueryParameter DEVICE_TYPE =
// QUERY_ATTRIBUTE.byName("deviceType");
// public static final QueryParameter CHANNELID =
// QUERY_ATTRIBUTE.byName("channelId");
// public static final QueryParameter USERID = QUERY_ATTRIBUTE.byName("userId");
// public static final QueryParameter STATE = QUERY_ATTRIBUTE.byName("state");
// @AttributeProperty
// private String deviceType;
// @AttributeProperty
// private String channelId;
// @AttributeProperty
// private String userId;
// @AttributeProperty
// private String state;
// @AttributeProperty
// @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
// private Member member;
//
// public Device() {
// }
//
// public String getDeviceType() {
// return deviceType;
// }
//
// public void setDeviceType(String deviceType) {
// this.deviceType = deviceType;
// }
//
// public String getChannelId() {
// return channelId;
// }
//
// public void setChannelId(String channelId) {
// this.channelId = channelId;
// }
//
// public String getUserId() {
// return userId;
// }
//
// public void setUserId(String userId) {
// this.userId = userId;
// }
//
// public String getState() {
// return state;
// }
//
// public void setState(String state) {
// this.state = state;
// }
//
// public Member getMember() {
// return member;
// }
//
// public void setMember(Member member) {
// this.member = member;
// }
//
// }
