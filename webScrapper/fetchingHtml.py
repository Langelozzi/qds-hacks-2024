from course import Course
import requests
from bs4 import BeautifulSoup


def download_html(url):
    try:
        # Send a GET request to the URL
        response = requests.get(url)

        # Check if the request was successful (status code 200)
        if response.status_code == 200:
            # Parse the content of the request with BeautifulSoup
            soup = BeautifulSoup(response.content, 'html.parser')
            return soup
        else:
            print(
                f"Error fetching the page. Status code: {response.status_code}")
            return None
    except Exception as e:
        print(f"An error occurred: {e}")
        return None


def fetch_and_save_crns(crns):

    base_url = "https://www.bcit.ca/outlines/202410"
    for crn in crns:
        print(f"Fetching {crn}...")
        url = f"{base_url}{crn}"
        content = download_html(url)
        if content:
            # Save to HTML file
            with open(f"webScrapper/courseOutlines/{crn}.html", "w", encoding="utf-8") as file:
                file.write(str(content))
    print("done")


crns = [
    72588,  # business comms 1
    48057,  # 1100
    48068,  # 1113
]
