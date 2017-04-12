package ecse321.group12.tamas.alltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ecse321.group12.tamas.controller.TestPostTAJobCourse;
import ecse321.group12.tamas.controller.TestPostTAJobMaxHours;
import ecse321.group12.tamas.controller.TestPostTAJobRequiredCGPA;
import ecse321.group12.tamas.controller.TestPostTAJobRequiredCourseGPA;
import ecse321.group12.tamas.controller.TestRegisterApplicantCGPA;
import ecse321.group12.tamas.controller.TestRegisterApplicantId;
import ecse321.group12.tamas.controller.TestPostTAJobDeadline;
import ecse321.group12.tamas.controller.TestRegisterApplicantName;
import ecse321.group12.tamas.controller.TestRegisterDepartmentDeadline;
import ecse321.group12.tamas.controller.TestRegisterDepartmentId;
import ecse321.group12.tamas.controller.TestRegisterDepartmentName;
import ecse321.group12.tamas.controller.TestRegisterInstructorId;
import ecse321.group12.tamas.controller.TestRegisterInstructorName;
import ecse321.group12.tamas.controller.TestTamasController;
import ecse321.group12.tamas.persistence.TestPersistence;
import ecse321.group12.tamas.integration.TamasIntegrationTests;

@RunWith(Suite.class)
@SuiteClasses({TestPostTAJobCourse.class, TestPostTAJobDeadline.class, TestPostTAJobMaxHours.class, TestPostTAJobRequiredCGPA.class,
	TestPostTAJobRequiredCourseGPA.class, TestRegisterApplicantCGPA.class, TestRegisterApplicantId.class, TestRegisterApplicantName.class,
	TestRegisterDepartmentDeadline.class, TestRegisterDepartmentId.class, TestRegisterDepartmentName.class, TestRegisterInstructorId.class,
	TestRegisterInstructorName.class, TestTamasController.class, TestPersistence.class, TamasIntegrationTests.class})
public class AllTests {

}
