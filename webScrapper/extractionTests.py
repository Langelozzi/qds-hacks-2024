import unittest
from bs4 import BeautifulSoup
from course import Course

from parsingHtml import create_course_from_soup, extract_course_code_and_name, extract_course_description, extract_course_details, extract_evaluation_criteria

# Assuming the existence of the functions and the Course class as described previously

# Sample data for comparison
sample_course_data = {
    "school": "School of Computing and Academic Studies",
    "program": "Computer Systems Technology (CST) Diploma",
    "course_credits": '7',
    "minimum_passing_grade": "50%",
    "start_date": "January 08, 2024",
    "end_date": "April 19, 2024",
    "total_hours": '105',
    "total_weeks": '15',
    "hours_per_week": '7',
    "delivery_type": "Lecture",
    "prerequisites": "No prerequisites are required for this course.",
    "crn": "48058",
    "title": "COMP 1510",
    "description": "This hands-on course is the foundation for all future programming courses and complements COMP 1537 and COMP 1800. This course introduces the fundamental concepts of programming including design, development, testing, debugging simple programs, as well as error-handling, and problem solving.",
    "evaluation_criteria": {
        "Assignments": 15,
        "Lab quizzes/assignments": "15",
        "Midterm Exam": '25',
        "Final Exam": '40',
        "Participation": '5'
    }
}


class TestCourseExtraction(unittest.TestCase):
    def setUp(self):
        self.soup = None
        with open('webScrapper/courseOutlines/48058.html', 'r', encoding='utf-8') as file:
            # Step 2: Read the file's content
            html_content = file.read()

            self.soup = BeautifulSoup(html_content, 'html.parser')

    def test_course_code_and_name(self):
        expected_title = sample_course_data["title"]
        result = extract_course_code_and_name(self.soup)[0]
        self.assertEqual(result, expected_title,
                         "Course code and name do not match")

    def test_evaluation_criteria(self):
        expected_criteria = sample_course_data["evaluation_criteria"]
        result = extract_evaluation_criteria(self.soup)
        self.assertEqual(result, expected_criteria,
                         "Evaluation criteria do not match")

    def test_course_description(self):
        expected_description = sample_course_data["description"]
        result = extract_course_description(self.soup)
        self.assertEqual(result, expected_description,
                         "Course description does not match")

    def test_course_details(self):
        # Assuming extract_course_details returns a dict with all course details except evaluation criteria
        expected_details = {key: value for key, value in sample_course_data.items(
        ) if key != "evaluation_criteria"}
        result = extract_course_details(self.soup)
        self.assertEqual(result, expected_details,
                         "Course details do not match")

    def test_create_course_from_soup(self):
        # Assuming create_course_from_soup returns a Course object
        expected_course = Course(**sample_course_data)
        result = create_course_from_soup(self.soup)
        for key in sample_course_data:
            self.assertEqual(getattr(result, key), getattr(
                expected_course, key), f"{key} does not match")


# To run the tests, you would typically use if __name__ == '__main__': unittest.main()
# This setup assumes that the actual HTML parsing functions (which were to be assumed present) correctly extract the necessary information from a BeautifulSoup object.
if __name__ == '__main__':
    unittest.main()
