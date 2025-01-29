import com.example.data.students.repos.StudentRepo
import com.example.domain.students.entities.Todolist
import com.example.domain.students.repos.StudentRepositoryContract
import com.example.domain.students.usecases.GetStudentService
import com.example.exception.StudentNotFoundException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class GetStudentServiceTest {

    private lateinit var studentRepo: StudentRepositoryContract
    private lateinit var getStudentService: GetStudentService

    @BeforeEach
    fun setup() {
        studentRepo = mockk<StudentRepositoryContract>()
        getStudentService = GetStudentService(studentRepo)
    }

    @Test
    fun `should return a student by id`() {
        val id = 1
        val expectedStudent = Todolist(id, 101, "Alice")
        coEvery { studentRepo.get(id) } returns expectedStudent

        val result = getStudentService.invoke(id)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(101, result?.userid)
        Assertions.assertEquals("Alice", result?.username)

        coVerify { studentRepo.get(id) }
    }
}
