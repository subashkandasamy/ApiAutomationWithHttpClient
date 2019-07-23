package LobsterApi.Utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import LobsterApi.TestBase.TestBase;

public class Jsonutils extends TestBase {

	public ArrayList<String> josonparser(String ijson, String iparent, String ichild) {

		ArrayList<String> inodeValue = new ArrayList<String>();
		JSONObject obj = new JSONObject(ijson);
		JSONArray arr = obj.getJSONArray(iparent);
		for (int i = 0; i < arr.length(); i++) {
			inodeValue.add(arr.getJSONObject(i).getString(ichild));

		}
		return inodeValue;

	}
	
	
	public String getSingnode(String ijson, String iparent, String ichild) {

		JSONObject obj = new JSONObject(ijson);
		String inodeValue = obj.getJSONObject(iparent).getString(ichild);
		return inodeValue;

	}

	public int findingsubstring(String ijson, String ifindstring) {

		return ijson.indexOf(ifindstring);

	}

}
