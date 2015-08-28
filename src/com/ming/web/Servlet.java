package com.ming.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

import com.ming.Client_moniDuihua;
import com.ming.crf.Time;
import com.ming.slots.Slots_4;
import com.ming.time.TimeFetcher;

public class Servlet  extends HttpServlet {
	
	public Client_moniDuihua  d = new Client_moniDuihua();
	
	/*
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		
		res.getWriter().println("gao mign hui");
		String utterance = req.getParameter("utterance");
		
		res.getWriter().println(utterance);
		
	}
*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
		response.setContentType("text/html;charset=utf-8");   
		response.setCharacterEncoding("utf-8"); 
		//response.getWriter().println("高明辉");
		String utterance = request.getParameter("utterance");
		
		//response.getWriter().println(utterance);
		 
		if(d == null)
		{
			d = new Client_moniDuihua();
		}
		
		HttpSession session = request.getSession();
		Slots_4 slots = (Slots_4) session.getAttribute("slots");
		
		if(slots == null)
		{
			slots = new Slots_4();
		}
		
		String ret = null;
		try {
			ret =  d.duihua(utterance,slots);
		} catch (JSONException e) {
			ret = "对不起，因为网络原因未收到你的回话";
		}
		
		session.setAttribute("slots", slots);
		
		PrintWriter out = response.getWriter();
		out.println(ret);
		
		
		
		
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
