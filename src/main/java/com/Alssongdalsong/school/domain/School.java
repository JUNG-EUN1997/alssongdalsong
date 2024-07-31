package com.Alssongdalsong.school.domain;

import com.Alssongdalsong.common.domain.BaseTimeEntity;
import com.Alssongdalsong.school.dto.SchoolListResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class School extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer schoolCode; //학교 고유 코드
    private String name;
    private String address;

    public SchoolListResDto fromListEntity() {
        return SchoolListResDto.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .build();
    }
}
