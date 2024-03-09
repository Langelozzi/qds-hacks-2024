import json


class Course:
    def __init__(self, school="", program="", course_credits="", minimum_passing_grade="",
                 start_date="", end_date="", total_hours="", total_weeks="", hours_per_week="",
                 delivery_type="", prerequisites="", crn="", title="", description="",
                 learning_outcomes="", evaluation_criteria={}):
        self.school = school
        self.program = program
        self.course_credits = course_credits
        self.minimum_passing_grade = minimum_passing_grade
        self.start_date = start_date

        self.end_date = end_date
        self.total_hours = total_hours
        self.total_weeks = total_weeks
        self.hours_per_week = hours_per_week
        self.delivery_type = delivery_type
        self.prerequisites = prerequisites
        self.crn = crn
        self.title = title
        self.description = description
        self.learning_outcomes = learning_outcomes
        self.evaluation_criteria = evaluation_criteria

    def get_attributes(self):
        # Compile attributes into a list in the specified order
        attributes = [
            self.school,
            self.program,
            self.course_credits,
            self.minimum_passing_grade,
            self.start_date,
            self.end_date,
            self.total_hours,
            self.total_weeks,
            self.hours_per_week,
            self.delivery_type,
            self.prerequisites,
            self.crn,
            self.title,
            self.description,
            self.learning_outcomes,
            json.dumps(self.evaluation_criteria)
        ]
        return attributes

    def __str__(self):
        return f"""Course Information:
- School: {self.school}
- Program: {self.program}
- Course Credits: {self.course_credits}
- Minimum Passing Grade: {self.minimum_passing_grade}%
- Start Date: {self.start_date}
- End Date: {self.end_date}
- Total Hours: {self.total_hours}
- Total Weeks: {self.total_weeks}
- Hours/Week: {self.hours_per_week}
- Delivery Type: {self.delivery_type}
- Prerequisites: {self.prerequisites}
- CRN: {self.crn}
- Title: {self.title}
- Description: {self.description}
- Learning Outcomes: {self.learning_outcomes}
- Evaluation Criteria: {self.evaluation_criteria}"""


comp1510 = Course(
    school="School of Computing and Academic Studies",
    program="Computer Systems Technology (CST) Diploma",
    course_credits=7,
    minimum_passing_grade="50%",
    start_date="January 08, 2024",
    end_date="April 19, 2024",
    total_hours=105,
    total_weeks=15,
    hours_per_week=7,
    delivery_type="Lecture",
    prerequisites="No prerequisites are required for this course.",
    crn="48058",
    title="COMP 1510",
    description="This hands-on course is the foundation for all future programming courses and complements COMP 1537 and COMP 1800. This course introduces the fundamental concepts of programming including design, development, testing, debugging simple programs, as well as error-handling, and problem solving.",
    evaluation_criteria={
        "Assignments": 15,
        "Lab quizzes/assignments": 15,
        "Midterm Exam": 25,
        "Final Exam": 40,
        "Participation": 5
    }
)
