package fastcampus.issueservice.web

import fastcampus.issueservice.config.AuthUser
import fastcampus.issueservice.model.CommentRequest
import fastcampus.issueservice.model.CommentResponse
import fastcampus.issueservice.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
class CommentController(
  private val commentService: CommentService
) {
  @PostMapping
  fun create(
    authUser: AuthUser,
    @PathVariable issueId: Long,
    @RequestBody request: CommentRequest
  ): CommentResponse {
    return commentService.create(issueId, authUser.userId, authUser.name, request)
  }

  @PutMapping("/{id}")
  fun edit(
    authUser: AuthUser,
    @PathVariable id: Long,
    @RequestBody request: CommentRequest,
  ) = commentService.edit(id, authUser.userId, request)

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(
    authUser: AuthUser,
    @PathVariable issueId: Long,
    @PathVariable id: Long,
  ){
    commentService.delete(issueId, id, authUser.userId)
  }
}