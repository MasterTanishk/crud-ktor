import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.requests.CreateRequest
import com.example.domain.students.usecases.CreateStudentServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class CreateStudentServiceImplTest {

    private lateinit var studentRepo: StudentRepo
    private lateinit var createStudentService: CreateStudentServiceImpl

    @BeforeEach
    fun setup() {
        studentRepo = mock()
        createStudentService = CreateStudentServiceImpl(studentRepo)
    }

    @Test
    fun `should create a student and return it`() {
        // Arrange
        val request = CreateRequest(userid = 101, username = "Alice")
        val expectedStudent = Todolist(1, 101, "Alice")
        whenever(studentRepo.create(request)).thenReturn(expectedStudent)

        // Act
        val result = createStudentService.invoke(request)

        // Assert
        assertNotNull(result)
        assertEquals(101, result.userid)
        assertEquals("Alice", result.username)
        verify(studentRepo, times(1)).create(request)
    }
}
