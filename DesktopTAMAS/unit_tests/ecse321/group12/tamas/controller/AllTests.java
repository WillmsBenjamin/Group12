package ecse321.group12.tamas.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPostTAJobCourse.class, TestPostTAJobDeadline.class, TestPostTAJobMaxHours.class,
		TestPostTAJobRequiredCGPA.class, TestPostTAJobRequiredCourseGPA.class, TestRegisterApplicantCGPA.class,
		TestRegisterApplicantId.class, TestRegisterApplicantName.class, TestRegisterDepartmentDeadline.class,
		TestRegisterDepartmentId.class, TestRegisterDepartmentName.class, TestRegisterInstructorId.class,
		TestRegisterInstructorName.class, TestTamasController.class })
public class AllTests {

}
