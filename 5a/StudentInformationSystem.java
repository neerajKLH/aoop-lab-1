import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Single Responsibility Principle (SRP) - Each class has one responsibility

// Student class handles student details
class Student {
    private String id;
    private String name;
    private List<Course> courses = new ArrayList<>();

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void enrollInCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            course.addStudent(this);
        }
    }

    public void withdrawFromCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            course.removeStudent(this);
        }
    }
}

// Course class handles course details
class Course {
    private String courseId;
    private String courseName;
    private List<Student> enrolledStudents = new ArrayList<>();

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }
}

// Interface for enrollment management
interface EnrollmentManager {
    void enroll(Student student, Course course);
    void withdraw(Student student, Course course);
}

// Concrete implementation of EnrollmentManager
class EnrollmentManagerImpl implements EnrollmentManager {
    @Override
    public void enroll(Student student, Course course) {
        student.enrollInCourse(course);
    }

    @Override
    public void withdraw(Student student, Course course) {
        student.withdrawFromCourse(course);
    }
}

// Interface for handling different types of student services
interface StudentService {
    void addStudent(Student student);
    Student getStudentById(String id);
}

// Concrete implementation of StudentService
class StudentServiceImpl implements StudentService {
    private Map<String, Student> studentMap = new HashMap<>();

    @Override
    public void addStudent(Student student) {
        studentMap.put(student.getId(), student);
    }

    @Override
    public Student getStudentById(String id) {
        return studentMap.get(id);
    }
}

// Interface for handling different types of course services
interface CourseService {
    void addCourse(Course course);
    Course getCourseById(String id);
}

// Concrete implementation of CourseService
class CourseServiceImpl implements CourseService {
    private Map<String, Course> courseMap = new HashMap<>();

    @Override
    public void addCourse(Course course) {
        courseMap.put(course.getCourseId(), course);
    }

    @Override
    public Course getCourseById(String id) {
        return courseMap.get(id);
    }
}

// Main class to demonstrate the Student Information System
public class StudentInformationSystem {
    public static void main(String[] args) {
        // Create services
        StudentService studentService = new StudentServiceImpl();
        CourseService courseService = new CourseServiceImpl();
        EnrollmentManager enrollmentManager = new EnrollmentManagerImpl();

        // Create students
        Student alice = new Student("1", "Alice");
        Student bob = new Student("2", "Bob");

        // Create courses
        Course math101 = new Course("C01", "Math 101");
        Course cs101 = new Course("C02", "Computer Science 101");

        // Add students and courses to services
        studentService.addStudent(alice);
        studentService.addStudent(bob);
        courseService.addCourse(math101);
        courseService.addCourse(cs101);

        // Enroll students in courses
        enrollmentManager.enroll(alice, math101);
        enrollmentManager.enroll(bob, cs101);

        // Print details
        System.out.println("Students enrolled in " + math101.getCourseName() + ":");
        for (Student student : math101.getEnrolledStudents()) {
            System.out.println(student.getName());
        }

        System.out.println("Students enrolled in " + cs101.getCourseName() + ":");
        for (Student student : cs101.getEnrolledStudents()) {
            System.out.println(student.getName());
        }

        // Withdraw a student from a course
        enrollmentManager.withdraw(bob, cs101);

        // Print details after withdrawal
        System.out.println("Students enrolled in " + cs101.getCourseName() + " after withdrawal:");
        for (Student student : cs101.getEnrolledStudents()) {
            System.out.println(student.getName());
        }
    }
}