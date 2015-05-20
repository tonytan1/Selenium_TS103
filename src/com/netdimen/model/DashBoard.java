package com.netdimen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Config;
import com.netdimen.dao.AdobeObject;
import com.netdimen.dao.ChartType;
import com.netdimen.utils.DataUtils;
import com.netdimen.utils.UIFunctionUtils;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class DashBoard extends com.netdimen.abstractclasses.TestObject {
	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public DashBoard() {
		super();
	}

	
	public void runCheckDashboard(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.MyCharts"));
		
		HashMap<String, ArrayList<String>> chart_values = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		Iterator<String> charts = chart_values.keySet().iterator();
		
		ArrayList<AdobeObject> objs = new ArrayList();
		
		while(charts.hasNext()){
			String chart = charts.next();
			
			ArrayList<String> values = chart_values.get(chart);
			for(String valueStr: values){
				int counter = 0;
				String[] valuesArray = valueStr.split(";");				
				String[] keys = new String[valuesArray.length];
				String[] expectedResults = new String[valuesArray.length];
				
				for(String value: valuesArray){
					int index = value.indexOf("=");
					keys[counter] = value.substring(0, index);
					expectedResults[counter] = value.substring(index+"=".length());
					counter++;
				}
				
				int index = chart.indexOf("(");
				String chartName = chart.substring(0, index).trim();
				By srcBy = By.xpath("//table/tbody[descendant::tr/td/div/div/span/h3[text()='" + chartName 
						+"']]/tr/td/div/object/param[@name='FlashVars']");
				
				String temp = DataUtils.decodeURL(WebDriverUtils.getAttribute(driver,
						srcBy, "value"));
				index = temp.indexOf("=");
				String dataURL = temp.substring(index + 1);
				String url = "http://" + Config.getInstance().getProperty("IP") + ":"
						+ Config.getInstance().getProperty("port") + dataURL;
				
				ChartType chartType = null ;
				if(chart.contains("Pie")){
					chartType = ChartType.Pie;
				}else if(chart.contains("Histogram")){
					chartType = ChartType.Histogram;
				}else if(chart.contains("Table")){
					chartType = ChartType.Table;
				}
				
				AdobeObject obj = new AdobeObject(url, chartType, keys, expectedResults);
				objs.add(obj);
			}
		}
		
		for(AdobeObject obj: objs){
			WebDriverUtils.checkAdobeFlashResults(driver, obj.getChartType(), obj.getUrl(), obj.getKeys(), obj.getExpectedResults());
		}
	}

}