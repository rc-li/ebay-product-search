from app import app
from flask import request, send_from_directory
import os


@app.route("/")
def index():
    return app.send_static_file(os.path.join('js','index.html').replace('\\','/'))

@app.route("/about")
def about():
    return """
    <h1 style='color: red;'>I'm a red H1 heading!</h1>
    <p>This is a lovely little paragraph</p>
    <code>Flask is <em>awesome</em></code>
    """
