package fastcampus.issueservice.domain.enums

enum class IssueType {
  BUG, TASK;

  companion object{
    // operator ~~ invoke => 생성자처럼 사용 가능
    operator fun invoke (type:String) = valueOf(type.uppercase())
  }
}

//fun main(){
//  val type = IssueType("BUG")
//}