package com.notify.model;

import java.util.*;

public interface NotifyDAO_interface {
          public void insert(NotifyVO notifyVO);
          public void update(NotifyVO notifyVO);
          public void delete(Integer notify_no);
          public NotifyVO findByPrimaryKey(Integer notify_no);
          public List<NotifyVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<NotifyVO> getAll(Map<String, String[]> map); 
}
