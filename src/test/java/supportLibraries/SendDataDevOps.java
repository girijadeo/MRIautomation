package supportLibraries;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class SendDataDevOps {
	@SuppressWarnings("unchecked")
	public void sendData(String applicationId, String processId, String toolId, String Description, String version,
			String cycle, String passed, String failed, String blocked, String startDateTime, String endDateTime,
			String metadata) {
//		try {
//			JSONObject json = new JSONObject();
//			json.put("ApplicationId", applicationId);// 6
//			json.put("AutomationProcessId", processId); // 14
//			json.put("AutomationToolId", toolId); // 19
//			json.put("Description", Description);
//			json.put("Version", version); // 4.0
//			json.put("Cycle", cycle);// First Build
//			json.put("Passed", passed);
//			json.put("Failed", failed);
//			json.put("Blocked", blocked);
//			json.put("StartDateTime", startDateTime);
//			json.put("EndDateTime", endDateTime);
//			json.put("Metadata", metadata);
////			URL url = new URL("https://devopsmetricsservice.azurewebsites.net/api/metrics/submit");
//			URL url = new URL("https://stg-devopsmetricsservice.azurewebsites.net/api/metrics/submit");
//			HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
//			httpcon.setDoOutput(true);
//			httpcon.setDoInput(true);
//			httpcon.setRequestMethod("POST");
//			httpcon.setRequestProperty("Accept", "application/json");
//			httpcon.setRequestProperty("Content-type", "application/json");
//			httpcon.connect();
//
//			// OutputStream os = httpcon.getOutputStream();
//			OutputStreamWriter output = new OutputStreamWriter(httpcon.getOutputStream());
//			output.write(json.toString());
//			output.flush();
//			System.out.println(httpcon.getRequestMethod());
//			System.out.println(httpcon.getResponseMessage());
//
//		} catch (Exception e) {
//			System.out.println(e.getLocalizedMessage());
//		}

	}
}
