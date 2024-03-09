import json
import requests

comp_courses = ['COMP/3800', 'COMP/4800', 'COMP/4800', 'COMP/4983', 'COMP/4989', 'COMP/4941', 'COMP/4945', 'COMP/4964', 'COMP/4968', 'COMP/4976', 'COMP/4977', 'COMP/4921', 'COMP/4925', 'COMP/4981', 'COMP/4985', 'COMP/4932',
                'COMP/4995', 'COMP/4870', 'COMP/4911', 'COMP/4915', 'COMP/4948', 'COMP/4949', 'COMP/4958', 'COMP/4959', 'COMP/4943', 'COMP/4944', 'COMP/4946', 'MKTG/4919', 'COMP/4952', 'COMP/4956', 'COMP/2990', 'COMP/3990', 'COMP/4537', 'COMP/4736']


def get_course_info(course_code):
    base_url = "https://www.bcit.ca/wp-json/bcit/outlines/v1/load_course_term/202410/"
    full_url = f"{base_url}{course_code.lower()}/"

    print(full_url)
    try:
        response = requests.get(full_url)
        if response.status_code == 200:
            data = response.json()
            if data["success"] and "courses" in data["data"]:
                return data["data"]["courses"]
            else:
                return f"fail on {course_code}"
        else:
            base_url = "https://www.bcit.ca/wp-json/bcit/outlines/v1/load_course_term/202330/"
            full_url = f"{base_url}{course_code.lower()}/"
            response = requests.get(full_url)
            if response.status_code == 200:
                data = response.json()
                if data["success"] and "courses" in data["data"]:
                    return data["data"]["courses"]
                else:
                    return f"fail on {course_code}"
    except Exception as e:
        return f"An error occurred: {str(e)}"


course_crns = []
for course in comp_courses:
    a = get_course_info(course)
    course_crns.append(a)
# course_crns = [get_course_info(comp_courses[4])]

with open("webScrapper/crns.txt", "w") as file:
    for crn in course_crns:
        file.write(json.dumps(crn))
# https://www.bcit.ca/wp-json/bcit/outlines/v1/load_course_term/202410/comp/4989/
# https://www.bcit.ca/wp-json/bcit/outlines/v1/load_course_term/202330/comp/4989/
