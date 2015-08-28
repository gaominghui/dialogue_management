package com.ming;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;



import org.jdom2.JDOMException;
import org.json.JSONException;
import org.json.JSONObject;



public class demo {
	/*
	 * 这个类是原来的python,flask的网络调用，一次只能有一个用户测试，所以已经废弃
	 * 没有什么用了，不用看了
	 */
	
    static moniDuihua d;

	public static void main(String[] args) throws JDOMException, IOException {
		d = new moniDuihua();
	
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/dialog", new GetHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("The server is running");
	}

    private static class GetHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            StringBuilder response = new StringBuilder();
            Map<String, String> parms = demo.queryToMap(httpExchange.getRequestURI().getQuery());
            response.append(parms.get("utterance"));
            System.out.println(response.toString());
            try {
            	demo.writeResponse(httpExchange, d.duihua(response.toString()));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }


    public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    /**
     * returns the url parameters in a map
     * @param query
     * @return map
     */
    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }
}
