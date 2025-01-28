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
        studentRepo = mockk()
        getAllStudentService = GetAllStudentServiceImpl(studentRepo)
    }

    @Test
    fun `should return a list of students`() {
        val mockStudents = listOf(
            Todolist(1, 101, "Alice"),
            Todolist(2, 102, "Bob")
        )
        coEvery { studentRepo.fetchAll() } returns mockStudents

        val result = getAllStudentService.invoke()

        assertEquals(2, result.size)
        assertEquals("Alice", result[0].username)

        coVerify { studentRepo.fetchAll() }
    }
}
