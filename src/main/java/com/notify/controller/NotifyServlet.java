package com.notify.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.notify.model.*;

public class NotifyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page_n.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		String str = req.getParameter("notify_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入通知編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/notify/select_page_n.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				Integer notify_no = null;
				try {
					notify_no = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("通知編號格式不正確，請輸入數字");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/notify/select_page_n.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				NotifyService notifySvc = new NotifyService();
				NotifyVO notifyVO = notifySvc.getOneNotify(notify_no);
				if (notifyVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/notify/select_page_n.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("notifyVO", notifyVO); // 資料庫取出的notifyVO物件,存入req
				String url = "/back-end/notify/listOneNotify.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneNotify.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllNotify.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer notify_no = Integer.valueOf(req.getParameter("notify_no"));
				
				/***************************2.開始查詢資料****************************************/
				NotifyService notifySvc = new NotifyService();
				NotifyVO notifyVO = notifySvc.getOneNotify(notify_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("notifyVO", notifyVO);         // 資料庫取出的notifyO物件,存入req
				String url = "/back-end/notify/update_notify_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_notify_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_notify_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		Integer notify_no = Integer.valueOf(req.getParameter("notify_no").trim());
			
		String notify_content = req.getParameter("notify_content").trim();
				if (notify_content == null || notify_content.trim().length() == 0) {
					errorMsgs.add("通知內容請勿空白");
				}	
				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

		Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
//		String managerstatusReg = "^[0-1]$";
				NotifyVO notifyVO = new NotifyVO();
				notifyVO.setNotify_no(notify_no);
				notifyVO.setNotify_content(notify_content);
				notifyVO.setMem_id(mem_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("notifyVO", notifyVO); // 含有輸入格式錯誤的notifyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/notify/update_notify_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				NotifyService notifySvc = new NotifyService();
				notifyVO = notifySvc.updateNotify(notify_no, mem_id, notify_content);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("notifyVO", notifyVO); // 資料庫update成功後,正確的的notifyVO物件,存入req
				String url = "/back-end/notify/listOneNotify.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneNotify.jsp
				successView.forward(req, res);
		}

		
		
		
        if ("insert".equals(action)) { // 來自addNotify.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//		String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
		
			String str = req.getParameter("mem_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			
			Integer mem_id = 0; //?????????????????????????????
			try {
				mem_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("會員編號格式不正確，請輸入數字");
			}
		
		String notify_content = req.getParameter("notify_content").trim();
				if (notify_content == null || notify_content.trim().length() == 0) {
					errorMsgs.add("通知內容請勿空白");
				}
				
//				java.sql.Date hiredate = null;
//				try {
//		hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
//		Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
		
		

				NotifyVO notifyVO = new NotifyVO();
				notifyVO.setNotify_content(notify_content);
				notifyVO.setMem_id(mem_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("notifyVO", notifyVO); // 含有輸入格式錯誤的notifyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/notify/addNotify.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				NotifyService notifySvc = new NotifyService();
				notifyVO = notifySvc.addNotify(mem_id, notify_content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/notify/listAllNotify.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllNotify.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllNotify.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
		Integer notify_no = Integer.valueOf(req.getParameter("notify_no"));
				
				/***************************2.開始刪除資料***************************************/
				NotifyService notifySvc = new NotifyService();
				notifySvc.deleteNotify(notify_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/notify/listAllNotify.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
