package com.notify.model;

import java.util.List;

public class NotifyService {

	private NotifyDAO_interface dao;

	public NotifyService() {
		dao = new NotifyJDBCDAO();
	}

	public NotifyVO addNotify(Integer mem_id, String notify_content) {

		NotifyVO notifyVO = new NotifyVO(); // set notifyVO

		notifyVO.setMem_id(mem_id);
		notifyVO.setNotify_content(notify_content);
		dao.insert(notifyVO);

		return notifyVO;
	}

	public NotifyVO updateNotify(Integer notify_no, Integer mem_id, String notify_content) {

		NotifyVO notifyVO = new NotifyVO();

		notifyVO.setNotify_no(notify_no);
		notifyVO.setMem_id(mem_id);
		notifyVO.setNotify_content(notify_content);
		dao.update(notifyVO);

		return notifyVO;
	}

	public void deleteNotify(Integer notify_no) {
		dao.delete(notify_no);
	}

	public NotifyVO getOneNotify(Integer notify_no) {
		return dao.findByPrimaryKey(notify_no);
	}

	public List<NotifyVO> getAll() {
		return dao.getAll();
	}
}
