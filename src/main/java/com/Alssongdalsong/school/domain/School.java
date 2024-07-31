package com.Alssongdalsong.school.domain;

import com.Alssongdalsong.common.domain.BaseTimeEntity;
import com.Alssongdalsong.school.dto.SchoolListResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class School extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Integer schoolCode; //학교 고유 코드, 추후 API용
    private String name;
    private String address;

    public SchoolListResDto fromListEntity() {
        return SchoolListResDto.builder()
                .id(this.id)
                .schoolCode(this.schoolCode)
                .name(this.name)
                .address(this.address)
                .build();
    }
}
