
$(document).ready(() => {
    requireLogin(() => {
        loadBoardDetail();
    });
});

let editArticle = () => {
    let resourceId = $('#hiddenId').val();
    window.location.href = "/update/" + resourceId;
}

let deleteArticle = () => {

    let resourceId = $('#hiddenId').val();
    let filePath = $('#hiddenFilePath').val();

    $.ajax({
        type: 'DELETE',
        url: '/api/boards/' + resourceId, // 실제 서버 API URL 및 삭제할 리소스 ID
        data: JSON.stringify({ filePath: filePath }), // filePath를 JSON으로 서버에 전송
        contentType: 'application/json', // JSON 형식으로 전송
        success: (response) => {
            alert('리소스가 성공적으로 삭제되었습니다.');
            window.location.href = '/'; // 성공 후 목록 페이지로 이동
        },
        error: (error) => {
            alert('리소스 삭제 중 오류가 발생했습니다.');
            console.error('Error:', error);
        }
    });
}

let loadBoardDetail = () => {

    let hId = $('#hiddenId').val();
    let currentUserId = getCurrentUserId();
    $.ajax({
        type: 'GET',
        url: '/api/boards/' + hId + '/with-comments',
        success: (response) => {
            $('#title').text(response.title);
            $('#content').text(response.content);
            $('#userId').text(response.userId);
            $('#created').text(response.created);

            if (currentUserId != response.userId) {
                $('#editBtn').prop('disabled', true);
                $('#deleteBtn').prop('disabled', true);
            }

            $('#fileList').empty();

            if (response.filePath && response.filePath.length > 0) {
                let filePath = response.filePath;
                $('#hiddenFilePath').val(filePath)
                let normalized = filePath.replace(/\\/g, '/');
                let fileName = normalized.substring(normalized.lastIndexOf('/') + 1);
                let fileElement = `
                            <li>
                                <a href="/api/boards/file/download/${fileName}">${fileName}</a> <!-- 다운로드 링크 -->
                            </li>`;
                $('#fileList').append(fileElement);
            } else {
                $('#fileList').append('<li>첨부된 파일이 없습니다.</li>');
            }

            renderComments(response.comments);

        },
        error: function (error) {
            console.error('오류 발생:', error);
            alert('상세 데이터를 불러오는데 오류가 발생했습니다.');
        }
    });
}

let renderComments = (comments) => {
    const $list = $('#commentList');
    $list.empty(); // 재호출 시 중복 방지

    $('#commentCount').text(comments && comments.length > 0 ? comments.length : '');

    if (comments == null || comments.length <= 0) {
        $list.append('<li class="no-comment">아직 댓글이 없습니다. 첫 댓글을 남겨보세요!</li>');
        return;
    }

    comments.forEach((c) => {
        $list.append(
            `
            <li class="comment-item">
                <div class="comment-meta">
                    <strong>${c.userId}</strong>
                    <span class="comment-date">${c.created}</span>
                </div>
                <p class="comment-content">${c.content}</p>
            </li>
            `
        );
    });
}

let submitComment = () => {
    let hId = $('#hiddenId').val();
    let content = $('#commentContent').val();

    if (content == null || content.trim() === '') {
        alert('댓글 내용을 입력해주세요.');
        return;
    }

    $.ajax({
        type: 'POST',
        url: '/api/boards/' + hId + '/comments',
        contentType: 'application/json',                  // JSON 본문 (@RequestBody 로 받는다)
        data: JSON.stringify({ content: content }),        // CommentWriteRequestDto 필드와 키가 같아야 한다
        success: () => {
            $('#commentContent').val('');   // 입력칸 비우기
            loadBoardDetail();              // 방금 단 댓글이 보이도록 다시 조회
        },
        error: (error) => {
            console.error('오류 발생:', error);
            alert('댓글 등록 중 오류가 발생했습니다.');
        }
    });
}