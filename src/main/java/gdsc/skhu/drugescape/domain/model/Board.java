package gdsc.skhu.drugescape.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private int heartCnt;

    @Column(nullable = false)
    private int CommentCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @JsonBackReference
    private Member member;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments; // 댓글

    public void incrementHeartCount() {
        this.heartCnt += 1;
    }

    public void decrementHeartCount() {
        this.heartCnt = Math.max(0, this.heartCnt - 1); // 음수 방지
    }
}