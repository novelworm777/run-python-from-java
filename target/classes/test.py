import os

dir = os.getcwd()
filename = "testfile"
output = ["Hello World", 999, True]

with open(os.path.join(dir, filename), 'w') as f:
    for o in output:
        f.write(str(o) + "\n")