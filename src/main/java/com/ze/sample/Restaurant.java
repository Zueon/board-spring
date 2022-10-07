package com.ze.sample;

import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component      // 스프링에게 해당 클래스가 스프링에서 관리해야할 대상임을 표시
@Data           // Lombok의 setter를 생성하는 기능, 생성자, toString() 등 자동으로 생성하도록 한다.
public class Restaurant {
    // 자동으로 setChef()를 컴파일 시 생성
    @Setter(onMethod_ = @Autowired)  // setChef()에 @Autowired 어노테이션 추가
    private Chef chef;
}
