package com.beyond.basic.b2_board.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
//      기본적으로 Entity는 상속이 불가능하여 MappedSuperclass를 붙여야 Entity와의 상속관계가 성립 가능
//      Entity가 아니므로 @Entity는 안붙임!

@MappedSuperclass

//      상속할 경우 Getter는 같이 따라가지 않으므로 Getter를 미리 붙여놓음.
@Getter
public class BaseTimeEntity {
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
