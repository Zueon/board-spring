package com.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
    private int pageNum;
    private int amount;

    private String type;
    private String keyword;

    public Criteria() {
        this(1,10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
    // 검색 조건 처리를 위해서 ...
    public String[] getType(){
        return type == null ? new String[] { } : type.split("");
    }
}
