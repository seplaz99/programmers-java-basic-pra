package com.example.basic_board_token.service;

import com.example.basic_board_token.domain.entity.Board;
import com.example.basic_board_token.domain.repository.BoardRepository;
import com.example.basic_board_token.dto.*;
import com.example.basic_board_token.exception.BoardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileService fileService;

    public List<Board> getBoardList(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());

        return boardRepository.findAll(pageable).getContent();
    }

    public int getTotalBoards() {
        return (int) boardRepository.count();
    }

    @Transactional
    public void saveBoard( String userId, String title, String content, MultipartFile file ) {

        String filePath = fileService.storeFile(file);

        boardRepository.save(
                Board.builder()
                        .userId(userId)
                        .title(title)
                        .content(content)
                        .filePath(filePath)
                        .created(LocalDateTime.now())
                        .build()
        );
    }

    public Board getBoardDetail(long id) {
        return boardRepository.findById(id)
                .orElseThrow( () -> new BoardNotFoundException("[BOARD] 게시글을 찾을 수 없습니다. id : " + id));
    }

    @Transactional
    public void updateBoard(long id, String userId, BoardUpdateRequestDto dto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(
                        () -> new BoardNotFoundException("[BOARD] 수정할 게시글을 찾을 수 없습니다. id : " + id)
                );

        requireOwner(board, userId, "수정");

        String filePath = board.getFilePath();
        if ( dto.isFileFlag() ) {
            fileService.deleteFile(filePath);
            filePath = fileService.storeFile(dto.getFile());
        }

        board.update( dto.getTitle(), dto.getContent(), filePath );
    }

    @Transactional
    public void deleteBoard(long id, String userId, BoardDeleteRequestDto dto) {

        Board board = boardRepository.findById(id)
                .orElseThrow(
                        () -> new BoardNotFoundException("[BOARD] 삭제할 게시글을 찾을 수 없습니다. id : " + id)
                );

        requireOwner(board, userId, "삭제");

        boardRepository.deleteById(id);
        fileService.deleteFile(dto.getFilePath());
    }

    private void requireOwner(Board board, String userId, String action) {
        if ( !board.getUserId().equals(userId) ) {
            throw new AccessDeniedException("본인이 작성한 게시글만 " + action + "할 수 있습니다.");
        }
    }

    public Page<BoardListItemResponseDto> searchBoards(BoardSearchRequestDto dto, Pageable pageable) {
        return boardRepository.searchBoards(dto, pageable);
    }

    public Board getBoardWithComments(Long id) {
        return boardRepository.findWithComments(id)
                .orElseThrow(
                        () -> new BoardNotFoundException("게시글을 찾을 수 없습니다. id = " + id)
                );
    }

    public List<BoardAuthorStatsResponseDto> getAuthorStats(long minCount) {
        return boardRepository.countBoardsByAuthor(minCount);
    }
}
