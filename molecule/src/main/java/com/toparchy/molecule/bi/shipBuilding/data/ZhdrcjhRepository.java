package com.toparchy.molecule.bi.shipBuilding.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import com.toparchy.molecule.bi.shipBuilding.model.Zhdrcjh;
import com.toparchy.molecule.bi.shipBuilding.model.ZhdrcjhDataHandle;
import com.toparchy.molecule.bi.shipBuilding.model.ZhdrcjhInternalData;

@ApplicationScoped
public class ZhdrcjhRepository {
	@PersistenceUnit(unitName = "molecule")
	private EntityManagerFactory emf;
	private String sql;
	private Query query;
	private List<Zhdrcjh> objecArraytList;
	private List<ZhdrcjhInternalData> zhdrcjhInternalDatas;
	private ZhdrcjhDataHandle zhdrcjhDataHandle;
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	private String[] color = { "aliceblue", "antiquewhite", "aqua",
			"aquamarine", "azure", "beige", "bisque", "brown", "cadetblue",
			"crimson", "yellowgreen", "turquoise" };

	public ZhdrcjhDataHandle findByGcbh(String gcbh, int start, int max) {
		EntityManager em = emf.createEntityManager();
		sql = "select ppk_varcode, cxmc, gcbh, gcmc, xmbh, xmmc, xmlb, jhkg, jhwg from JH_D_Zhdrcjh_DJ where gcbh =?1 order by xmbh";
		query = em.createNativeQuery(sql, Zhdrcjh.class);
		query.setParameter(1, gcbh);
		query.setFirstResult(start);
		query.setMaxResults(max);
		objecArraytList = query.getResultList();
		int i = 4;
		zhdrcjhDataHandle = new ZhdrcjhDataHandle();
		zhdrcjhInternalDatas = new ArrayList<ZhdrcjhInternalData>();
		Calendar ksDate = Calendar.getInstance();
		Calendar jsDate = Calendar.getInstance();
		for (Zhdrcjh zhdrcjh : objecArraytList) {
			ZhdrcjhInternalData zhdrcjhInternalData = new ZhdrcjhInternalData();
			if (zhdrcjh.getXmlb().equals("技术准备")
					&& zhdrcjh.getXmmc().equals("技术准备")) {
				zhdrcjhInternalData.setId(1);
				zhdrcjhInternalData.setStart_date(formatter.format(zhdrcjh
						.getJhkg()));
				ksDate.setTime(zhdrcjh.getJhkg());
				jsDate.setTime(zhdrcjh.getJhwg());
				zhdrcjhInternalData.setDuration(String.valueOf((jsDate
						.getTimeInMillis() - ksDate.getTimeInMillis())
						/ (1000 * 60 * 60 * 24)));
				zhdrcjhInternalData.setOpen("true");
				zhdrcjhInternalData.setText(zhdrcjh.getXmmc());
				zhdrcjhInternalData.setColor(color[0]);
			}
			if (zhdrcjh.getXmlb().equals("技术准备")
					&& !zhdrcjh.getXmmc().equals("技术准备")) {
				zhdrcjhInternalData.setId(i);
				zhdrcjhInternalData.setStart_date(formatter.format(zhdrcjh
						.getJhkg()));
				ksDate.setTime(zhdrcjh.getJhkg());
				jsDate.setTime(zhdrcjh.getJhwg());
				zhdrcjhInternalData.setDuration(String.valueOf((jsDate
						.getTimeInMillis() - ksDate.getTimeInMillis())
						/ (1000 * 60 * 60 * 24)));
				zhdrcjhInternalData.setOpen("true");
				zhdrcjhInternalData.setText(zhdrcjh.getXmmc());
				zhdrcjhInternalData.setColor(color[0]);
				zhdrcjhInternalData.setParent(1);
			}
			if (zhdrcjh.getXmlb().equals("物资准备")
					&& zhdrcjh.getXmmc().equals("物资准备")) {
				zhdrcjhInternalData.setId(2);
				zhdrcjhInternalData.setStart_date(formatter.format(zhdrcjh
						.getJhkg()));
				ksDate.setTime(zhdrcjh.getJhkg());
				jsDate.setTime(zhdrcjh.getJhwg());
				zhdrcjhInternalData.setDuration(String.valueOf((jsDate
						.getTimeInMillis() - ksDate.getTimeInMillis())
						/ (1000 * 60 * 60 * 24)));
				zhdrcjhInternalData.setOpen("true");
				zhdrcjhInternalData.setText(zhdrcjh.getXmmc());
				zhdrcjhInternalData.setColor(color[1]);
			}
			if (zhdrcjh.getXmlb().equals("物资准备")
					&& !zhdrcjh.getXmmc().equals("物资准备")) {
				zhdrcjhInternalData.setId(i);
				zhdrcjhInternalData.setStart_date(formatter.format(zhdrcjh
						.getJhkg()));
				ksDate.setTime(zhdrcjh.getJhkg());
				jsDate.setTime(zhdrcjh.getJhwg());
				zhdrcjhInternalData.setDuration(String.valueOf((jsDate
						.getTimeInMillis() - ksDate.getTimeInMillis())
						/ (1000 * 60 * 60 * 24)));
				zhdrcjhInternalData.setOpen("true");
				zhdrcjhInternalData.setText(zhdrcjh.getXmmc());
				zhdrcjhInternalData.setColor(color[1]);
				zhdrcjhInternalData.setParent(2);
			}
			if (zhdrcjh.getXmlb().equals("生产准备")
					&& zhdrcjh.getXmmc().equals("生产准备")) {
				zhdrcjhInternalData.setId(3);
				zhdrcjhInternalData.setStart_date(formatter.format(zhdrcjh
						.getJhkg()));
				ksDate.setTime(zhdrcjh.getJhkg());
				jsDate.setTime(zhdrcjh.getJhwg());
				zhdrcjhInternalData.setDuration(String.valueOf((jsDate
						.getTimeInMillis() - ksDate.getTimeInMillis())
						/ (1000 * 60 * 60 * 24)));
				zhdrcjhInternalData.setOpen("true");
				zhdrcjhInternalData.setText(zhdrcjh.getXmmc());
				zhdrcjhInternalData.setColor(color[2]);
			}
			if (zhdrcjh.getXmlb().equals("生产准备")
					&& !zhdrcjh.getXmmc().equals("生产准备")) {
				zhdrcjhInternalData.setId(i);
				zhdrcjhInternalData.setStart_date(formatter.format(zhdrcjh
						.getJhkg()));
				ksDate.setTime(zhdrcjh.getJhkg());
				jsDate.setTime(zhdrcjh.getJhwg());
				zhdrcjhInternalData.setDuration(String.valueOf((jsDate
						.getTimeInMillis() - ksDate.getTimeInMillis())
						/ (1000 * 60 * 60 * 24)));
				zhdrcjhInternalData.setOpen("true");
				zhdrcjhInternalData.setText(zhdrcjh.getXmmc());
				zhdrcjhInternalData.setColor(color[2]);
				zhdrcjhInternalData.setParent(3);
			}
			zhdrcjhInternalDatas.add(zhdrcjhInternalData);
			i++;
		}
		zhdrcjhDataHandle.setData(zhdrcjhInternalDatas);
		em.close();
		return zhdrcjhDataHandle;
	}
}
