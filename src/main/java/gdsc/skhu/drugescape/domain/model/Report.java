package gdsc.skhu.drugescape.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int point;

    @Column(nullable = false)
    private int maximumDays; // maximum_day 문제 발생, 왜 3?

    @Column(nullable = false)
    private int dailyGoals;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY)
    private List<Donation> donations;

    public void applyUpdates(int point, int maximumDays, int dailyGoals) {
        this.point = point;
        this.maximumDays = Math.max(this.maximumDays, maximumDays);
        this.dailyGoals = dailyGoals;
    }

    public void pointDecrease(int pointsToDecrease) {
        if (this.point < pointsToDecrease) {
            throw new IllegalStateException("기부할 수 있는 포인트가 부족합니다.");
        }
        this.point -= pointsToDecrease;
    }
}