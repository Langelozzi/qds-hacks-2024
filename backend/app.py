from flask import Flask, request, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)


@app.route('/api', methods=['GET'])
def hello_world():
    return jsonify({'message': 'Hello, World!'})


@app.route('/api/echo', methods=['POST'])
def echo():
    data = request.get_json()
    return jsonify(data)


if __name__ == '__main__':
    app.run(debug=True)
