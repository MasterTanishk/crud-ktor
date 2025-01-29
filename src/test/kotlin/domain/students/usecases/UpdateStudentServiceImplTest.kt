import com.example.data.students.repos.StudentRepo
import com.example.domain.students.repos.StudentRepositoryContract
import com.example.domain.students.requests.UpdateRequest
import com.example.domain.students.usecases.UpdateStudentServiceImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UpdateStudentServiceImplTest {

    private lateinit var studentRepo: StudentRepositoryContract
    private lateinit var updateStudentService: UpdateStudentServiceImpl

    @BeforeEach
    fun setup() {
        studentRepo = mockk()
        updateStudentService = UpdateStudentServiceImpl(studentRepo)
    }

    @Test
    fun `should update a student`() {

        val id = 1
        val updateRequest = UpdateRequest(userid = 101, username = "Alice Updated")

        coEvery { studentRepo.update(id, updateRequest) } returns Unit

        updateStudentService.invoke(id, updateRequest)

        coVerify { studentRepo.update(id, updateRequest) }
    }
}
