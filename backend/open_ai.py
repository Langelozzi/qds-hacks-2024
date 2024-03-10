import openai
import os

openai.api_key = os.getenv("OPENAI_API_KEY")

def query_gpt(prompt, model="davinci-002", max_tokens=100):
    """Query GPT model and return the generated text."""
    response = openai.Completion.create(
        engine=model,
        prompt=prompt,
        max_tokens=max_tokens
    )
    return response.choices[0].text.strip()