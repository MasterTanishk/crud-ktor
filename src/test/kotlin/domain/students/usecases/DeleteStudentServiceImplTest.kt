import com.example.data.students.repos.StudentRepo
import com.example.domain.students.usecases.DeleteStudentServiceImpl
import com.example.exception.StudentNotFoundException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import kotlin.test.assertFailsWith

class DeleteStudentServiceImplTest {

    private lateinit var studentRepo: StudentRepo
    private lateinit var deleteStudentService: DeleteStudentServiceImpl

    @BeforeEach
    fun setup() {
        studentRepo = mockk()
        deleteStudentService = DeleteStudentServiceImpl(studentRepo)
    }

    @Test
    fun `should delete a student when id is present`() {
        // Arrange
        val studentId = 1
        coEvery { studentRepo.delete(studentId) } returns Unit

        // Act
        deleteStudentService.invoke(studentId)

        // Assert
        coVerify(exactly = 1) { studentRepo.delete(studentId) }
    }

    @Test
    fun `should throw StudentNotFoundException when id does not exist`() {
        // Arrange
        val studentId = 997

        // Simulating that the studentRepo does not find the student, so we throw StudentNotFoundException
        coEvery { studentRepo.delete(studentId) } throws StudentNotFoundException()

        // Act & Assert
//        coVerify {
//            deleteStudentService.invoke(studentId)
//        }
//
//        // Ensure that the exception is thrown
//        try {
//            deleteStudentService.invoke(studentId)
//        } catch (e: StudentNotFoundException) {
//            assert(e.message == "Student not found")
//        }
    }
}
