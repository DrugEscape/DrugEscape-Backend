package gdsc.skhu.drugescape.domain.dto;

import gdsc.skhu.drugescape.domain.model.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    @Schema(description = "게시판 ID", example = "1")
    private Long id; // 게시판 ID 추가

    @Schema(description = "제목", example = "처음 글 씁니다.")
    private String title;

    @Schema(description = "내용", example = "저는 ~~~")
    private String content;

    @Schema(description = "작성자", example = "사용자명")
    private String memberName; // 게시판 작성자 이름 추가

    @Schema(description = "좋아요 수", example = "+10")
    private Integer heartCnt;

    @Schema(description = "댓글 수", example = "+34")
    private Integer commentCnt;

    @Schema(description = "만든 날짜", example = "2024.02.02")
    private String createdAt;

    @Schema(description = "수정한 날짜", example = "2024.02.03")
    private String lastModifiedAt;

    @Schema(description = "댓글 목록", example = "화이팅, 응원합니다.")
    private List<CommentDTO> comments; // 게시글에 달린 댓글 목록 추가

    public static BoardDTO from(Board board, List<CommentDTO> commentDTOs) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .memberName(board.getMember().getName()) // 작성자 이름 설정
                .heartCnt(board.getHeartCnt())
                .commentCnt(board.getCommentCnt())
                .createdAt(board.getCreatedAt() != null ? board.getCreatedAt().format(formatter) : null)
                .lastModifiedAt(board.getLastModifiedAt() != null ? board.getLastModifiedAt().format(formatter) : null)
                .comments(commentDTOs) // 댓글 목록 설정
                .build();
    }
}