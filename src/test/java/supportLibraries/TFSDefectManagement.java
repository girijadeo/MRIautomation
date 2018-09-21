package supportLibraries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONObject;;

public class TFSDefectManagement {
	@SuppressWarnings("unchecked")
	public void createBugInTFS(String API_URL, String title,String repro_steps,String project_name_in_TFS,String SystemInfo,String priority,
			String severity,String worktype, String history) {
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			JSONObject json = new JSONObject();
			json.put("action", "create");
			json.put("title", title); 
			json.put("repro_steps", repro_steps);
			json.put("project_name_in_TFS", project_name_in_TFS);
			json.put("SystemInfo", SystemInfo); 
			json.put("priority", priority);
			json.put("severity", severity);
			json.put("worktype", worktype);
			json.put("history", history);
			URL url = new URL(API_URL);
			HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
			httpcon.setDoOutput(true);
			httpcon.setDoInput(true);
			httpcon.setRequestMethod("POST");
			httpcon.setRequestProperty("Accept", "application/json");
			httpcon.connect();
//			
//			httpcon.setConnectTimeout(20);
//			Utility_Functions.timeWait(20);
			
			OutputStreamWriter output = new OutputStreamWriter(httpcon.getOutputStream());
			output.write(json.toString());
			output.flush();
			System.out.println(httpcon.getRequestMethod());
			System.out.println(httpcon.getResponseMessage());
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(httpcon.getInputStream()));
			String inputLine;
			StringBuffer response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response1.toString());
			String response_string = response1.toString();

			JSONObject jsonObj = new JSONObject(response_string);
			System.out.println("Defect ID = "+jsonObj.get("id"));
			
//		
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}
	
	@SuppressWarnings("unchecked")
	public boolean searchBugInTFS(String API_URL, String title,String project_name_in_TFS) {
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			JSONObject json = new JSONObject();
			json.put("action", "search");
			json.put("defect_title_to_search", title); 
			json.put("project_name_in_TFS", project_name_in_TFS);
			URL url = new URL(API_URL);
			HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
			httpcon.setDoOutput(true);
			httpcon.setDoInput(true);
			httpcon.setRequestMethod("POST");
			httpcon.setRequestProperty("Accept", "application/json");
			httpcon.connect();
			
			httpcon.setConnectTimeout(20);
			
			Utility_Functions.timeWait(20);
			
			OutputStreamWriter output = new OutputStreamWriter(httpcon.getOutputStream());
			output.write(json.toString());
			output.flush();
			System.out.println(httpcon.getRequestMethod());
			System.out.println(httpcon.getResponseMessage());
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(httpcon.getInputStream()));
			String inputLine;
			StringBuffer response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response1.toString());
			String response_string = response1.toString();

			JSONObject jsonObj = new JSONObject(response_string);
			System.out.println("Existing defect = "+jsonObj.get("workItems"));
			
//			System.out.println(jsonObj.get("workItems").toString());
			
			
			if(jsonObj.get("workItems").toString().contains("id")) {
				return true;				
			}
			else {
				return false;
			}
			
//		
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return false;

	}
//}
	}
//}
