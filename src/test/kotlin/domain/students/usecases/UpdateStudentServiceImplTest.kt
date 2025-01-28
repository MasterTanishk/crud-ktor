import com.example.data.students.repos.StudentRepo
import com.example.domain.students.requests.UpdateRequest
import com.example.domain.students.usecases.UpdateStudentServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class UpdateStudentServiceImplTest {

    private lateinit var studentRepo: StudentRepo
    private lateinit var updateStudentService: UpdateStudentServiceImpl

    @BeforeEach
    fun setup() {
        studentRepo = mock()
        updateStudentService = UpdateStudentServiceImpl(studentRepo)
    }

    @Test
    fun `should update a student`() {
        // Arrange
        val id = 1
        val updateRequest = UpdateRequest(userid = 101, username = "Alice Updated")

        // Act
        updateStudentService.invoke(id, updateRequest)

        // Assert
        verify(studentRepo, times(1)).update(id, updateRequest)
    }
}
