from flask import Flask, request, jsonify
from flask_cors import CORS
from open_ai import query_gpt

app = Flask(__name__)
CORS(app)
count = 0
question = ""


@app.route('/api/generate', methods=['POST'])
def generate_text():
    """Generate text using OpenAI's GPT model based on the input prompt."""
    data = request.get_json()
    prompt = data.get('prompt')
    if not prompt:
        return jsonify({'error': 'Prompt is required.'}), 400
    # You could customize model and max_tokens by passing them in data and using them here
    generated_text = query_gpt(prompt)
    return jsonify({'generated_text': generated_text})

@app.route('/api', methods=['GET'])
def hello_world():
    return jsonify({'message': 'Hello, World!'})


@app.route('/api/echo', methods=['POST'])
def echo():
    data = request.get_json()
    return jsonify(data)


if __name__ == '__main__':
    app.run(debug=True)
