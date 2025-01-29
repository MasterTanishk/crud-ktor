import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.repos.StudentRepositoryContract
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

    private lateinit var studentRepositoryContract: StudentRepositoryContract
    private lateinit var createStudentService: CreateStudentServiceImpl

    @BeforeEach
    fun setup() {
        studentRepositoryContract = mockk()
        createStudentService = CreateStudentServiceImpl(studentRepositoryContract)
    }

    @Test
    fun `should create a student and return it`() {
        val request = CreateRequest(userid = 101, username = "Alice")
        val expectedStudent = Todolist(1, 101, "Alice")

        coEvery { studentRepositoryContract.create(request) } returns expectedStudent

        val result = createStudentService.invoke(request)

        assertNotNull(result)
        assertEquals(101, result.userid)
        assertEquals("Alice", result.username)

        coVerify(exactly = 1) { studentRepositoryContract.create(request) }
    }

    @Test
    fun `should throw exception if student creation fails`() {
        val request = CreateRequest(userid = 101, username = "Alice")

        coEvery { studentRepositoryContract.create(request) } throws IllegalStateException("Failed to create student")

        val exception = assertFailsWith<IllegalStateException> {
            createStudentService.invoke(request)
        }

        assertEquals("Failed to create student", exception.message)

        coVerify(exactly = 1) { studentRepositoryContract.create(request) }
    }
}
