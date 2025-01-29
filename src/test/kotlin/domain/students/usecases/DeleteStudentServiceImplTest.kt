import com.example.data.students.repos.StudentRepo
import com.example.domain.students.repos.StudentRepositoryContract
import com.example.domain.students.usecases.DeleteStudentServiceImpl
import com.example.exception.StudentNotFoundException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class DeleteStudentServiceImplTest {

    private lateinit var studentRepo: StudentRepositoryContract
    private lateinit var deleteStudentService: DeleteStudentServiceImpl

    @BeforeEach
    fun setup() {
        studentRepo = mockk()
        deleteStudentService = DeleteStudentServiceImpl(studentRepo)
    }

    @Test
    fun `should delete a student when id is present`() {
        val studentId = 1
        coEvery { studentRepo.delete(studentId) } returns Unit
        deleteStudentService.invoke(studentId)
        coVerify(exactly = 1) { studentRepo.delete(studentId) }
    }

    @Test
    fun `should throw StudentNotFoundException when id does not exist`() {
        val studentId = 997
        coEvery { studentRepo.delete(studentId) } throws StudentNotFoundException()
    }
}
