package com.example.springdatajpa.dao;

import java.time.LocalDateTime;


public interface BoardIf {
    public Integer getBoardId();
    public String getTitle();
    public String getContent();
    public Integer getUserId();
    public String getName();
    public LocalDateTime getRegdate();
    public int getViewCnt();


}

// b.board_id, b.title, b.content, b.user_id, u.name, b.regdate, b.view_cnt
// boardId, title, content, userId, name, regdate, viewCnt