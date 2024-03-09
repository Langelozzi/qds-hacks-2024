from bs4 import BeautifulSoup
from db import execute_query_with_values, execute_query_with_values_batch
from fetchingHtml import fetch_and_save_crns
from parsingHtml import create_complete_course


def main():
    crns = [
        # term 1
        72588,  # business comms 1
        48068,  # 1113
        87560,  # 1537
        86105,
        87568,

        # term 2
        82195,
        72562,
        12573,
        86143,
        77186,
        72574,

        # term 3
        87617,
        73701,
        73698,
        81885,
        87231,

        # term 3 options
        85360,
        84594,
        86083,
        73705,
        87612,
        31249,
        47798,
        46827,
        32087,
        3910,
        46927,

        # term 4
    ]
    # Attempt to download all the html files
    # try:
    #     fetch_and_save_crns(crns)
    # except Exception as e:
    #     print(f"Error fetching and saving CRNs: {e}")
    #     return

    courses = []
    for crn in crns:
        try:
            with open(f"webScrapper/courseOutlines/{crn}.html") as file:
                html_content = file.read()
                soup = BeautifulSoup(html_content, 'html.parser')
                c = create_complete_course(soup)
                courses.append(c)
        except Exception as e:
            print(f"Error processing CRN {crn}: {e}")
            continue

    # Construct the batch query for inserting courses into the database
    query = """
    INSERT INTO courses(
        school, 
        program, 
        course_credits, 
        minimum_passing_grade, 
        start_date, 
        end_date, 
        total_hours, 
        total_weeks, 
        hours_per_week, 
        delivery_type, 
        prerequisites, 
        crn, 
        title, 
        description, 
        learning_outcomes, 
        evaluation_criteria
    ) VALUES (
        %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s
    );
    """
    # try:
    #     for course in courses:
    #         execute_query_with_values(course.get_attributes(), query)
    # except Exception as e:
    #     print(e)
    values = [(course.get_attributes()) for course in courses]

    # Attempt to execute the batch query
    try:
        execute_query_with_values_batch(query, values)
    except Exception as e:
        print(f"Error executing batch insert: {e}")


if __name__ == "__main__":
    main()
