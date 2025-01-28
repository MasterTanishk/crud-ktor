import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.requests.CreateRequest
import com.example.domain.students.usecases.CreateStudentServiceImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class CreateStudentServiceImplTest {

    private lateinit var studentRepo: StudentRepo
    private lateinit var createStudentService: CreateStudentServiceImpl

    @BeforeEach
    fun setup() {
        studentRepo = mockk()
        createStudentService = CreateStudentServiceImpl(studentRepo)
    }

    @Test
    fun `should create a student and return it`() {
        val request = CreateRequest(userid = 101, username = "Alice")
        val expectedStudent = Todolist(1, 101, "Alice")

        coEvery { studentRepo.create(request) } returns expectedStudent

        val result = createStudentService.invoke(request)

        assertNotNull(result)
        assertEquals(101, result.userid)
        assertEquals("Alice", result.username)

        coVerify(exactly = 1) { studentRepo.create(request) }
    }

    @Test
    fun `should throw exception if student creation fails`() {
        val request = CreateRequest(userid = 101, username = "Alice")

        coEvery { studentRepo.create(request) } throws IllegalStateException("Failed to create student")

        val exception = assertFailsWith<IllegalStateException> {
            createStudentService.invoke(request)
        }

        assertEquals("Failed to create student", exception.message)

        coVerify(exactly = 1) { studentRepo.create(request) }
    }
}
