from course import Course


def extract_course_code_and_name(soup):
    title_div = soup.find('div', class_='outline__title')
    if title_div:
        course_code = title_div.find('div', class_='outline__course-code').text.strip(
        ) if title_div.find('div', class_='outline__course-code') else ""
        course_name = title_div.find('div', class_='outline__course-name').text.strip(
        ) if title_div.find('div', class_='outline__course-name') else ""
        return course_code, course_name
    return "", ""


def extract_evaluation_criteria(soup):
    eval_criteria = {}
    eval_criteria_heading = soup.find('h3', string="Evaluation Criteria")
    if eval_criteria_heading:
        eval_table = eval_criteria_heading.find_next_sibling('table')
        if eval_table:
            # Initialize indices for criteria and percentage
            criteria_index = -1
            percentage_index = -1

            # Find header row and determine the position of 'Criteria' and '%'
            header_row = eval_table.find('tr')
            headers = [th.text.strip() for th in header_row.find_all(
                ['th', 'td'])]  # Supports 'th' or 'td' for headers

            if 'Criteria' in headers:
                criteria_index = headers.index('Criteria')
            if '%' in headers:
                percentage_index = headers.index('%')

            # Only proceed if both 'Criteria' and '%' columns were found
            if criteria_index != -1 and percentage_index != -1:
                # Iterate over each row in the table excluding the header row
                for row in eval_table.find_all('tr')[1:]:  # Skip header row
                    cells = row.find_all('td')
                    # Extract criteria and percentage based on determined indices
                    criteria = cells[criteria_index].text.strip()
                    percentage = cells[percentage_index].text.strip()
                    # Populate the dictionary
                    eval_criteria[criteria] = percentage

    return eval_criteria


def extract_course_details(soup):
    details = {}
    details_div = soup.find('div', class_='outline__details')
    if details_div:
        rows = details_div.find('tbody').find_all('tr')
        for row in rows:
            key = row.find('th').text.strip()
            value = row.find('td').text.strip()
            details[key] = value
    return details


def extract_course_description(soup):
    description_div = soup.find('div', class_='outline__course-description')
    if description_div:
        return description_div.find('p').text.strip()
    return ""


def create_course_from_soup(soup):
    course_code, course_name = extract_course_code_and_name(soup)
    eval_criteria = extract_evaluation_criteria(soup)
    details = extract_course_details(soup)
    description = extract_course_description(soup)

    # Create a Course instance
    course = Course(
        school=details.get("School", ""),
        program=details.get("Program", ""),
        course_credits=details.get("Course Credits", ""),
        minimum_passing_grade=details.get("Minimum Passing Grade", ""),
        start_date=details.get("Start Date", ""),
        end_date=details.get("End Date", ""),
        total_hours=details.get("Total Hours", ""),
        total_weeks=details.get("Total Weeks", ""),
        hours_per_week=details.get("Hours/Weeks", ""),
        delivery_type=details.get("Delivery Type", ""),
        prerequisites=details.get("Prerequisite(s)", ""),
        crn=details.get("CRN", ""),
        title=f"{course_code} - {course_name}",
        description=description,
        learning_outcomes="",
        evaluation_criteria=eval_criteria
    )

    return course


def create_complete_course(soup):
    """
    Extracts course information from a BeautifulSoup object and creates a Course instance.

    :param soup: BeautifulSoup object containing the HTML of a course page.
    :return: A Course instance with extracted details.
    """
    # Extract course code and name
    course_code, course_name = extract_course_code_and_name(soup)

    # Extract evaluation criteria
    eval_criteria = extract_evaluation_criteria(soup)

    # Extract course details
    # details = extract_course_details(soup)

    # Extract course description
    # description = extract_course_description(soup)

    # Create a Course instance with the extracted information
    course = Course(
        # school=details.get("School", ""),
        # program=details.get("Program", ""),
        # course_credits=details.get("Course Credits", ""),
        # minimum_passing_grade=details.get("Minimum Passing Grade", ""),
        # start_date=details.get("Start Date", ""),
        # end_date=details.get("End Date", ""),
        # total_hours=details.get("Total Hours", ""),
        # total_weeks=details.get("Total Weeks", ""),
        # Corrected from "Hours/Weeks" to "Hours/Week" for consistency
        # hours_per_week=details.get("Hours/Week", ""),
        # delivery_type=details.get("Delivery Type", ""),
        # prerequisites=details.get("Prerequisite(s)", ""),
        # crn=details.get("CRN", ""),
        title=f"{course_code} - {course_name}",
        # description=description,
        # learning_outcomes="",
        evaluation_criteria=eval_criteria
    )

    return course
