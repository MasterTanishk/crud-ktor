import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.usecases.GetAllStudentServiceImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GetAllStudentServiceImplTest {

    private lateinit var studentRepo: StudentRepo
    private lateinit var getAllStudentService: GetAllStudentServiceImpl

    @BeforeEach
    fun setup() {
        // Create a mock for the StudentRepo
        studentRepo = mockk()
        getAllStudentService = GetAllStudentServiceImpl(studentRepo)
    }

    @Test
    fun `should return a list of students`() {
        // Arrange
        val mockStudents = listOf(
            Todolist(1, 101, "Alice"),
            Todolist(2, 102, "Bob")
        )
        coEvery { studentRepo.fetchAll() } returns mockStudents

        // Act
        val result = getAllStudentService.invoke()

        // Assert
        assertEquals(2, result.size)
        assertEquals("Alice", result[0].username)

        // Verify that the fetchAll() function was called exactly once
        coVerify { studentRepo.fetchAll() }
    }
}
