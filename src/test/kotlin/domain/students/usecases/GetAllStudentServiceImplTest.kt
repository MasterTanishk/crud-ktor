import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.usecases.GetAllStudentServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class GetAllStudentServiceImplTest {

    private lateinit var studentRepo: StudentRepo
    private lateinit var getAllStudentService: GetAllStudentServiceImpl

    @BeforeEach
    fun setup() {
        studentRepo = mock()
        getAllStudentService = GetAllStudentServiceImpl(studentRepo)
    }

    @Test
    fun `should return a list of students`() {
        // Arrange
        val mockStudents = listOf(
            Todolist(1, 101, "Alice"),
            Todolist(2, 102, "Bob")
        )
        whenever(studentRepo.fetchAll()).thenReturn(mockStudents)

        // Act
        val result = getAllStudentService.invoke()

        // Assert
        assertEquals(2, result.size)
        assertEquals("Alice", result[0].username)
        verify(studentRepo, times(1)).fetchAll()
    }
}
