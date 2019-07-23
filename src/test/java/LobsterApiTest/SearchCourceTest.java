package LobsterApiTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.DataProvider;

import LobsterApi.TestBase.TestBase;
import LobsterApi.Utils.Excelutils;
import LobsterApi.Utils.Jsonutils;
import LobsterApi.client.ApiClients;

public class SearchCourceTest extends TestBase {
	
	
	TestBase testBase;
	String serviceUrl;
	String query;
	ApiClients restClient;
	Excelutils excelutil;
	Jsonutils jsonutils;
	String sheetName = "SearchCourceApi";
	CloseableHttpResponse closebaleHttpResponse;
	
	@BeforeMethod
	public void setUp(){
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
	    excelutil = new Excelutils();
	    jsonutils = new Jsonutils();
	}
	
    @DataProvider
	public Object[][] searchCourceDatagetter(){
		Object data[][] = Excelutils.getTestData(sheetName,"Data001");
		return data;
	}
      
	
	@Test(dataProvider="searchCourceDatagetter")
	public void postAPITest(String isearchQuery, String DataVarity) throws IOException{
		restClient = new ApiClients();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("Host", "aapi.lobsterink.com");
		headerMap.put("tenant", "lobsterink");
		query = prop.getProperty("querypart1")+ isearchQuery + prop.getProperty("querypart2");
		closebaleHttpResponse = restClient.post(serviceUrl, query, headerMap);
		
		//1. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		
		//2. JsonString:
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		
		Assert.assertTrue(jsonutils.findingsubstring(responseString,isearchQuery)> 0);
		
		
	}
	
	
}
	
	
	
	
