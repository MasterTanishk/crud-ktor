import com.example.data.students.repos.StudentRepo
import com.example.domain.students.requests.UpdateRequest
import com.example.domain.students.usecases.UpdateStudentServiceImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UpdateStudentServiceImplTest {

    private lateinit var studentRepo: StudentRepo
    private lateinit var updateStudentService: UpdateStudentServiceImpl

    @BeforeEach
    fun setup() {
        // Create a mock for the StudentRepo
        studentRepo = mockk()
        updateStudentService = UpdateStudentServiceImpl(studentRepo)
    }

    @Test
    fun `should update a student`() {
        // Arrange
        val id = 1
        val updateRequest = UpdateRequest(userid = 101, username = "Alice Updated")

        // Mock the update function in StudentRepo
        coEvery { studentRepo.update(id, updateRequest) } returns Unit

        // Act
        updateStudentService.invoke(id, updateRequest)

        // Assert
        coVerify { studentRepo.update(id, updateRequest) }  // Verify that the update function was called once
    }
}
