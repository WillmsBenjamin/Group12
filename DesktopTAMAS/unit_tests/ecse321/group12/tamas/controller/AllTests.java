package ecse321.group12.tamas.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPostTAJobaIsLab.class, TestPostTAJobApproval.class, TestPostTAJobCourse.class,
		TestPostTAJobDeadline.class, TestPostTAJobMinHours.class, TestPostTAJobRequiredCGPA.class,
		TestPostTAJobRequiredCourseGPA.class, TestPostTAJobRequiredSkills.class, TestPostTAJobWage.class,
		TestRegisterApplicantCGPA.class, TestRegisterApplicantId.class, TestRegisterApplicantIsGraduate.class,
		TestRegisterApplicantName.class, TestRegisterApplicantSkills.class, TestRegisterDepartmentDeadline.class,
		TestRegisterDepartmentId.class, TestRegisterDepartmentName.class, TestRegisterInstructorId.class,
		TestRegisterInstructorName.class, TestRemoveApplicant.class, TestRemoveApplication.class,
		TestRemoveAssignment.class, TestRemoveCourse.class, TestRemoveInstructor.class, TestRemoveJob.class })
public class AllTests {

}
