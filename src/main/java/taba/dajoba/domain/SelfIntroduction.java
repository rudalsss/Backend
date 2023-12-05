package taba.dajoba.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="self_introduction")
@Getter
@SequenceGenerator(
        name="self_introduction_seq", sequenceName = "self_introduction_seq", initialValue = 1, allocationSize = 1
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelfIntroduction {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "self_introduction_seq")
    @Column(name = "intro_id")
    private Long id;

    private String introName;   // 자기소개서 이름

    @Lob
    private String introContent;    //자기소개서 내용

    @Column(columnDefinition = "DATE")
    private LocalDate lastUpdated;  // 마지막으로 수정된 시각

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Field field;    //희망분야-DesireField 참고

    @OneToMany(mappedBy = "selfIntroduction")
    private List<Match> matches = new ArrayList<>();

    private int signal = 1;

    //==생성 메서드==//
    public static SelfIntroduction create(String introName, String introContent, User user, Field field) {
        SelfIntroduction selfIntroduction = new SelfIntroduction();
        selfIntroduction.introName = introName;
        selfIntroduction.introContent = introContent;
        selfIntroduction.user = user;
        selfIntroduction.field = field;
        selfIntroduction.lastUpdated = LocalDate.now();  // 생성 시점의 날짜로 초기화
        return selfIntroduction;
    }

    //==수정 메서드==//
    public void update(String introName, String introContent, Field field) {
        this.introName = introName;
        this.introContent = introContent;
        this.field = field;
        this.lastUpdated = LocalDate.now();  // 수정 시점의 날짜로 업데이트
        this.signal = 0;
    }

    //==조회 메서드==//
    //repository에 작성
}
