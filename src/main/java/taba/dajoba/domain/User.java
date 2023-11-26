package taba.dajoba.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dajoba_user")
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;

    private String password;

    private String name;

    private int experience;

    @Embedded
    private DesireRegion desireRegion;

    @Column(name = "job_content")
    private String content;

    @OneToMany(mappedBy = "user")
    private List<UserSkill> userSkills = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    private List<SelfIntroduction> selfIntroductions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "academic_background_id")
    private AcademicBackground academicBackground;
}
