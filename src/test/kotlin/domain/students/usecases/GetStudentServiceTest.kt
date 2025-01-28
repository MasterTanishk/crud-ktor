
import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.usecases.GetStudentService
import com.example.exception.StudentNotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import kotlin.test.assertFailsWith

class GetStudentServiceTest {

    private lateinit var studentRepo: StudentRepo
    private lateinit var getStudentService: GetStudentService

    @BeforeEach
    fun setup() {
        studentRepo = mock()
        getStudentService = GetStudentService(studentRepo)
    }

    @Test
    fun `should return a student by id`() {
        // Arrange
        val id = 1
        val expectedStudent = Todolist(id, 101, "Alice")
        whenever(studentRepo.get(id)).thenReturn(expectedStudent)

        // Act
        val result = getStudentService.invoke(id)

        // Assert
        assertNotNull(result)
        assertEquals(101, result.userid)
        assertEquals("Alice", result.username)
        verify(studentRepo, times(1)).get(id)
    }

    @Test
    fun `should throw StudentNotFoundException when student is not found`() {
        // Arrange
        val id = 7
        whenever(studentRepo.get(id)).thenReturn(null)  // Simulate student not found

        // Act & Assert
        assertFailsWith<StudentNotFoundException> {
            getStudentService.invoke(id)
        }
    }
}
