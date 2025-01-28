import com.example.data.students.repos.StudentRepo
import com.example.domain.students.usecases.DeleteStudentServiceImpl
import com.example.exception.StudentNotFoundException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import kotlin.test.assertFailsWith

class DeleteStudentServiceImplTest {

    private lateinit var studentRepo: StudentRepo
    private lateinit var deleteStudentService: DeleteStudentServiceImpl

    @BeforeEach
    fun setup() {
        studentRepo = mock()
        deleteStudentService = DeleteStudentServiceImpl(studentRepo)
    }

    @Test
    fun `should delete a student when id is present`() {
        val id = 1
        doNothing().whenever(studentRepo).delete(id)
        deleteStudentService.invoke(id)
        verify(studentRepo, times(1)).delete(id)
    }

    @Test
    fun `should throw StudentNotFoundException when id does not exist`() {
        // Arrange
        val studentId = 997
        doThrow(StudentNotFoundException()).whenever(studentRepo).delete(studentId)
        assertFailsWith<StudentNotFoundException> {
            deleteStudentService.invoke(studentId)
        }
    }
}
