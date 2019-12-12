from flask import Flask
from flask import request
from flask import jsonify

import stardog_util

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'This is RTDM Flask Client! Hit the endpoint /topics. e.g. <ip_address>/topics?term=Bagging'


@app.route('/topics')
def topics():
    inputTerm = request.args.get('term')
    result = stardog_util.getTerms(inputTerm)
    print(result)
    return jsonify(result)


if __name__ == '__main__':
    app.run()
