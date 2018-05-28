package io.github.kihoonkim.scsa_ci.student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @MockBean(name = "studentRepository")
    private StudentRepository mockStudentRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getStudents() {
        ParameterizedTypeReference<List<Student>> type = new ParameterizedTypeReference<List<Student>>() {};
        restTemplate.exchange("/students", HttpMethod.GET, new HttpEntity<>(null, null), type);

        verify(mockStudentRepository).findAll();
    }

    @Test
    public void createStudent() {
        Student student = new Student();
        restTemplate.postForEntity("/students", student, Student.class);

        verify(mockStudentRepository).save(eq(student));
    }
}