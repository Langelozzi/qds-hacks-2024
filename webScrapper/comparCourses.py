def compare_courses(course1, course2):
    attributes = ["school", "program", "course_credits", "minimum_passing_grade",
                  "start_date", "end_date", "total_hours", "total_weeks", "hours_per_week",
                  "delivery_type", "prerequisites", "crn", "title", "description",
                  "learning_outcomes", "evaluation_criteria"]
    for attr in attributes:
        attr1 = getattr(course1, attr, None)
        attr2 = getattr(course2, attr, None)
        if attr1 == attr2:
            print(f"\033[32m{attr}: MATCH\033[0m")
        else:
            print(f"\033[31m{attr}: NOT MATCH\033[0m")
