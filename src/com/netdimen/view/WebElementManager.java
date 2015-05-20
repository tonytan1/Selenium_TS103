package com.netdimen.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.netdimen.config.Config;
import com.netdimen.utils.POIUtils;

/**
 * 
 * @author lester.li This is a class to load WebElements from excel and provide
 *         service for menu navigation and general webelement query with sheet
 *         and id
 */
public class WebElementManager {
	private FileInputStream excel;
	private HSSFWorkbook wb;
	private Hashtable<String, WebElementWrapper> tb; 
	private static WebElementManager instance;

	private WebElementManager() {
		try {
			excel = new FileInputStream(Config.getInstance().getProperty(
					"WebElementSheets"));
			wb = new HSSFWorkbook(excel);
			tb = new Hashtable<String, WebElementWrapper>();
			buildHastTableFromExcel(wb);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static WebElementManager getInstance() {
		if (instance == null) {
			synchronized (WebElementManager.class) { // Add a synch block
				if (instance == null) { // verify some other synch block didn't
										// create a WebElementManager yet...
					instance = new WebElementManager();
				}
			}
		}
		return instance;
	}
	
	private void buildHastTableFromExcel(HSSFWorkbook wb){
		for (int i = 0; i <wb.getNumberOfSheets(); i++) {
			HSSFSheet sheet = wb.getSheetAt(i);
			String strSheetName= sheet.getSheetName();
			ArrayList<String> tempIDs = new ArrayList<String>();
			
			tempIDs = POIUtils.getColumnFromExcel(sheet, 0, 1);
			ArrayList<String> tempRowData;
			for (int j = 0; j < tempIDs.size(); j++) {
				tempRowData = POIUtils.getRowFromExcel(sheet, j+1);
				WebElementWrapper we = null;
				int size= tempRowData.size();
				String expectMsg;
				if (size <5) {
					expectMsg="";
				}else{
					expectMsg=tempRowData.get(4).trim();
				}
				try {
					we =  new WebElementWrapper(tempRowData.get(0).trim(),
							tempRowData.get(1).trim(), 
							tempRowData.get(2).trim(),
							tempRowData.get(3).trim(),
							expectMsg, 
							sheet.getSheetName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tb.put(strSheetName +tempIDs.get(j) , we);	
			}
		}
	}

	/**
	 * 
	 * @param strSheetName
	 * @param id
	 * @return
	 */
	public WebElementWrapper getWebElementWrapper(String strSheetName, String id) {
		WebElementWrapper we;
		we= this.tb.get(strSheetName+id);
		if (we == null){
			System.out.println("WebElement:getWebElementBy: welement id= " + strSheetName+id
					+ "not existed");
			return null;
		}else{
			return we.Clone();
		}	
	}

	/**
	 * 
	 * @param strSheetName
	 *            = the sheet name in excel
	 * @param id
	 *            =WebElement id
	 * @return an ArrayList to be use for Navigation
	 */
	public ArrayList<WebElementWrapper> getNavigationPathList(
			String strSheetName, String id) {
		ArrayList<WebElementWrapper> pathList = new ArrayList<WebElementWrapper>();
		findParentNode(strSheetName, id, pathList);
		return pathList;
	}

	/**
	 * 
	 * @param strSheetName
	 *            = the sheet name in excel
	 * @param id
	 *            =WebElement id
	 * @param list
	 *            : pass by reference to add the webelement.by to construct
	 *            Navigation Path with recursive call
	 */
	private void findParentNode(String strSheetName, String id,
			ArrayList<WebElementWrapper> list) {
		WebElementWrapper temp = null;
		temp=tb.get(strSheetName + id);
		if (!temp.getParentId().equalsIgnoreCase(WebElementWrapper.ROOT)) {
			findParentNode(strSheetName, temp.getParentId(), list);
		} 
		
		list.add(temp.Clone());
	/*	if(Config.DEBUG_MODE){
			System.out.println("findParentNode: " + temp.getId() + "="
					+ temp.getElementValue());	
		}*/
	}

}
