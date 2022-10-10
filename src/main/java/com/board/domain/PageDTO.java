package com.board.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
    private int firstPage;
    private int lastPage;
    private int totalBoard;

    private boolean prev, next;

    private Criteria criteria;

    public PageDTO(Criteria cri, int totalBoard) {
        this.totalBoard = totalBoard;
        this.criteria = cri;

        this.lastPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
        this.firstPage = lastPage - 9;

        int end = (int) (Math.ceil((totalBoard * 1.0) / cri.getAmount()));

        if(end < lastPage) this.lastPage = end;

        this.prev = this.firstPage > 1;
        this.next = this.lastPage < end;
    }
}
