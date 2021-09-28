import re

def solution(files):
    files.sort(key = lambda x : (re.match('[^0-9]+',x).group(0).lower(), int(re.search('[0-9]+',x).group().lstrip('0'))))
    return files
