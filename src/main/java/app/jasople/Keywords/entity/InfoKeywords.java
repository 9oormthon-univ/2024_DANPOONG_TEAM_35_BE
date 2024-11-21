package app.jasople.Keywords.entity;

import app.jasople.IndustryInfo.entity.IndustryInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class InfoKeywords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "IndustryInfo_id")
    private IndustryInfo industryInfo;

    @OneToOne
    @JoinColumn(name = "Keywords_id")
    private Keywords keyword;
}
